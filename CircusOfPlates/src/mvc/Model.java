package mvc;


import Parsers.Memento;
import Parsers.Stream;
import game.ScoreManager;
import game.player.Player;
import game.shapes.Shape;
import game.shapes.states.Captured;
import game.shapes.states.OnGround;
import game.shapes.states.State;
import plateGenerator.Belt;
import plateGenerator.LeftBelt;
import plateGenerator.RightBelt;

import javax.swing.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Observable;
import java.util.Observer;

import static utilities.Properties.frameHeight;
import static utilities.Properties.frameWidth;

public class Model extends Observable {

    private ArrayList<Belt> belts;
    private ArrayList<Player> players;
    private ArrayList<Shape> shapes;
    private NewShapeThread shapeThread;
    private boolean isRunning;
    private String level;
    private ScoreManager scoreManager;
    private Stream stream;
    private int laserHeight;
    private boolean twoPlayers;

    public Model(Observer gameViewer) {
        players = new ArrayList<>();
        shapes = new ArrayList<>();
        belts = new ArrayList<>();
        shapeThread = new NewShapeThread();
        isRunning = false;
        addObserver(gameViewer);
        scoreManager = null;
        laserHeight = 140;
        stream = new Stream();
    }

    private void setBelts(int x) {
        belts.add(new LeftBelt(0, 50, 400));
        belts.add(new LeftBelt(0, 100, 250));
        belts.add(new RightBelt(x, 50, 400));
        belts.add(new RightBelt(x, 100, 250));
    }

    public void setPlayers(boolean twoPlayers, ArrayList<String> names) {
        players.add(new Player(names.get(0)));
        if (twoPlayers)
            players.add(new Player(names.get(1)));
    }

    public void setLevel(String dataLevel) {
        this.level = dataLevel;
        for (Belt belt : belts) {
            belt.getRandomGenerator().setDifficultyLevel(dataLevel);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void movePlayer(Player player, int step) {
        player.move(step);
        setChanged();
        notifyObservers(players);
        player.manageStack(shapes);
        removeExpired();
        setChanged();
        notifyObservers(shapes);
    }

    private synchronized void removeExpired() {
        int size = shapes.size();
        for (int i = size - 1; i >= 0; --i) {
            Shape shape = shapes.get(i);
            State state = shape.getState();
            if ((state instanceof OnGround) || (state instanceof Captured)) {
                shapes.remove(i);
            }
        }
        setChanged();
        notifyObservers(shapes);

    }

    private synchronized void updateShapes() {
        for (Shape shape : shapes) {
            shape.update();
        }
        setChanged();
        notifyObservers(shapes);
    }

    private synchronized void updatePlayers() {
        for (Player player : players) {
            player.manageStack(shapes);
            removeExpired();
            setChanged();
            notifyObservers(shapes);
        }
        setChanged();
        notifyObservers(players);
    }

    public synchronized void startGame(LinkedHashMap<String, Object> settings, boolean newGame) {
        Boolean twoPlayers = (Boolean) settings.get("twoPlayers");
        if (newGame) {
            restart();
            ArrayList<String> names = (ArrayList<String>) settings.get("names");
            setPlayers(twoPlayers, names);
            setChanged();
        }
        setChanged();
        notifyObservers(twoPlayers);
        /* after game grid is initialized */
        setBelts(frameWidth());
        setChanged();
        notifyObservers(belts);
        /* should be called after belts set */
        setLevel((String) settings.get("level"));
        scoreManager = ScoreManager.getInstance(players, frameHeight() - laserHeight);
        shapeThread.setTimerDelay(1700);
        shapeThread.execute();
        updateGameItems();
        isRunning = true;
        notifyAll();
        // notify();
    }

    private void restart() {
        shapes.clear();
        players.clear();
    }

    public void pauseGame() {
        isRunning = false;
    }

    public synchronized void newLevel() {
        shapes.clear();
        for (Player player : players) {
            player.newLevel();
        }
        isRunning = true;
        notify();
    }

    public synchronized void continueGame() {
        isRunning = true;
        notify();
    }

    public synchronized void updateGameItems() {
        updateShapes();
        updatePlayers();
        removeExpired();
        if (scoreManager.isOver()) {
            System.out.println("game over");
            isRunning = false;
            setChanged();
            notifyObservers(scoreManager.getWinner());
        }
    }

    public synchronized boolean isRunning() throws InterruptedException {
        if (!isRunning)
            wait();
        return isRunning;
    }

    public void save() {
        stream.save(players, shapes, level, "trial");
    }

    public void load() {
        Memento loadedGame = stream.load("trial");
        players = loadedGame.getPlayers();
        shapes = loadedGame.getShapes();

        LinkedHashMap<String,Object> settings = new LinkedHashMap<>();
        ArrayList<String> names = new ArrayList<>();
        for (Player player : players)
            names.add(player.getName());

        settings.put("twoPlayers", players.size() > 1);
        settings.put("level", loadedGame.getLevel());
        settings.put("names", names);

        startGame(settings, false);
    }

    private class NewShapeThread extends SwingWorker<Void, Void> {

        int timerDelay;

        protected void setTimerDelay(int timerDelay) {
            this.timerDelay = timerDelay;
        }

        @Override
        protected Void doInBackground() throws Exception {
            while (isRunning()) {
                for (Belt belt : belts)
                    shapes.add(belt.addShape());
                setChanged();
                notifyObservers(shapes);
                Thread.sleep(timerDelay);
            }
            return null;
        }
    }
}

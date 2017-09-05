package mvc;

import static utilities.Properties.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CopyOnWriteArrayList;

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

public class Model extends Observable {

	private ArrayList<Belt> belts;
	private ArrayList<Player> players;
	private ArrayList<Shape> shapes;
	private NewShapeThread shapeThread;
	private boolean isRunning;
	private String level;
	private String firstPlayerTool;
	private ScoreManager scoreManager;
	private Stream fileManager;
	private int laserHeight;
	private boolean twoPlayers;

	public Model(Observer gameViewer) {
		players = new ArrayList<Player>();
		shapes = new ArrayList<Shape>();
		belts = new ArrayList<Belt>();
		shapeThread = new NewShapeThread();
		isRunning = false;
		addObserver(gameViewer);
		scoreManager = null;
		laserHeight = 140;
		fileManager = new Stream();
	}

	private void setBelts(int x) {
		belts.add(new LeftBelt(0, 50, 400));
		belts.add(new LeftBelt(0, 100, 250));
		belts.add(new RightBelt(x, 50, 400));
		belts.add(new RightBelt(x, 100, 250));
	}

	public void setPlayers(boolean twoPlayers, ArrayList<String> names) {

		players.add(new Player(names.get(0)));
		if (twoPlayers) {
			players.add(new Player(names.get(1)));
		}
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

	public String getFirstPlayerTool() {
		return firstPlayerTool;
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
		synchronized (shapes) {
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

	}

	private void updateShapes() {
		synchronized (shapes) {
			for (Shape shape : shapes) {
				shape.update();
			}
			setChanged();
			notifyObservers(shapes);
		}
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

	public synchronized void startGame(LinkedHashMap<String, Object> settings) {
		restart();
		twoPlayers = (boolean) settings.get("twoPlayers");
		ArrayList<String> names = (ArrayList<String>) settings.get("names");
		setPlayers(twoPlayers, names);
		setChanged();
		Boolean twoBPlayers = new Boolean(twoPlayers);
		notifyObservers(twoBPlayers);
		/* after game grid is intialized */
		setBelts(frameWidth());
		setChanged();
		notifyObservers(belts);
		/* should be called after belts set */
		setLevel((String) settings.get("level"));
		scoreManager = ScoreManager.getInstance(players, frameHeight() - laserHeight);
		shapeThread.setTimerDelay(GENERATION_SHAPES_SPEED);
		shapeThread.execute();
		updateGameItems();
		isRunning = true;
		notifyAll();

	}

	private void restart() {
		shapes.clear();
		players.clear();
	}

	public void pauseGame() {
		isRunning = false;
	}

	public synchronized void playAgain() {
		shapes.clear();
		setChanged();
		notifyObservers(shapes);
		for (Player player : players) {
			player.reset();
		}
		setChanged();
		notifyObservers(players);
		isRunning = true;
		notifyAll();
	}

	public synchronized void continueGame() {
		isRunning = true;
		notifyAll();
	}

	public synchronized void updateGameItems() {
		updateShapes();
		updatePlayers();
		removeExpired();
		if (scoreManager.isOver()) {

			isRunning = false;
			setChanged();
			notifyObservers(scoreManager.getWinner());
			System.out.println("game over after notify");
		}
	}

	public synchronized boolean isRunning() throws InterruptedException {
		if (!isRunning) {
			wait();
		}
		return isRunning;
	}

	public void saveGame() {
		// fileManager.save(players,shapes,level,twoPlayers,firstPlayerTool,"trial");
	}

	public void loadGame() {
		Memento temp = fileManager.load("trial");
		LinkedHashMap<String, Object> settings = new LinkedHashMap<String, Object>();
		settings.put("twoPlayers", temp.getTwoPlayers());
		settings.put("level", temp.getLevel());
		/*
		 * settings.put("names", names.);
		 */
	}

	private class NewShapeThread extends SwingWorker<Void, Void> {

		int timerDelay;

		protected void setTimerDelay(int timerDelay) {
			this.timerDelay = timerDelay;
		}

		@Override
		protected Void doInBackground() throws Exception {

			while (isRunning()) {
				System.out.println("Running add newShape");
				synchronized (shapes) {
					for (Belt belt : belts) {
						shapes.add(belt.addShape());
					}
				}
				System.out.println("model" + shapes.size());
				setChanged();
				notifyObservers(shapes);
				Thread.sleep(timerDelay);

			}

			return null;
		}
	}

}

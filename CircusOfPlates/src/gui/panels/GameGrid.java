package gui.panels;

import game.player.Player;

import game.player.PlayerUI;
import game.shapes.LaserBeam;
import game.shapes.Shape;
import mvc.Controller;
import plateGenerator.Belt;
import utilities.Pair;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static utilities.Properties.*;

public class GameGrid extends JPanel {

    private static final long serialVersionUID = 1L;
    private static final int leftDirection = -1;
    private static final int rightDirection = 1;
    private static final int playerStep = 60;

    private BufferedImage backGroundImage;
    private LaserBeam leaserBeam;
    private ArrayList<Belt> belts;
    private ArrayList<PlayerUI> playersUI;
    private ArrayList<Shape> shapes;
    private HashMap<Integer, Pair<JComponent, Integer>> keyBoardMoveMap;
    private boolean twoPlayers;
    private JLabel player1Name;
    private JLabel player2Name;
    private JLabel player1_score;
    private JLabel player2_score;

    // remove static dimensions in all class!!!

    public GameGrid(boolean twoPlayers, ArrayList<Player> modelPlayers) {
        try {
            backGroundImage = ImageIO.read(ResourceLoader.loadStream(BACK_GROUND));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found!");
        }
        this.setSize(frameWidth(), frameHeight());
        this.setLayout(null);
        this.setFocusable(true);
        this.twoPlayers = twoPlayers;
        leaserBeam = new LaserBeam();
        belts = new ArrayList<>();
        shapes = new ArrayList<>();
        playersUI = new ArrayList<>();
        keyBoardMoveMap = new HashMap<>();

        playersUI.add(new PlayerUI(modelPlayers.get(0), PLAYER1, leftDirection));
        this.add(playersUI.get(0));
        declarePlayer1();

        if (this.twoPlayers) {
            playersUI.add(new PlayerUI(modelPlayers.get(1), PLAYER2, rightDirection));
            this.add(playersUI.get(1));
            declarePlayer2();
        }

        setKeyBoardMoveMap(this.twoPlayers);
    }

    private void declarePlayer1() {
        player1Name = new JLabel(playersUI.get(0).getPlayer().getName());
        player1Name.setFont(player1Name.getFont().deriveFont(22.0f));
        player1Name.setBounds(30, 150, 200, 50);

        player1_score = new JLabel(Integer.toString(playersUI.get(0).getPlayer().getScore()));
        player1_score.setFont(player1Name.getFont().deriveFont(22.0f));
        player1_score.setBounds(30, 180, 150, 50);

        this.add(player1Name);
        this.add(player1_score);
    }

    private void declarePlayer2() {
        player2Name = new JLabel(playersUI.get(1).getPlayer().getName());
        player2Name.setFont(player1Name.getFont().deriveFont(22.0f));
        player2Name.setBounds(1300, 150, 200, 50);

        player2_score = new JLabel(Integer.toString(playersUI.get(1).getPlayer().getScore()));
        player2_score.setFont(player1Name.getFont().deriveFont(22.0f));
        player2_score.setBounds(1300, 180, 150, 50);

        this.add(player2Name);
        this.add(player2_score);
    }


    private void setKeyBoardMoveMap(final boolean twoPlayers) {
        keyBoardMoveMap.put(KeyEvent.VK_LEFT, new Pair(playersUI.get(0), leftDirection * playerStep));
        keyBoardMoveMap.put(KeyEvent.VK_RIGHT, new Pair(playersUI.get(0), rightDirection * playerStep));

        if (twoPlayers) {
            keyBoardMoveMap.put(KeyEvent.VK_A, new Pair(playersUI.get(1), leftDirection * playerStep));
            keyBoardMoveMap.put(KeyEvent.VK_D, new Pair(playersUI.get(1), rightDirection * playerStep));
        }
    }

    public void setController(Controller controller) {

        this.addKeyListener(new MultiKeyPressListener(controller));

    }

    private void drawBelts(Graphics2D graphics2d) {
        for (Belt belt : belts)
            belt.drawBelt(graphics2d);
    }

    private void drawPlayers(Graphics2D graphics2d) {
        for (PlayerUI player : playersUI)
            player.draw(graphics2d);
    }

    private void drawShapes(Graphics2D graphics2d) {
        for (int i = 0; i < shapes.size(); i++) {
            Shape shape = shapes.get(i);
            shape.draw(graphics2d);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.repaint();
        Graphics2D graphics2d = (Graphics2D) g;
        graphics2d.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
        leaserBeam.draw(graphics2d);
        drawShapes(graphics2d);
        drawPlayers(graphics2d);
        drawBelts(graphics2d);

    }

    public void updateBelts(ArrayList<Belt> belts) {
        this.belts = belts;
    }

    public synchronized void updateShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void updatePlayers(ArrayList<Player> players) {
        int i = 0;
        for (PlayerUI player : playersUI) {
            player.updatePLayerModel(players.get(i++));
            showScore();
        }
    }

    private void showScore() {
        if (twoPlayers)
            player2_score.setText(Integer.toString(playersUI.get(1).getPlayer().getScore()));
        player1_score.setText(Integer.toString(playersUI.get(0).getPlayer().getScore()));
    }

    private class MultiKeyPressListener implements KeyListener {

        // Set of currently pressed keys
        private final Set<Integer> pressedKeys = new HashSet<>();
        private Controller controller;

        MultiKeyPressListener(Controller controller) {
            this.controller = controller;
        }

        @Override
        public synchronized void keyPressed(KeyEvent e) {
            pressedKeys.add(e.getKeyCode());
            if (pressedKeys.size() > 0) {
                for (Integer pressedKey : pressedKeys) {
                    if (pressedKey == KeyEvent.VK_ESCAPE) {
                        controller.pauseGame();
                        pressedKeys.remove(KeyEvent.VK_ESCAPE);
                    }

                    if (keyBoardMoveMap.containsKey(pressedKey)) {
                        Pair<JComponent, Integer> moveInfo = keyBoardMoveMap.get(pressedKey);
                        controller.movePlayer((PlayerUI) moveInfo.getFirst(), (int) moveInfo.getSecond());
                    }
                }
            }
        }

        @Override
        public synchronized void keyReleased(KeyEvent e) {
            pressedKeys.remove(e.getKeyCode());
        }

        @Override
        public void keyTyped(KeyEvent arg0) {
            // TODO Auto-generated method stub

        }
    }

}
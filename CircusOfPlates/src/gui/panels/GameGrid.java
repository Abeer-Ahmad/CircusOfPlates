package gui.panels;

import java.awt.Graphics;
 
 
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import game.player.Player;
import game.player.PlayerUI;
import game.shapes.LaserBeam;
import game.shapes.Shape;
import gui.handlers.KeyBoardHandler;
import gui.handlers.MouseHandler;
import mvc.Controller;
import plateGenerator.Belt;
import static utilities.Properties.*;

public class GameGrid extends JPanel {

    private static final long serialVersionUID = 1L;
    private BufferedImage backGroundImage;
    private LaserBeam leaserBeam;
    private ArrayList<Belt> belts;
    private ArrayList<PlayerUI> playersUI;
    private ArrayList<Shape> shapes;
    private boolean twoPlayers;
    private String firstPlayerTool;
    private JLabel player1Name;
    private JLabel player2Name;
    private JLabel player1_score;
    private JLabel player2_score;

    // remove static dimensions in all class!!!

    public GameGrid(boolean twoPlayers, ArrayList<Player> modelPlayers, String firstPlayerTool) {
        this.setSize(frameWidth(), frameHeight());
        try {
            backGroundImage = ImageIO.read(new File(BACK_GROUND));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found!");
        }
        this.setLayout(null);
        this.twoPlayers = twoPlayers;
        this.firstPlayerTool=firstPlayerTool;
        this.setFocusable(true);
        leaserBeam = new LaserBeam();
        belts= new  ArrayList<>();
        shapes= new ArrayList<>();
        playersUI= new ArrayList<>();
        playersUI.add(new PlayerUI(modelPlayers.get(0), PLAYER1, -1));
        this.add(playersUI.get(0));
        declarePlayer1();
		if (this.twoPlayers) {
			playersUI.add(new PlayerUI(modelPlayers.get(1), PLAYER2, 1));
			this.add(playersUI.get(1));
			declarePlayer2();
		}     
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

    /* move to keyboard handler */
    private void setKeyBoardController(final Controller controller, final PlayerUI keyPlayer) {
        /*KeyBoardHandler keyBoardHandler = new KeyBoardHandler(controller,keyPlayer);
        keyPlayer.requestFocus();
         keyPlayer.addKeyListener(keyBoardHandler);
        this.addKeyListener(keyBoardHandler);
        //this.requestFocus();*/
        keyPlayer.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),
                "pressedR");
        keyPlayer.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),
                "pressedL");
        AbstractAction pressedActionL = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                controller.movePlayer(keyPlayer, -50);
            }
        };
        AbstractAction pressedActionR = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                controller.movePlayer(keyPlayer, 50);
            }
        };

        keyPlayer.getActionMap().put("pressedL", pressedActionL);
        keyPlayer.getActionMap().put("pressedR", pressedActionR);
    }

    private void setMouseController(final Controller controller, final PlayerUI mousePlayer) {
        /*MouseHandler mouseHandler = new MouseHandler(controller,mousePlayer);
        mousePlayer.requestFocus();
        mousePlayer.addMouseListener(mouseHandler);
        mousePlayer.addMouseMotionListener(mouseHandler);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);*/
        mousePlayer.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"),
                "pressedR");
        mousePlayer.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"),
                "pressedL");
        AbstractAction pressedActionL = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                controller.movePlayer(mousePlayer, -40);
            }
        };
        AbstractAction pressedActionR = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                controller.movePlayer(mousePlayer, 40);
            }
        };
        mousePlayer.getActionMap().put("pressedL", pressedActionL);
        mousePlayer.getActionMap().put("pressedR", pressedActionR);
    }

    public void setController(Controller controller) {
        this.addKeyListener(new KeyBoardHandler(controller));
        if (firstPlayerTool.equals("Keyboard")) {
            setKeyBoardController(controller, playersUI.get(0));
            if (twoPlayers)
                setMouseController(controller, playersUI.get(1));
        } else if (firstPlayerTool.equals("Mouse")) {
            setMouseController(controller, playersUI.get(0));
            if (twoPlayers)
                setKeyBoardController(controller, playersUI.get(1));
        }
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
        /*if ((firstPlayerTool.equals("Mouse"))||(twoPlayers)){
            setMouseController(controller);
        }
        this.add(players.get(0));
        this.add(players.get(1));
        setKeyBoardController(controller,players.get(0));
        setMouseController(controller,players.get(1));*/
    }

    public void updateBelts(ArrayList<Belt> belts) {
        this.belts = belts;
    }

    public synchronized void updateShapes(ArrayList<Shape> shapes) {
        this.shapes = shapes;
    }

    public void updatePlayers(ArrayList<Player> players) {
        int i = 0;
        for (PlayerUI player : this.playersUI) {
            player.updatePLayerModel(players.get(i++));
            showScore();
        }
    }

    private void showScore() {
        if (twoPlayers)
            player2_score.setText(Integer.toString(playersUI.get(1).getPlayer().getScore()));
        player1_score.setText(Integer.toString(playersUI.get(0).getPlayer().getScore()));
    }
}
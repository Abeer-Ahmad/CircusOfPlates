package gui.panels;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import game.player.Player;
import mvc.Controller;
import mvc.IViewer;

public class WinnerView extends JPanel implements ActionListener,IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton exit;
	private ImageIcon exitImage;
	private JButton importNewShape;
	private ImageIcon importShapeImage;
	private ImageIcon loadGameImage;
	private JButton continueGame;
	private JButton newGame;
	private ImageIcon newGameImage;
	// private JLabel winner;
	private Controller controller;
	public WinnerView(Player player, int xFrame, int yFrame) {
		this.setSize(xFrame, yFrame);
		try {
			backGroundImage = ImageIO.read(new File("resources/imgs/newgame.jpg"));
			newGameImage = new ImageIcon(ImageIO.read(new File("resources/imgs/button.jpg")));
			loadGameImage = new ImageIcon(ImageIO.read(new File("resources/imgs/background.jpg")));
			exitImage = new ImageIcon(ImageIO.read(new File("resources/imgs/background.jpg")));
		} catch (IOException e) {
			throw new RuntimeException("Image not found");
		}
		this.setLayout(null);
		setBounds(0, 0, 1000, 1000);
		this.setFocusable(true);
		JOptionPane.showMessageDialog(null, player.getName() + " wins with "
				+ Integer.toString(player.getScore()) + " points", "Warning",
				JOptionPane.PLAIN_MESSAGE);
		// winner = new JLabel(player.getName() + " wins with " + Integer.toString(player.getScore()) + " points");
		// winner.setBounds(200, 200, 500, 50);
		setButtons();
		repaint();
	}

	public void setController(Controller controller){
		this.controller= controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame) {
			controller.changeDisplay("playerMenu");
		}
		if (e.getSource() == continueGame) {
			controller.newLevel();
		}
		if (e.getSource() == exit) {
			controller.exitGame();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() {
		newGame = new JButton("New Game");
		newGame.setBounds(250, 50, 500, 100);
		newGame.addActionListener(this);
		this.add(newGame);
		continueGame = new JButton("PlayAgain");
		continueGame.setBounds(250, 200, 500, 100);
		continueGame.addActionListener(this);
		this.add(continueGame);
		exit = new JButton("Exit Program");
		exit.setBounds(250, 350, 500, 100);
		exit.addActionListener(this);
		this.add(exit);
	}

}


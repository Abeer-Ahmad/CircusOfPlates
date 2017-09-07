package gui.panels;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import game.player.Player;
import mvc.Controller;
import mvc.IViewer;
import utilities.ResourceLoader;

import static utilities.Properties.*;

public class WinnerView extends JPanel implements ActionListener,IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton exit;
	private JButton continueGame;
	private JButton newGame;
	private Controller controller;
	public WinnerView(Player player) {
		setSize(frameWidth(), frameHeight());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			backGroundImage = ImageIO.read(ResourceLoader.load(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		this.setFocusable(true);
		JOptionPane.showMessageDialog(null, player.getName() + " wins with "
				+ Integer.toString(player.getScore()) + " points", "Warning",
				JOptionPane.PLAIN_MESSAGE);
		setButtons();
		repaint();
	}

	public void setController(Controller controller){
		this.controller= controller;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == newGame)
			controller.changeDisplay("playerMenu");
		if (e.getSource() == continueGame)
			controller.playAgain();
		if (e.getSource() == exit)
			controller.exitGame();
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() { // remove static dimensions!!!
		newGame = new JButton("New Game");
		newGame.setBounds(250, 50, 500, 100);
		newGame.addActionListener(this);
		this.add(newGame);
		continueGame = new JButton("Play Again");
		continueGame.setBounds(250, 200, 500, 100);
		continueGame.addActionListener(this);
		this.add(continueGame);
		exit = new JButton("Quit");
		exit.setBounds(250, 350, 500, 100);
		exit.addActionListener(this);
		this.add(exit);
	}
}


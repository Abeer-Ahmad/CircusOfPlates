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
import static utilities.Properties.*;

public class WinnerView extends JPanel implements ActionListener,IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton exit;
	private JButton playAgain;
	private JButton newGame;
	private Controller controller;
	public WinnerView(Player player) {
		setSize(frameWidth(), frameHeight());
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			backGroundImage = ImageIO.read(new File(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.setLayout(null);
		this.setFocusable(true);
		System.out.println("inside constructor of winnerview");
		System.out.println(player.getName()+ "  "+player.getScore());
		System.out.println("inside constructor of winnerview");
		JOptionPane.showMessageDialog(null, player.getName() + " wins with "
				+ Integer.toString(player.getScore()) + " points", "Winner Announcement",
				JOptionPane.PLAIN_MESSAGE);
		System.out.println("after JOption constructor of winnerview");
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
		if (e.getSource() == playAgain)
			controller.playAgain();
		if (e.getSource() == exit)
			controller.exitGame();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.repaint();
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
		System.out.println("repaint winner view");
	}

	private void setButtons() { // remove static dimensions!!!
		newGame = new JButton("New Game");
		newGame.setBounds(250, 50, 500, 100);
		newGame.addActionListener(this);
		this.add(newGame);
		playAgain = new JButton("Play Again");
		playAgain.setBounds(250, 200, 500, 100);
		playAgain.addActionListener(this);
		this.add(playAgain);
		exit = new JButton("Quit");
		exit.setBounds(250, 350, 500, 100);
		exit.addActionListener(this);
		this.add(exit);
	}
}


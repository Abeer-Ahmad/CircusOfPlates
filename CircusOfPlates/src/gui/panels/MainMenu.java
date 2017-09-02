package gui.panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import mvc.Controller;
import mvc.IViewer;
import static utilities.Properties.*;

public class MainMenu extends JPanel implements ActionListener,IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton exit;
	private JButton loadSavedGame;
	private JButton newGame;
	private Controller controller;

	public MainMenu() {
		this.setSize(frameWidth(), frameHeight());
		try {
			backGroundImage = ImageIO.read(new File(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		}
		setSize(frameWidth(), frameHeight());
		this.setLayout(null);
		this.setFocusable(true);
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
		if (e.getSource() == loadSavedGame)
			controller.load();
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
		loadSavedGame = new JButton("Load Game");
		loadSavedGame.setBounds(250, 200, 500, 100);
		loadSavedGame.addActionListener(this);
		this.add(loadSavedGame);
		exit = new JButton("Quit");
		exit.setBounds(250, 350, 500, 100);
		exit.addActionListener(this);
		this.add(exit);
	}
}

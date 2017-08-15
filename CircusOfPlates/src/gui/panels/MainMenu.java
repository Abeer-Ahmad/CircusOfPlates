package gui.panels;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import mvc.Controller;
import mvc.IViewer;

public class MainMenu extends JPanel implements ActionListener,IViewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton exit;
	private ImageIcon exitImage;
	// private LoadImage loadImage;
	private JButton importNewShape;
	private ImageIcon importShapeImage;
	private ImageIcon loadGameImage;
	private JButton loadSavedGame;
	private JButton newGame;
	private ImageIcon newGameImage;
	private Controller controller;
	public MainMenu(int xframe, int yFrame) {
		// loadImage = new LoadImage();
		try {
			backGroundImage = ImageIO.read(new File("newgamef.jpg"));
			newGameImage = new ImageIcon(ImageIO.read(new File("button.jpg")));
			loadGameImage = new ImageIcon(ImageIO.read(new File("background.jpg")));
			importShapeImage = new ImageIcon(ImageIO.read(new File("background.jpg")));
			exitImage = new ImageIcon(ImageIO.read(new File("background.jpg")));
		} catch (IOException e) {
			throw new RuntimeException("Image not found");
		}
		this.setLayout(null);
		setBounds(0, 0, 1000, 1000);
		this.setFocusable(true);
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
		if (e.getSource() == loadSavedGame) {
			System.out.println("Load");
		}
		if (e.getSource() == exit) {
			controller.exitGame();
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, null);
	}

	private void setButtons() {
		newGame = new JButton("New Game");
		newGame.setBounds(250, 50, 500, 100);
		newGame.addActionListener(this);
		this.add(newGame);
		loadSavedGame = new JButton("Load");
		loadSavedGame.setBounds(250, 200, 500, 100);
		loadSavedGame.addActionListener(this);
		this.add(loadSavedGame);
		exit = new JButton("Exit Program");
		exit.setBounds(250, 350, 500, 100);
		exit.addActionListener(this);
		this.add(exit);
	}

}

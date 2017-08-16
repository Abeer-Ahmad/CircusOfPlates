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

public class PauseMenu extends JPanel implements ActionListener,IViewer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton continueGame;
	private ImageIcon continueGameImage;
	private JButton mainMenu;
	private ImageIcon mainMenuImage;
	// private LoadImage loadImage;
	private JButton saveGame;
	private ImageIcon saveGameImage;
    private Controller controller;
	public PauseMenu(int xFrame,int yFrame) {
		// loadImage = new LoadImage();
		this.setSize(xFrame, yFrame);
		try {
			backGroundImage = ImageIO.read(new File("resources" + File.separator + "imgs" + File.separator + "newgame.jpg"));
			continueGameImage = new ImageIcon(ImageIO.read(new File("resources" + File.separator + "imgs" + File.separator + "background.jpg")));
			saveGameImage = new ImageIcon(ImageIO.read(new File("resources" + File.separator + "imgs" + File.separator + "background.jpg")));
			mainMenuImage = new ImageIcon(ImageIO.read(new File("resources" + File.separator + "imgs" + File.separator + "background.jpg")));
		} catch (IOException e) {
			throw new RuntimeException("Image not found");
		}
		this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(null);
		this.setFocusable(true);
		setButtons();
		repaint();
	}
	
	public void setController (Controller controller){
		this.controller= controller;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == continueGame) {
			controller.continueGame();
		}
		if (e.getSource() == saveGame) {
			controller.save();
		}
		if (e.getSource() == mainMenu) {
			controller.changeDisplay("mainMenu");;
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() {
		continueGame = new JButton("continueGame");
		continueGame.setBounds(250, 100, 500, 100);
		continueGame.addActionListener(this);
		this.add(continueGame);
		saveGame = new JButton("saveGame");
		saveGame.setBounds(250, 300, 500, 100);
		saveGame.addActionListener(this);
		this.add(saveGame);
		mainMenu = new JButton("mainMenu");
		mainMenu.setBounds(250, 500, 500, 100);
		mainMenu.addActionListener(this);
		this.add(mainMenu);
	}

}

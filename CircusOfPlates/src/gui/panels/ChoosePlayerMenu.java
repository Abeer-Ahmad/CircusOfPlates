package gui.panels;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.Controller;
import mvc.IViewer;
import static utilities.Properties.*;
public class ChoosePlayerMenu extends JPanel implements IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton onePlayer;
	private JButton twoPlayer;
	private ActionListener menuController;
	private ActionListener infoController;
	private JTextField userName;
	private JTextField userName2;
	private  JComboBox<String> levelsList;
    private boolean twoPLayers;
    private String dataLevel;
    private int y;

	// remove static dimensions in all class!!!
    
	public ChoosePlayerMenu() {
		this.setSize(frameWidth(), frameHeight());
		try {
			backGroundImage = ImageIO.read(new File(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		}
		this.setLayout(null);
		this.setFocusable(true);
		setButtons();
		repaint();
	}

	public void setController (Controller controller){
		this.menuController = new PlayerMenuController(controller);
		onePlayer.addActionListener(this.menuController);
		twoPlayer.addActionListener(this.menuController);
		
	}
	

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() {
		onePlayer = new JButton("One Player");
		onePlayer.setBounds(250, 200, 500, 100);
		this.add(onePlayer);
		twoPlayer = new JButton("Two Players");
		twoPlayer.setBounds(250, 400, 500, 100);
		this.add(twoPlayer);
		
	}
	
	private JPanel setPlayerInfoPanel(boolean twoPlayers) {
	    JPanel playerInfo = new JPanel();
		playerInfo.setLayout(null);
		playerInfo.setPreferredSize(new Dimension(400, 400));
		if (twoPlayers)
			setInfoTwoPlayer(playerInfo);
		else
			setInfoOnePlayer(playerInfo);
		
		JLabel difficultyLevel = new JLabel("Difficulty Level");
		difficultyLevel.setBounds(60, y + 40, 250, 30);
		final DefaultComboBoxModel<String> levels = new DefaultComboBoxModel<String>();
		levels.addElement("Easy");
		levels.addElement("Medium");
		levels.addElement("Hard");
		levelsList = new JComboBox<String>(levels);
		levelsList.setSelectedIndex(-1);
		levelsList.setBounds(230, y + 40 , 80, 30);
		this.infoController= new infoMenuController();
		levelsList.addActionListener(infoController);
		playerInfo.add(difficultyLevel);
		playerInfo.add(levelsList);
		return playerInfo;
	}
	
	public LinkedHashMap<String,Object> getConfigurations()	{
		LinkedHashMap<String,Object> settings = new LinkedHashMap<>();
		ArrayList<String> names = new ArrayList<>();
		names.add(userName.getText());
		if (twoPLayers){
			names.add(userName2.getText());	
		}
		settings.put("twoPlayers", twoPLayers);
		settings.put("level",dataLevel);
		settings.put("names", names);
		return settings;
	}
	
private void setInfoOnePlayer(JPanel current){
	JLabel name = new JLabel("Enter your name");
	  name.setBounds(60,40,250,30);
	 userName = new JTextField();
	 userName.setBounds(60,80,250,30);
	 y = 120;
	 current.add(name);
	 current.add(userName);
}

private void setInfoTwoPlayer(JPanel current){
	JLabel name = new JLabel("Player1, Enter your name");
	  name.setBounds(60,40,250,30);
	 userName = new JTextField();
	 userName.setBounds(60,80,250,30);
	 JLabel name2 = new JLabel("Player2, Enter your name");
	 name2.setBounds(60,120,250,30);
	  userName2 = new JTextField();
	 userName2.setBounds(60,160,250,30);
	 y = 200;
	 current.add(name);
	 current.add(userName);
	 current.add(name2);
	 current.add(userName2);
}


private class PlayerMenuController implements ActionListener {
	private Controller controller;
	public PlayerMenuController (Controller controller){
		this.controller= controller;
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			 twoPLayers=false;
			if (e.getSource() == onePlayer)
				twoPLayers =false;
			if (e.getSource() == twoPlayer)
				twoPLayers = true;	
			this.controller.popMessage(ChoosePlayerMenu.this,setPlayerInfoPanel(twoPLayers));
		}
}

private class infoMenuController implements ActionListener {

		public infoMenuController() {

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == levelsList)
				dataLevel = levelsList.getItemAt(levelsList.getSelectedIndex());
		}
	}
}

package gui;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mvc.Controller;
import mvc.PlayerNumbers;

public class ChoosePlayerMenu extends JPanel implements ViewerIF {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private JButton onePlayer;
	private ImageIcon onePlayerImage;
	private JButton twoPlayer;
	private ImageIcon twoPlayerImage;
	private ActionListener menuController;
	private ActionListener infoController;
	private Controller controller;
	private JTextField userName;
	private JTextField userName2;
	private  JComboBox<String> levelsList;
	private  JComboBox<String> toolsList;
    private boolean twoPLayers;
    private String dataLevel;
    private String tool;
    private int yFrame;
    private int xFrame;
    private int y;
	public ChoosePlayerMenu(int xFrame, int yFrame) {
		// loadImage = new LoadImage();
		try {
			backGroundImage = ImageIO.read(new File("newgamef.jpg"));
			onePlayerImage = new ImageIcon(ImageIO.read(new File("background.jpg")));
			twoPlayerImage = new ImageIcon(ImageIO.read(new File("images.png")));
		} catch (IOException e) {
			throw new RuntimeException("Image not found");
		}
		//this.setBorder(new EmptyBorder(15, 15, 15, 15));
		this.setLayout(null);
		this.setFocusable(true);
		setButtons();
		this.yFrame=yFrame;
		this.xFrame=xFrame;
		repaint();
	}

	public void setController (Controller controller){
		this.controller= controller;
		this.menuController = new PlayerMenuController(controller);
		
		onePlayer.addActionListener(this.menuController);
		twoPlayer.addActionListener(this.menuController);
		
	}
	

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, null);
	}

	private void setButtons() {
		onePlayer = new JButton("Player One");
		onePlayer.setBounds(250, 200, 500, 100);
		
		this.add(onePlayer);
		twoPlayer = new JButton("Player Two");
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
		
		final DefaultComboBoxModel<String> tools = new DefaultComboBoxModel<String>();
		tools.addElement("Mouse");
		tools.addElement("KeyBoard");
		toolsList = new JComboBox<String>(tools);
		toolsList.setSelectedIndex(-1);
		toolsList.setBounds(230, y, 80, 30);
		
		JLabel difficultyLevel = new JLabel("Difficulty Level");
		difficultyLevel.setBounds(60, y + 40, 250, 30);
		final DefaultComboBoxModel<String> levels = new DefaultComboBoxModel<String>();
		levels.addElement("Easy");
		levels.addElement("Medium");
		levels.addElement("Hard");
		levelsList = new JComboBox<String>(levels);
		levelsList.setSelectedIndex(-1);
		levelsList.setBounds(230, y + 40 , 80, 30);
		this.infoController= new infoMenuController(controller);
		levelsList.addActionListener(infoController);
		toolsList.addActionListener(infoController);
		playerInfo.add(difficultyLevel);
		playerInfo.add(toolsList);
		playerInfo.add(levelsList);
		return playerInfo;
	}
	
	public LinkedHashMap<String,Object> getConfigurations()	{
		LinkedHashMap<String,Object> settings = new LinkedHashMap<String,Object>();
		ArrayList<String> names = new ArrayList<String>();
		names.add(userName.getText());
		if (twoPLayers){
			names.add(userName2.getText());	
		}
		settings.put("twoPlayers", twoPLayers);
		settings.put("level",dataLevel);
		settings.put("names", names);
		settings.put("tool",tool);
		settings.put("dimX", xFrame);
		settings.put("dimY",yFrame);
		return settings;
		
	
	}
	
private void setInfoOnePlayer(JPanel current){
	JLabel name = new JLabel("Enter your name");
	  name.setBounds(60,40,250,30);
	 userName = new JTextField();
	 userName.setBounds(60,80,250,30);
	 y = 120;
	 JLabel tool = new JLabel("How would you like to Play?"); 
	 tool.setBounds(60, 120, 253, 30);
	 current.add(name);
	 current.add(userName);
	 current.add(tool);
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
	 JLabel tool = new JLabel(" How would player1 Play?"); 
	 tool.setBounds(60, 200, 253, 30);
	 current.add(name);
	 current.add(userName);
	 current.add(name2);
	 current.add(userName2);
	 current.add(tool);
}


private class PlayerMenuController implements ActionListener{

	private Controller controller;
	public PlayerMenuController (Controller controller){
		this.controller= controller;
	}
		@Override
		public void actionPerformed(ActionEvent e) {
			 twoPLayers=false;
			if (e.getSource() == onePlayer) {
				twoPLayers =false;
			}
			if (e.getSource() == twoPlayer) {
				twoPLayers = true;	
			}
			this.controller.popMessage(ChoosePlayerMenu.this,setPlayerInfoPanel(twoPLayers));	
	}	
}

	private class infoMenuController implements ActionListener {

		private Controller controller;

		public infoMenuController(Controller controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == levelsList) {
				dataLevel = (String) levelsList.getItemAt(levelsList.getSelectedIndex());
			}
			if (e.getSource() == toolsList) {
				tool = (String) toolsList.getItemAt(toolsList.getSelectedIndex());
			}
		}

	}
}

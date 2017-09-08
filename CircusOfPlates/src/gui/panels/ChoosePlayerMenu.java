package gui.panels;

import mvc.Controller;
import mvc.IViewer;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import static utilities.Properties.*;

public class ChoosePlayerMenu extends JPanel implements IViewer {

	private static final long serialVersionUID = 1L;

	private BufferedImage backGroundImage;
	private Map<String, JButton> buttons;
	private ActionListener menuController;
	private ActionListener infoController;
	private JTextField userName;
	private JTextField userName2;
	private JComboBox<String> levelsList;
	private boolean twoPLayers;
	private boolean savedGame;
	private String dataLevel;
	private static int topLeftBoxXAlignment;
	private static int topLeftBoxYAlignment;
	private int y;

	// remove static dimensions in all class!!!

	public ChoosePlayerMenu() {
		try {
			backGroundImage = ImageIO.read(ResourceLoader.loadStream(NEW_GAME));
		} catch (IOException e) {
			throw new RuntimeException("Image Not Found!");
		}
		savedGame = false;
		this.setSize(frameWidth(), frameHeight());
		this.setLayout(null);
		this.setFocusable(true);
		buttons = new LinkedHashMap<>();
		topLeftBoxXAlignment = frameWidth() / 4;
		topLeftBoxYAlignment = frameWidth() / 4;
		setButtons();
		repaint();
	}

	public void setController(Controller controller) {
		this.menuController = new PlayerMenuController(controller);
		buttons.get(ONEPLAYER_BUTTON).addActionListener(menuController);
		buttons.get(TWOPLAYERS_BUTTON).addActionListener(menuController);
	}

	public void setSaved(boolean current) {
		savedGame = current;
	}

	@Override
	public void paintComponent(Graphics g) {
		g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
	}

	private void setButtons() {

		javax.swing.Box box = javax.swing.Box.createHorizontalBox();
		ImageIcon onePlayerIcon = null;
		ImageIcon twoPlayerIcon = null;
		try {
			onePlayerIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(ONEPLAYER_BUTTON)));
			buttons.put(ONEPLAYER_BUTTON, new JButton(onePlayerIcon));
			buttons.get(ONEPLAYER_BUTTON).setName(ONEPLAYER_BUTTON);
			twoPlayerIcon = new ImageIcon(ImageIO.read(ResourceLoader.loadStream(TWOPLAYERS_BUTTON)));
			buttons.put(TWOPLAYERS_BUTTON, new JButton(twoPlayerIcon));
			buttons.get(TWOPLAYERS_BUTTON).setName(TWOPLAYERS_BUTTON);

		} catch (IOException e) {
			System.out.println("Button Image not found");
		}
		for (JButton button : buttons.values()) {
			button.setSize(onePlayerIcon.getIconWidth(), onePlayerIcon.getIconHeight());
			button.setOpaque(false);
			button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			box.add(button);
			box.add(Box.createHorizontalStrut(onePlayerIcon.getIconWidth() / 3));
		}
		box.setBounds(topLeftBoxXAlignment, topLeftBoxYAlignment, 4 * twoPlayerIcon.getIconWidth(),
				twoPlayerIcon.getIconHeight());
		this.add(box);
	}

	private JPanel setPlayerInfoPanel(boolean twoPlayers) {
		JPanel playerInfo = new JPanel();
		playerInfo.setLayout(null);
		playerInfo.setPreferredSize(new Dimension(400, 400));
		if (twoPlayers)
			setInfoTwoPlayer(playerInfo);
		else
			setInfoOnePlayer(playerInfo);

		if (!savedGame) {
			JLabel difficultyLevel = new JLabel("Difficulty Level");
			difficultyLevel.setBounds(60, y + 40, 250, 30);
			final DefaultComboBoxModel<String> levels = new DefaultComboBoxModel<>();
			levels.addElement("Easy");
			levels.addElement("Medium");
			levels.addElement("Hard");
			levelsList = new JComboBox<>(levels);
			levelsList.setSelectedIndex(-1);
			levelsList.setBounds(230, y + 40, 80, 30);
			this.infoController = new InfoMenuController();
			levelsList.addActionListener(infoController);
			playerInfo.add(difficultyLevel);
			playerInfo.add(levelsList);
		}
		return playerInfo;
	}

	public LinkedHashMap<String, Object> getConfigurations() {
		LinkedHashMap<String, Object> settings = new LinkedHashMap<>();
		ArrayList<String> names = new ArrayList<>();
		names.add(userName.getText());
		if (twoPLayers)
			names.add(userName2.getText());

		settings.put("twoPlayers", twoPLayers);
		settings.put("level", dataLevel);
		settings.put("names", names);
		return settings;
	}

	private void setInfoOnePlayer(JPanel current) {
		JLabel name = new JLabel("Enter your name");
		name.setBounds(60, 40, 250, 30);
		userName = new JTextField();
		userName.setBounds(60, 80, 250, 30);
		y = 120;
		current.add(name);
		current.add(userName);
	}

	private void setInfoTwoPlayer(JPanel current) {
		JLabel name = new JLabel("Player1, Enter your name");
		name.setBounds(60, 40, 250, 30);
		userName = new JTextField();
		userName.setBounds(60, 80, 250, 30);
		JLabel name2 = new JLabel("Player2, Enter your name");
		name2.setBounds(60, 120, 250, 30);
		userName2 = new JTextField();
		userName2.setBounds(60, 160, 250, 30);
		y = 200;
		current.add(name);
		current.add(userName);
		current.add(name2);
		current.add(userName2);
	}

	private class PlayerMenuController implements ActionListener {
		private Controller controller;

		public PlayerMenuController(Controller controller) {
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton buttonPressed = (JButton) e.getSource();
			twoPLayers = false;
			if (buttonPressed.getName().equals(ONEPLAYER_BUTTON))
				twoPLayers = false;
			if (buttonPressed.getName().equals(TWOPLAYERS_BUTTON))
				twoPLayers = true;

			this.controller.popMessage(ChoosePlayerMenu.this, setPlayerInfoPanel(twoPLayers), savedGame);
		}
	}

	private class InfoMenuController implements ActionListener {
		protected InfoMenuController() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == levelsList)
				dataLevel = levelsList.getItemAt(levelsList.getSelectedIndex());
		}
	}
}

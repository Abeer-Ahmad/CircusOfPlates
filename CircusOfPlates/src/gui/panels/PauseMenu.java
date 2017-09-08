package gui.panels;

import mvc.Controller;
import mvc.IViewer;
import utilities.ResourceLoader;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static utilities.Properties.*;

public class PauseMenu extends JPanel implements ActionListener, IViewer {

    private static final long serialVersionUID = 1L;

    private BufferedImage backGroundImage;
    private JButton continueGame;
    private JButton mainMenu;
    private JButton saveGame;
    private Controller controller;

    public PauseMenu() {
        this.setSize(frameWidth(), frameHeight());
        try {
            backGroundImage = ImageIO.read(ResourceLoader.loadStream(NEW_GAME));
        } catch (IOException e) {
            throw new RuntimeException("Image Not Found!");
        }
        this.setBorder(new EmptyBorder(15, 15, 15, 15)); // remove static dimensions!!!
        this.setLayout(null);
        this.setFocusable(true);
        setButtons();
        repaint();
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == continueGame)
            controller.continueGame();
        if (e.getSource() == saveGame)
            controller.save();
        if (e.getSource() == mainMenu)
            controller.changeDisplay("mainMenu");
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(backGroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void setButtons() { // remove static dimensions!!!
        continueGame = new JButton("Continue");
        continueGame.setBounds(250, 100, 500, 100);
        continueGame.addActionListener(this);
        this.add(continueGame);

        saveGame = new JButton("Save");
        saveGame.setBounds(250, 300, 500, 100);
        saveGame.addActionListener(this);
        this.add(saveGame);

        mainMenu = new JButton("Main Menu");
        mainMenu.setBounds(250, 500, 500, 100);
        mainMenu.addActionListener(this);
        this.add(mainMenu);
    }
}

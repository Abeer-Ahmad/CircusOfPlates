package mvc;

import game.player.Player;
import game.shapes.Shape;
import gui.MainFrame;
import gui.panels.*;
import plateGenerator.Belt;

import javax.swing.*;
import java.util.*;

import static utilities.Properties.frameHeight;
import static utilities.Properties.frameWidth;

public class Viewer implements Observer {

    private Controller controller;
    private LinkedHashMap<String, JPanel> menuPanels;
    private GameGrid gameGrid;
    private MainFrame mainFrame;

    public Viewer() {
        menuPanels = new LinkedHashMap<>();
        menuPanels.put("mainMenu", new MainMenu());

        /**can be done using swing utility thread**/
        menuPanels.put("playerMenu", new ChoosePlayerMenu());
        menuPanels.put("pauseMenu", new PauseMenu());
        mainFrame = new MainFrame(menuPanels.get("mainMenu"));
        mainFrame.setSize(frameWidth(), frameHeight());
        mainFrame.setVisible(true);
    }

    public void setController(Controller controller) {
        this.controller = controller;
        Iterator itr = menuPanels.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry panel = (Map.Entry) itr.next();
            ((IViewer) panel.getValue()).setController(controller);
        }
    }

    public void goToGame() {
        mainFrame.changeScene(gameGrid);
    }

    @Override
    public void update(Observable model, Object valueChanged) {
        if (valueChanged instanceof Boolean) {
            boolean twoPlayers = (boolean) valueChanged;
            Model gameModel = (Model) model;
            gameGrid = new GameGrid(twoPlayers, gameModel.getPlayers());
            gameGrid.setController(controller);
            goToGame();
        } else if (valueChanged instanceof ArrayList) {
            if (((ArrayList) valueChanged).size() > 0) {
                if (((ArrayList) valueChanged).get(0) instanceof Shape)
                    gameGrid.updateShapes((ArrayList<Shape>) valueChanged);
                if (((ArrayList) valueChanged).get(0) instanceof Player)
                    gameGrid.updatePlayers((ArrayList<Player>) valueChanged);
                if (((ArrayList) valueChanged).get(0) instanceof Belt)
                    gameGrid.updateBelts((ArrayList<Belt>) valueChanged);
            }
        } else if (valueChanged instanceof Player) {
            System.out.println("winner notified");
            WinnerView winView = new WinnerView((Player) valueChanged);
            winView.setController(controller);
            mainFrame.changeScene(winView);
        }
        gameGrid.validate();
        gameGrid.repaint();
    }

    public void readInfo() {
        setCurrentPanel("playerMenu");
        ((ChoosePlayerMenu) menuPanels.get("playerMenu")).setSaved(true);
    }

    public void setCurrentPanel(String namePanel) {
        mainFrame.changeScene(menuPanels.get(namePanel));
    }

    public void exitGame() {
        mainFrame.close();
    }

    public void popMessage(JPanel container, JPanel message, boolean savedGame) {
        if (JOptionPane.showConfirmDialog(container, message, "New Game", JOptionPane.PLAIN_MESSAGE) == JOptionPane.OK_OPTION) {
            if (savedGame) {
                ((ChoosePlayerMenu) menuPanels.get("playerMenu")).setSaved(false);
                ArrayList<String> names = (ArrayList<String>) ((ChoosePlayerMenu) container).getConfigurations().get("names");
                String game = "";
                for (String name : names)
                    game += name;
                controller.load(game);
            } else
                this.controller.startGame(((ChoosePlayerMenu) container).getConfigurations());
        }
    }

    public void confirmSaving() {
        JOptionPane.showMessageDialog(null, "Your game has been saved!");
    }
}

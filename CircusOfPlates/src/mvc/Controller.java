package mvc;


import game.player.PlayerUI;

import javax.swing.*;
import java.util.LinkedHashMap;

public class Controller {


    protected Model model;
    protected Viewer viewer;

    public Controller(Model model, Viewer viewer) {
        this.model = model;
        this.viewer = viewer;
        this.viewer.setController(this);
    }

    public void movePlayer(PlayerUI playerUI, int step) {
        model.movePlayer(playerUI.getPlayer(), step);
    }

    public void pauseGame() {
        model.pauseGame();
        viewer.setCurrentPanel("pauseMenu");
    }

    public void continueGame() {
        model.continueGame();
        viewer.goToGame();
    }

    public void save() {
        model.save();
        viewer.confirmSaving();
        viewer.setCurrentPanel("mainMenu");
    }

    public void load() {
        // viewer.setCurrentPanel("playerMenu");
        model.load();
    }

    public void popMessage(JPanel container, JPanel message) {
        viewer.popMessage(container, message);
    }

    public void changeDisplay(String namePanel) {
        viewer.setCurrentPanel(namePanel);
    }

    public void startGame(LinkedHashMap<String, Object> settings) {
        model.startGame(settings, true);
    }

    public void exitGame() {
        viewer.exitGame();
    }

    public void newLevel() {
        model.newLevel();
        viewer.goToGame();
    }
}

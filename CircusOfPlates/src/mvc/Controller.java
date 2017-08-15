package mvc;



import java.util.LinkedHashMap;

import javax.swing.JPanel;

import game.player.PlayerUI;

public class Controller {

	
	protected Model gameItems;
	protected Viewer viewer;
	
	public  Controller(Model gameItems, Viewer gameGUI){
		 this.gameItems = gameItems;
		 this.viewer = gameGUI;	 
		 this.viewer.setController(this);
	}
	
	public void movePlayer(PlayerUI playerUI, int step) {
		gameItems.movePlayer(playerUI.getPlayer(), step);
	}

	public void pauseGame() {
		gameItems.pauseGame();
		viewer.setCurrentPanel("pauseMenu");
	}

	public void continueGame() {
		gameItems.continueGame();
		viewer.goToGame();
	}

	public void save() {
		
	}

   public void popMessage (JPanel container, JPanel message){  
	   viewer.popMessage(container,message);
   }

	public void changeDisplay(String namePanel) {
		viewer.setCurrentPanel(namePanel);
	}

	public void startGame(LinkedHashMap<String, Object> settings) {
		gameItems.startGame(settings);	
	}
	
	public void exitGame() {
		viewer.exitGame();	
	}

	public void newLevel() {
		gameItems.newLevel();
	    viewer.goToGame();		
	}
	
}

package gui.handlers;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import game.player.PlayerUI;
import mvc.Controller;

public class KeyBoardHandler implements KeyListener {

	int turn = 0;
	Controller gridController;
    PlayerUI playerUI;
    public KeyBoardHandler (Controller controller){
    	gridController= controller;
    }
	public KeyBoardHandler(Controller controller,PlayerUI player) {
		gridController= controller;
		playerUI=player;
		System.out.println("const"+playerUI.getPlayer().getName());
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		int pressed = arg0.getKeyCode();
		if (pressed == KeyEvent.VK_RIGHT) {
			/*System.out.println("keyMoving playerPosx"+playerUI.getX()+"y"+playerUI.getY());
			System.out.println("uikey"+playerUI.getPlayer().getName());
			gridController.movePlayer(playerUI, 20);*/	
		}
		if (pressed == KeyEvent.VK_LEFT) {
			//gridController.movePlayer(playerUI, -20);
		}
		if (pressed==KeyEvent.VK_ESCAPE){
			gridController.pauseGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}

package gui;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import mvc.Controller;

public class MouseHandler implements MouseListener, MouseMotionListener {
	
	private Controller gridController;
    private PlayerUI playerUI;
    int startX;
    
    public MouseHandler(){
    	
    }
	public MouseHandler(Controller controller,PlayerUI player) {
		gridController= controller;
		this.playerUI = player;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		startX =e.getX();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {	
		int currentX = e.getX();
		//System.out.println("mouseMoving startX"+startX+"playerPosx"+playerUI.getX()+"y"+playerUI.getY());
		System.out.println("mousekey"+playerUI.getPlayer().getName());
		gridController.movePlayer(playerUI, currentX-startX);
		startX=currentX;
		
			//gridController.movePlayer(1, e.getX()-startX);gr
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

}

package mvc;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import collections.Shapes;
import gui.ChoosePlayerMenu;
import gui.GameGrid;
import gui.MainFrame;
import gui.MainMenu;
import gui.PauseMenu;
import gui.ViewerIF;
import plateGenerator.Belt;
import shapes.Shape;
import system.Player;

public class Viewer implements Observer{

	private Controller  controller;
	private LinkedHashMap<String,JPanel> menuPanels;
	private GameGrid gameGrid;
	private MainFrame mainFrame ;
	private int xFrame;
	private int yFrame;
	 
	public Viewer ( ){
		menuPanels = new LinkedHashMap<String,JPanel>();
		xFrame= 1500;
		yFrame= 1000;
		menuPanels.put("mainMenu",new MainMenu(xFrame, yFrame));
		menuPanels.put("playerMenu",new ChoosePlayerMenu(xFrame,yFrame));
		menuPanels.put("pauseMenu",new PauseMenu(xFrame,yFrame)); 
		 mainFrame = new MainFrame(menuPanels.get("mainMenu"));
		 mainFrame.setTitle("Circus Of Plates");
		 mainFrame.setVisible(true);
	}
	
	public void  addJPanel (String panelName,JPanel newPanel){
		menuPanels.put(panelName,newPanel);
	}
	public void setController (Controller controller){
		this.controller = controller;
		
		 Iterator itr = menuPanels.entrySet().iterator();
         while (itr.hasNext()) {
             Map.Entry panel = (Map.Entry) itr.next();
             ((ViewerIF)panel.getValue()).setController(controller);    
         }
	}
	
	public void goToGame(){
		mainFrame.changeScene(gameGrid);
	}
	@Override
	public void update(Observable model, Object valueChanged) {
		
		if (valueChanged instanceof Boolean){
			boolean twoPlayers = (boolean) valueChanged;
			Model gameModel = (Model) model;
			gameGrid=new GameGrid(twoPlayers,gameModel.getPlayers(),gameModel.getFirstPlayerTool());
			gameGrid.setController(controller);
			goToGame();			
		}
		else if (valueChanged instanceof ArrayList){
			if (((ArrayList) valueChanged).size()>0){
		      if(((ArrayList) valueChanged).get(0)instanceof Shape){
		    	  gameGrid.updateShapes((ArrayList<Shape>) valueChanged); 
		      }
		      if (((ArrayList) valueChanged).get(0)instanceof Player){
		    	  gameGrid.updatePlayers((ArrayList<Player>) valueChanged);
		      }
		      if  (((ArrayList) valueChanged).get(0)instanceof Belt){
		    	  gameGrid.updateBelts((ArrayList<Belt>) valueChanged);
		    	 
		      }
			} 
		} else if (valueChanged instanceof Player) {
			
		}
		gameGrid.validate();
		gameGrid.repaint();
	}

	public void setCurrentPanel(String namePanel) {
		mainFrame.changeScene(menuPanels.get(namePanel));
	}

	public void exitGame() {
		mainFrame.close();	
	}

	public void popMessage(JPanel container, JPanel message) {
		
		if (JOptionPane.showConfirmDialog(container,message,"New Game",JOptionPane.PLAIN_MESSAGE )==JOptionPane.OK_OPTION){
			this.controller.startGame(((ChoosePlayerMenu) container).getConfigurations());
		}		
	}	
}

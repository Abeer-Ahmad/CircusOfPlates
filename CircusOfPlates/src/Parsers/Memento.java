package Parsers;
import java.io.Serializable;
import java.util.ArrayList;

import system.Player;
import shapes.Shape;

public class Memento  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5075456036659026153L;
	private ArrayList<Player> players;
	private ArrayList<Shape>  shapes;
	private String level;
	private boolean twoPlayers;
	private String tool;
	public Memento(ArrayList<Player> players,ArrayList<Shape> shapes, String level,boolean twoPlayers, String tool){
		this.players=players;
		this.shapes=shapes;
		this.level=level;
		this.twoPlayers=twoPlayers;
		this.tool=tool;
	}
	
	public ArrayList<Player>  getPlayers(){
		return players;	
	}
	
	public ArrayList<Shape>  getShapes(){
		return shapes;	
	}
	
	public String getLevel(){
		return level;
	}
	public String getTool(){
		return tool;
	}
	public boolean getTwoPlayers(){
		return twoPlayers;
	}
}

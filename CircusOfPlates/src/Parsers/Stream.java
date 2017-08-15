package Parsers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.player.Player;
import game.shapes.Shape;

public class Stream {
 
	public void save( ArrayList<Player> players,ArrayList<Shape> shapes,
			String level,boolean twoPlayers, String tool,String game){
		try {
			Path gamePath =Paths.get(System.getProperty("user.home")+File.separator+game+".txt");
			FileOutputStream fileOut = new FileOutputStream(gamePath.toFile());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			Memento newGame = new Memento(players,shapes,level,twoPlayers,tool);
			out.writeObject(newGame);
			out.close();
			fileOut.close();
					} catch (IOException e) {
			
			throw new RuntimeException("Error in Saving");
		}
		       // 
	}
	public Memento load (String game){
		try {
			Path gamePath =Paths.get(System.getProperty("user.home")+File.separator+game+".txt");
			FileInputStream fileIn = new FileInputStream(gamePath.toFile());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Memento loadedGame = (Memento)in.readObject();
			in.close();
			fileIn.close();
			return loadedGame;
					} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error in Loading");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found");
					}
	}
	
 
}

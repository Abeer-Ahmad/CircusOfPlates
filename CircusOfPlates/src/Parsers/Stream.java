package Parsers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import shapes.Shape;
import system.Player;

public class Stream {
 
	private ArrayList<Player> players;
	private ArrayList<Shape> shapes;
	public void save(ArrayList<Shape> shapes, ArrayList<Player> players, Path gamePath){
		try {
			
			FileOutputStream fileOut = new FileOutputStream(gamePath.toFile());
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			for (int i=0;i<players.size();i++){
				out.writeObject(players.get(i));
			}
			out.writeInt(shapes.size());
			for (int i=0;i<shapes.size();i++){
				out.writeObject(shapes.get(i));
			}
			out.close();
			fileOut.close();
					} catch (IOException e) {
			
			throw new RuntimeException("Error in Saving");
		}
		       // 
	}
	public void load (Path gamePath){
		try {
			FileInputStream fileIn = new FileInputStream(gamePath.toFile());
			ObjectInputStream in = new ObjectInputStream(fileIn);
			players = new ArrayList<Player>();
			shapes = new ArrayList<Shape>();
			for (int i=0;i<2;i++){
			players.add((Player)in.readObject());
			}
			int shapesSize = in.readInt();
			for (int i=0;i<shapesSize;i++){
				shapes.add((Shape)in.readObject());
			}
			in.close();
			fileIn.close();
					} catch (IOException e) {
			
			throw new RuntimeException("Error in Loading");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Class not found");
					}
	}
	
  public 	ArrayList<Player> getPLayers(){
	  return players;
  }
  public 	ArrayList<Shape> getShapes(){
	  return shapes;
  }
}

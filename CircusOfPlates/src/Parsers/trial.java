package Parsers;

import java.awt.Color;

import java.beans.XMLEncoder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import plateGenerator.Belt;
import plateGenerator.LeftBelt;
import shapes.CorruptShape;
import shapes.Shape;
import system.Player;

public class trial {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
      
      ArrayList<Shape> shapesWrite = new ArrayList<Shape>();
      ArrayList<Shape> shapesRead = new ArrayList<Shape>();
      ArrayList<Player> playerWrite = new ArrayList<Player>();
      ArrayList<Player> playerRead = new ArrayList<Player>();
      /*shapesWrite.add(new Oval (18,19,100,Color.white));
      shapesWrite.add(new Box (18,19,100,Color.white));
      playerWrite.add (new Player(8,"player1.jpg"));*/
      playerWrite.add (new Player(19,30,"shrouk"));
      shapesWrite.add(new CorruptShape(40,30,100));
	  
	 Path zewPath =Paths.get(System.getProperty("user.home")+File.separator+"Desktop"+File.separator+"trial.txt");
	 /*FileOutputStream fileOut = new FileOutputStream(zewPath.toFile());
	 FileInputStream fileIn = new FileInputStream(zewPath.toFile());
	 
	        ObjectOutputStream out = new ObjectOutputStream(fileOut);
	        ObjectInputStream in = new ObjectInputStream (fileIn);*/
	         Stream tryz = new Stream();
	         tryz.save(shapesWrite, playerWrite, zewPath);
	         tryz.load(zewPath);
	         playerRead = tryz.getPLayers();
	         shapesRead = tryz.getShapes();
	         
	         System.out.println(playerRead.get(0).getName());
	         System.out.println(shapesRead.get(0).getX());
	         
	     
	}

}

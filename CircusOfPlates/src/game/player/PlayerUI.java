package game.player;



import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import game.shapes.Shape;

public class PlayerUI  extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6003066744683293690L;
	private static final int SHIFT = 50;
	private BufferedImage image;
	private Player player;
	
	public PlayerUI(Player player, int sign, int frameWidth, int frameHeight, String path){
		try {	
		image = ImageIO.read(new File(path));
		int xCenter = frameWidth / 2 + sign * SHIFT;
		int yCenter = frameHeight - (image.getHeight() / 2);
		player.setDimensions(xCenter, yCenter, image.getHeight());
	  } catch (IOException e) {
		throw new RuntimeException("Player Image not found");
	  }
		this.player = player;
		this.setBounds(player.getxPostion(), player.getyPostion(), image.getWidth(), image.getHeight());
	}
	
	public void updatePLayerModel(Player updatedPlayer){   
		this.player= updatedPlayer;
	}
	
	public Player getPlayer (){
		return player;
	}
	
   
	public void draw(Graphics2D g) {
		int topLeftXCoor = player.getxPostion() - (image.getWidth()/2);
		int topLeftYCoor = player.getyPostion() - (image.getHeight()/2);
		g.drawImage(image, topLeftXCoor, topLeftYCoor , null);	
		this.setBounds(topLeftXCoor, topLeftYCoor, image.getWidth(), image.getHeight());
		
		for (int i = 0; i < player.getRightStack().size(); i++) {
			Shape shape = player.getRightStack().get(i);
			synchronized (shape) {
				shape.draw(g);
			}
		}
		
		for (int i = 0; i < player.getLeftStack().size(); i++) {
			Shape shape = player.getLeftStack().get(i);
			synchronized (shape) {
				shape.draw(g);
			}
		}
	}
}
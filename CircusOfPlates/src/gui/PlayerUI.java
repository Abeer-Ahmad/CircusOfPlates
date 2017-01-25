package gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import shapes.Shape;
import system.Player;

public class PlayerUI  extends JComponent{

	private BufferedImage image;
	private Image img;
	private Player player ;
	
	public PlayerUI(Player player, String path){
		try{
			
		image = ImageIO.read(new File(path));
		img = makeColorTransparent(image, Color.WHITE);
		player.setHeight(image.getHeight());
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
	private Image makeColorTransparent(BufferedImage im, final Color color) {
		ImageFilter filter = new RGBImageFilter() {
			public int markerRGB = color.getRGB() | 0xFF000000;

			@Override
			public int filterRGB(int x, int y, int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					return 0x00FFFFFF & rgb;
				} else {
					return rgb;
				}
			}
		};
		ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}
   
	public void draw(Graphics2D g) {
		g.drawImage(img, player.getxPostion(), player.getyPostion(), null);
		int xtopLeft= player.getxPostion()-(image.getWidth()/2);
		
		this.setBounds(player.getxPostion(), player.getyPostion(), image.getWidth(), image.getHeight());
		//System.out.println("playerUI x"+this.getX()+"y"+this.getY());
		for (Shape shape : player.getRightStack()) {
			synchronized (shape) {
				shape.draw(g);
			}
		}
		for (Shape shape : player.getLeftStack()) {
			synchronized (shape) {
				shape.draw(g);
			}
		}
		
	}
}

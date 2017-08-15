package plateGenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import game.shapes.Shape;

public abstract class Belt {

	protected Color color;
	protected int length;
	protected PlateGenerator randomGenerator;
	protected BasicStroke thickness;
	protected int x;
	protected int y;

	public Belt() {
		color = Color.BLACK;
		thickness = new BasicStroke(5);
		randomGenerator = new PlateGenerator();
	}

	public abstract Shape addShape();

	public abstract void drawBelt(Graphics2D g);
	
	public PlateGenerator getRandomGenerator() {
		return randomGenerator;
	}
}

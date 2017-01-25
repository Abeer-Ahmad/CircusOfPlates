package plateGenerator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

import shapes.Shape;
import system.RandomGenerator;

public abstract class Belt {

	protected Color color;
	protected int length;
	protected RandomGenerator randomGenerator;
	protected BasicStroke thickness;
	protected int x;
	protected int y;

	public Belt() {
		color = Color.BLACK;
		thickness = new BasicStroke(5);
		randomGenerator = new RandomGenerator();
	}

	public abstract Shape addShape();

	public abstract void drawBelt(Graphics2D g);
	
	public RandomGenerator getRandomGenerator() {
		return randomGenerator;
	}
}

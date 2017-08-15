package game.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class LaserBeam extends Shape {

	private Color color;
	private int width;

	public LaserBeam(int width) {
		super.xPostion = 0;
		super.yPostion = 140;
		this.color = Color.red;
		this.width = width;
	}

	@Override
	public void draw(Graphics2D g) {
		if (color.equals(Color.red)) {
			color = Color.blue;
		} else {
			color = Color.red;
		}
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		g.setStroke(new BasicStroke(5));
		g.drawLine(xPostion, yPostion, xPostion + width, yPostion);
	}

	@Override
	public Color getColor() {
		return color;
	}

}

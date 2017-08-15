package game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import game.shapes.states.OnBelt;

public class CorruptShape extends Shape{

	private Color color;
	private int width;
	private int height;
	
	public CorruptShape(final int x, final int y, final int beltLengt) {
		super();
		this.color = Color.BLACK;
		this.width = 80;
		this.height = 20;
		super.xPostion = x;
		super.yPostion = y;
		super.beltLength = beltLength;
	}
	
	public void setObject (final int x, final int y, final int beltLengt){
        this.setState(new OnBelt());
		this.width = 80;
		this.height = 20;
		super.xPostion = x;
		super.yPostion = y;
		super.beltLength = beltLengt;
	}
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return color;
	}

	@Override
	public void draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
		int topLeftX = super.xPostion - (width / 2);
		int topLeftY = super.yPostion - (height / 2);
		g.fillRect(topLeftX, topLeftY, width, height);
		
	}

	
}

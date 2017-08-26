package game.shapes;

import java.awt.*;
import java.io.Serializable;

import game.shapes.states.OnBelt;
import game.shapes.states.State;

import static utilities.Properties.*;

public abstract class Shape implements Serializable{

	private static final long serialVersionUID = 8799923820081680740L;

	protected int beltLength;
	protected int xCenter;
	protected int yCenter;
	protected int width;
	protected int height;
	protected Color color;
	private State state;
	private int moveSpeed;


	public Shape() {
		state = new OnBelt();
		width = SHAPE_WIDTH;
		height = SHAPE_HEIGHT;
	}
	
	public void setCenter(int x, int y) {
		this.xCenter = x;
		this.yCenter = y;
	}

	public void setSpeed(int randomSpeed) {
		this.moveSpeed = randomSpeed;
	}

	public int getSpeed() {
		return moveSpeed;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getState () {
		return state;
	}

	public int getBeltLength() {
		return beltLength;
	} // remove!!

	public int getX() {
		return xCenter;
	}

	public int getY() {
		return yCenter;
	}
	
	public Color getColor() { return color; }

	public void update() {
		state.updateSate(this);
		setCenter(state.getUpdatedX(), state.getUpdatedY());
	}

	protected void setShape(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(color);
	}

	public abstract void draw(Graphics2D g);
}

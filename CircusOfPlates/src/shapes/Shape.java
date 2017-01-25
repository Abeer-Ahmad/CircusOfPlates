package shapes;

import java.awt.Color;
import java.awt.Graphics2D;


public abstract class Shape {

	protected int beltLength;
	public int moveSpeed;
	private State state;
	protected int xPostion;
	protected int yPostion;

	public Shape() { 
		state = new OnBelt();
	}

	public void update() {
		state.updateSate(this);
		setCenter(state.getUpdatedX(), state.getUpdatedY());
	}
	
	public void setCenter(int x, int y) {
		this.xPostion = x;
		this.yPostion = y;
	}

	public void setSpeed(int randomSpeed) {
		this.moveSpeed = randomSpeed;
	}

	public void setState(State state) {
		this.state = state;
	}

	public void setState(ShapeStates state) {
		this.state.shapeState = state;
	}
	
	public int getBeltLength() {
		return beltLength;
	}
	
	public ShapeStates getShapeState() {
		return state.getState();
	}

	public int getX() {
		return xPostion;
	}

	public int getY() {
		return yPostion;
	}

	public int getSpeed() {
		return moveSpeed;
	}
	
	public abstract Color getColor();

	public abstract void draw(Graphics2D g);
}

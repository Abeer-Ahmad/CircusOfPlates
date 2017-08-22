package game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;

import game.shapes.states.OnBelt;
import game.shapes.states.ShapeStates;
import game.shapes.states.State;


public abstract class Shape implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8799923820081680740L;
	protected int beltLength;
	protected int xPostion;  //center of shape
	protected int yPostion;  //center of shape
	private int moveSpeed;
	private State state;

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
		this.state.setState(state);
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

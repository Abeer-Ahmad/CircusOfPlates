package game.shapes.states;

import java.io.Serializable;

import game.shapes.Shape;

public abstract class State implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 823924900345247275L;
    protected ShapeStates shapeState;

	public abstract int getUpdatedX();

	public abstract int getUpdatedY();
	
	
	public ShapeStates getState() {
		return this.shapeState;
	}
	
	public void setState(ShapeStates state) {
		this.shapeState = state;
	}

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength);
	
	public abstract void updateSate(Shape shape);

}

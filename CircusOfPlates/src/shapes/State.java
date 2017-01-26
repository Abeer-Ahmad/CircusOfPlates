package shapes;

import java.io.Serializable;

public abstract class State implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 823924900345247275L;
	protected ShapeStates shapeState;

	public abstract int getUpdatedX();

	public abstract int getUpdatedY();
	
	public abstract ShapeStates getState();

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength);
	
	public abstract void updateSate(Shape shape);

}

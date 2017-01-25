package shapes;

public abstract class State {
	
	protected ShapeStates shapeState;

	public abstract int getUpdatedX();

	public abstract int getUpdatedY();
	
	public abstract ShapeStates getState();

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength);
	
	public abstract void updateSate(Shape shape);

}

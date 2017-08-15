package game.shapes.states;

import game.shapes.Shape;

public class OnBelt extends State {
	
	private int x; // x_center
	private int y; // y_center
	
	public OnBelt() {
		this.shapeState = ShapeStates.onBelt;
	}

	@Override
	public int getUpdatedX() {
		return x;
	}

	@Override
	public int getUpdatedY() {
		return y;
	}
	
	@Override
	public ShapeStates getState() {
		return shapeState;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		this.x = x + moveSpeed;
		this.y = y;
		if (moveSpeed > 0) {
			return this.x >= beltLength;
		} else {
			return this.x <= 1500 - beltLength;
		}
	}

	@Override
	public void updateSate(Shape shape) {
		boolean stateChanged = updateCoor(shape.getSpeed(), shape.getX(), shape.getY(), shape.getBeltLength());
		shape.setCenter(x, y);
		if (stateChanged) {
			shape.setState(new Falling(x, y));
		}
	}
}

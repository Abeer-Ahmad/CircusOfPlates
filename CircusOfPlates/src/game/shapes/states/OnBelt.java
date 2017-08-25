package game.shapes.states;

import game.shapes.Shape;

public class OnBelt extends State {
	
	private int xCenter; // x_center
	private int yCenter; // y_center
	
	public OnBelt() {

	}

	@Override
	public int getUpdatedX() {
		return xCenter;
	}

	@Override
	public int getUpdatedY() {
		return yCenter;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		this.xCenter = x + moveSpeed;
		this.yCenter = y;
		if (moveSpeed > 0)
			return this.xCenter >= beltLength;
		else
			return this.xCenter <= 1500 - beltLength; // change 1500 to frame width
	}

	@Override
	public void updateSate(Shape shape) {
		boolean stateChanged = updateCoor(shape.getSpeed(), shape.getX(), shape.getY(), shape.getBeltLength());
		shape.setCenter(xCenter, yCenter);
		if (stateChanged)
			shape.setState(new Falling(xCenter, yCenter));
	}
}

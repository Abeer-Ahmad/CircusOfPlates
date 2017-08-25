package game.shapes.states;

import game.shapes.Shape;

public class Falling extends State {

	private int deltaT = 1;
	private int elaspedT = 0;
	private int g = 3;
	private int newXCenter;
	private int newYCenter;
	
	public Falling(int x, int y) {
		newXCenter = x;
		newYCenter = y;
	}

	@Override
	public int getUpdatedX() {
		return newXCenter;
	}

	@Override
	public int getUpdatedY() {
		return newYCenter;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		elaspedT = elaspedT + deltaT;
		newXCenter = x + (moveSpeed * deltaT);
		newYCenter = y + (int) (0.5 * g * deltaT * (2 * elaspedT + 1));
		return (newYCenter > 1000 || newXCenter > 1500 || newXCenter < 0); // change 1500 to frame width
	}

	@Override
	public void updateSate(Shape shape) {
		boolean stateChanged = updateCoor(shape.getSpeed(), shape.getX(), shape.getY(), shape.getBeltLength());
		shape.setCenter(newXCenter, newXCenter);
		if (stateChanged)
			shape.setState(new OnGround());
	}

}

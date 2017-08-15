package game.shapes.states;

import game.shapes.Shape;

public class Captured extends State{
	
	public Captured() {
		this.shapeState = ShapeStates.captured;
	}

	@Override
	public int getUpdatedX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getUpdatedY() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public ShapeStates getState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateSate(Shape shape) {
		// TODO Auto-generated method stub
		
	}
}

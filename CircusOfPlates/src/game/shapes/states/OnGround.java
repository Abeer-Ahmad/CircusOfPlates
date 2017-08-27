package game.shapes.states;

import game.shapes.Shape;

public class OnGround extends State {

	public OnGround() {
		state = "onGound";
	}

	@Override
	public int getUpdatedX() {
		return 0;
	}

	@Override
	public int getUpdatedY() {
		return 0;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		return false;
	}

	@Override
	public void updateSate(Shape shape) {
		
	}

}

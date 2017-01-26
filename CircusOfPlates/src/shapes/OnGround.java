package shapes;

public class OnGround extends State {

	public OnGround() {
		this.shapeState = ShapeStates.onGround;
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
	public ShapeStates getState() {
		return null;
	}
	
	@Override
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		return false;
	}

	@Override
	public void updateSate(Shape shape) {
		
	}

}

package shapes;

public class Falling extends State {

	int deltaT = 1;
	int elaspedT = 0;
	int g = 3;
	private int newXCenter;
	private int newYCenter;
	
	public Falling() {
		this.shapeState = ShapeStates.falling;
	}

	public int getUpdatedX() {
		return newXCenter;
	}

	public int getUpdatedY() {
		return newYCenter;
	}
	
	@Override
	public ShapeStates getState() {
		return shapeState;
	}
	
	public boolean updateCoor(int moveSpeed, int x, int y, int beltLength) {
		elaspedT = elaspedT + deltaT;
		newXCenter = x + (moveSpeed * deltaT);
		newYCenter = y + (int) (0.5 * g * deltaT * (2 * elaspedT + 1));
		return (newYCenter > 1000 || newXCenter > 1500 || newXCenter < 0);
	}

	@Override
	public void updateSate(Shape shape) {
		boolean stateChanged = updateCoor(shape.getSpeed(), shape.getX(), shape.getY(), shape.getBeltLength());
		shape.setCenter(this.getUpdatedX(), this.getUpdatedY());
		if (stateChanged) {
			shape.setState(new OnGround());
		}
	}

}

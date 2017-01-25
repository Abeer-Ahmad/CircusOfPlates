package interfaces;

public interface Moveable {

	public abstract int getUpdatedX();

	public abstract int getUpdatedY();

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength);

}
package game.shapes.states;

import java.io.Serializable;

import game.shapes.Shape;

public abstract class State implements Serializable{

	private static final long serialVersionUID = 823924900345247275L;

	public abstract int getUpdatedX();

	public abstract int getUpdatedY();

	public abstract void updateSate(Shape shape);

	public abstract boolean updateCoor(int moveSpeed, int x, int y, int beltLength); // read belt length from class

}

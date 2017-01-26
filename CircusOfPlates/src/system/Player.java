package system;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import shapes.Shape;
import shapes.ShapeStates;

public class Player {

	private static final int LIMIT = 3;

	private Stack<Shape> leftStack;
	private int leftStart;
	private Stack<Shape> rightStack;
	private int rightStart;
	private int score;
	private int xPostion;
	private int yPostion;
	private int height;
	private String name;

	public Player(int x, int y,String name) {

		this.name = name;
		this.xPostion = x;
		this.yPostion = y-200;
		this.leftStart = x;
		this.rightStart = x + 120;
		rightStack = new Stack<Shape>();
		leftStack = new Stack<Shape>();
	}

	private boolean manageCurrentHand(int xDistance, int yDistance, int start) {
		int end = start + 80;
		boolean xEpsilon = (xDistance >= start) && (xDistance <= end);
		boolean yEpsilon = (yDistance >= -30) && (yDistance <= 40);
		return (xEpsilon && yEpsilon);
	}

	public void manageStack(Collection<Shape> shapes) {
		if (shapes.equals(null))
			return;
		for (Shape shape : shapes) {
			if (shape.getShapeState() == ShapeStates.onGround || shape.getShapeState() == ShapeStates.captured) {
				continue;
			}
			int xDistance = shape.getX();
			int yDistance = yPostion+200 - getHandHeight(rightStack) - shape.getY();
			if (manageCurrentHand(xDistance, yDistance, rightStart)) {
				shape.setState(ShapeStates.captured);
				shape.setCenter(rightStart + 40, yPostion+200 - getHandHeight(rightStack) - 10);
				rightStack.push(shape);
				matchPlates(rightStack);
				continue;
			}
			yDistance = yPostion+200 - getHandHeight(leftStack) - shape.getY();
			if (manageCurrentHand(xDistance, yDistance, leftStart)) {
				shape.setState(ShapeStates.captured);
				shape.setCenter(leftStart + 40, yPostion+200 - getHandHeight(leftStack) - 10);
				leftStack.push(shape);
				matchPlates(leftStack);
			}
		}
	}

	private void matchPlates(Stack<Shape> currentHand) {
		int stackSize = currentHand.size();
		if (stackSize < 3)
			return;
		Color[] topColors = new Color[LIMIT];
		for (int i = 1; i < 4; i++) {
			topColors[i - 1] = currentHand.get(stackSize - i).getColor();
		}
		if (topColors[0].equals(topColors[1]) && topColors[0].equals(topColors[2])) {
			for (int i = 1; i < 4; i++) {
				currentHand.remove(stackSize - i);
			}
			updateScore();
		}
	}

	public void move(int step) {
		xPostion += step;
		xPostion += 1350;
		xPostion %= 1350;
		rightStart = xPostion + 120;
		leftStart = xPostion;
		for (Shape shape : rightStack) {
			shape.setCenter(rightStart + 40, shape.getY());
		}
		for (Shape shape : leftStack) {
			shape.setCenter(leftStart + 40, shape.getY());
		}
	}

	private void updateScore() {
		score += 5;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}
	
	public Stack<Shape> getLeftStack() {
		return leftStack;
	}

	public Stack<Shape> getRightStack() {
		return rightStack;
	}

	public int getMaxHeight() {
		int rightHeight = getHandHeight(rightStack);
		int leftHeight = getHandHeight(leftStack);
		int maxHeight = rightHeight > leftHeight ? rightHeight : leftHeight;
		return maxHeight;
	}

	private int getHandHeight(Stack<Shape> currentHand) {
		return height + currentHand.size() * 20;
	}

	public int getScore() {
		return score;
	}

	public int getxPostion() {
		return xPostion;
	}

	public int getyPostion() {
		return yPostion;
	}

	public String getName() {
		return name;
	}

	public void newLevel() {
		// TODO Auto-generated method stub
		this.score=0;
	}
}
package game.player;

import java.awt.*;
import java.util.Collection;
import java.util.Stack;

import game.shapes.Shape;
import game.shapes.states.ShapeStates;

public class Player {

	private static final int LIMIT = 3;

	private Stack<Shape> leftStack;
	private Stack<Shape> rightStack;
	private int leftHandCenter;
	private int rightHandCenter;
	private int score;
	private int xCenter; // center of clown
	private int yCenter; // center of clown
	private int height; // height of image
	private String name;

	public Player(String name) {
		this.name = name;
		rightStack = new Stack<>();
		leftStack = new Stack<>();
	}

	protected void setDimensions(int xCenter, int yCenter, int leftHandCenter, int rightHandCenter, int height) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.leftHandCenter = leftHandCenter;
		this.rightHandCenter = rightHandCenter;
		this.height = height;
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
			if (shape.getShapeState() == ShapeStates.onGround || shape.getShapeState() == ShapeStates.captured)
				continue;
			int xDistance = shape.getX();
			int yDistance = yCenter + 200 - getHandHeight(rightStack) - shape.getY();
			if (manageCurrentHand(xDistance, yDistance, rightHandCenter)) {
				shape.setState(ShapeStates.captured);
				shape.setCenter(rightHandCenter + 40, yCenter + 200 - getHandHeight(rightStack) - 10);
				rightStack.push(shape);
				matchPlates(rightStack);
				continue;
			}
			yDistance = yCenter + 200 - getHandHeight(leftStack) - shape.getY();
			if (manageCurrentHand(xDistance, yDistance, leftHandCenter)) {
				shape.setState(ShapeStates.captured);
				shape.setCenter(leftHandCenter + 40, yCenter + 200 - getHandHeight(leftStack) - 10);
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
		for (int i = 1; i < 4; i++)
			topColors[i - 1] = currentHand.get(stackSize - i).getColor();
		if (topColors[0].equals(topColors[1]) && topColors[0].equals(topColors[2])) {
			for (int i = 1; i < 4; i++)
				currentHand.remove(stackSize - i);
			updateScore();
		}
	}

	public void move(int step, int range) {
		xCenter += step;
		xCenter += range;
		xCenter %= range;
		rightHandCenter = xCenter + 120; // dynamic
		leftHandCenter = xCenter;
		for (Shape shape : rightStack)
			shape.setCenter(rightHandCenter + 40, shape.getY());
		for (Shape shape : leftStack)
			shape.setCenter(leftHandCenter + 40, shape.getY());
	}

	private void updateScore() { score += 5; }

	public Stack<Shape> getLeftStack() {
		return leftStack;
	}

	public Stack<Shape> getRightStack() {
		return rightStack;
	}

	public int getMaxHeight() {
		int rightHeight = getHandHeight(rightStack);
		int leftHeight = getHandHeight(leftStack);
		return rightHeight > leftHeight ? rightHeight : leftHeight;
	}

	private int getHandHeight(Stack<Shape> currentHand) {
		return height + currentHand.size() * 20;
	}

	public int getScore() {
		return score;
	}

	public int getxPostion() {
		return xCenter;
	}

	public int getyPostion() {
		return yCenter;
	}

	public String getName() {
		return name;
	}

	public void newLevel() {
		this.score = 0;
	}
}
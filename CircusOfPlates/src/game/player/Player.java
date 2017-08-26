package game.player;

import java.awt.*;
import java.util.Collection;
import java.util.Stack;

import game.shapes.Shape;
import game.shapes.states.*;
import utilities.Properties;

import static utilities.Properties.*;

public class Player {

	private static final int LIMIT = 3;
	private static final int xEPSILON = 10;
	private static final int yEPSILON = 5;
	private static final int rightHand = 1;
	private static final int leftHand = -1;
	private Stack<Shape> leftStack;
	private Stack<Shape> rightStack;
	private int leftHandXCenter;
	private int rightHandXCenter;
	private int handYCenter;
	private int score;
	private int xCenter;
	private int yCenter;
	private int height;
	private String name;


	public Player(String name) {
		this.name = name;
		rightStack = new Stack<>();
		leftStack = new Stack<>();
	}

	protected void setDimensions(int xCenter, int yCenter, int height) {
		this.xCenter = xCenter;
		this.yCenter = yCenter;
		this.leftHandXCenter = xCenter - Properties.shiftHandFromXCenter;
		this.rightHandXCenter = xCenter + Properties.shiftHandFromXCenter;
		this.height = height;
		this.handYCenter = (Properties.frameHeight() - yCenter - Properties.shiftHandFromYCenter); 
	}


	private boolean manageCurrentHand(int shapeXCenter, int shapeYCenter,int handSide) {
		int stackXCenter;
		int stackYCenter = this.handYCenter;
		if (handSide == rightHand) {
			stackXCenter = rightHandXCenter;
			stackYCenter = getRightHandHeight();
		} else {
			stackXCenter = leftHandXCenter;
			stackYCenter = getLeftHandHeight();
		}
		boolean xEpsilon = (shapeXCenter <= stackXCenter + xEPSILON) && (shapeXCenter >= stackXCenter - xEPSILON);
		boolean yEpsilon = (shapeYCenter <= stackYCenter + yEPSILON) && (shapeYCenter >= stackYCenter - yEPSILON);
		return xEpsilon && yEpsilon;
	}
	
	public void manageStack(Collection<Shape> shapes) { // remove static dimensions!!
		if (shapes.equals(null))
			return;
		for (Shape shape : shapes) {
            if (shape.getState() instanceof OnGround || shape.getState() instanceof Captured) {
				continue;
            }
			if (manageCurrentHand(shape.getX(), shape.getY(), rightHand)) {
                shape.setState(new Captured());
				System.out.println("Right hand caught a " + shape.getColor() + " shape");
				shape.setCenter(rightHandXCenter, getRightHandHeight());
				rightStack.push(shape);
				matchPlates(rightStack);
				continue;
			}
				
			if (manageCurrentHand(shape.getX(), shape.getY(), leftHand)) {
                shape.setState(new Captured());
				System.out.println("Left hand caught a " + shape.getColor() + " shape");
				shape.setCenter(leftHandXCenter, getLeftHandHeight());
				leftStack.push(shape);
				matchPlates(leftStack);
			}
		}
	}
	
	private int getRightHandHeight () {
		return handYCenter - (rightStack.size() * Properties.SHAPE_HEIGHT) - Properties.SHAPE_HEIGHT / 2;
	}
	
	private int getLeftHandHeight () {
		return handYCenter - (leftStack.size() * Properties.SHAPE_HEIGHT) - Properties.SHAPE_HEIGHT / 2;
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

		rightHandXCenter = xCenter + Properties.shiftHandFromXCenter; 
		leftHandXCenter = xCenter - Properties.shiftHandFromXCenter;

		for (Shape shape : rightStack)
			shape.setCenter(rightHandXCenter , shape.getY());
		for (Shape shape : leftStack)
			shape.setCenter(leftHandXCenter , shape.getY());
	}

	private void updateScore() { score += EXTRA_POINTS; }

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
		return height + currentHand.size() * SHAPE_HEIGHT;
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
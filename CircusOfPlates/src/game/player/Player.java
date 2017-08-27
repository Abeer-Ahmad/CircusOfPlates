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
	private int xCenter; // of the player
	private int yCenter; // of the player
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
		this.height = height;
		leftHandXCenter = xCenter - shiftHandFromXCenter;
		rightHandXCenter = xCenter + shiftHandFromXCenter;
		handYCenter = frameHeight() - height;
		System.out.println("frameHeight = " + frameHeight());
		System.out.println("image height = " + height);
		System.out.println("yCenter = " + yCenter);
		System.out.println("handYCenter = " + handYCenter);
	}


	private boolean manageCurrentHand(int shapeXCenter, int shapeYCenter,int handSide) {
		int stackXCenter, stackYCenter;
		if (handSide == rightHand) {
			stackXCenter = rightHandXCenter;
			stackYCenter = rightHandTopmostY();
		} else {
			stackXCenter = leftHandXCenter;
			stackYCenter = leftHandTopmostY();
		}
		System.out.print("shapes coor "+ shapeXCenter +" " + shapeYCenter);
		System.out.println(" hand coor "+ stackXCenter +" " + stackYCenter);
		boolean xEpsilon = (shapeXCenter <= stackXCenter + xEPSILON) && (shapeXCenter >= stackXCenter - xEPSILON);
		boolean yEpsilon = (shapeYCenter <= stackYCenter + yEPSILON) && (shapeYCenter >= stackYCenter - yEPSILON);
		return xEpsilon && yEpsilon;
	}
	
	public void manageStack(Collection<Shape> shapes) {
		if (shapes.equals(null))
			return;
		for (Shape shape : shapes) {
            if (shape.getState() instanceof OnGround || shape.getState() instanceof Captured) {
				continue;
            }
			if (manageCurrentHand(shape.getX(), shape.getY(), rightHand)) {
                shape.setState(new Captured());
				System.out.println("Right hand caught a " + shape.getColor() + " shape");
				shape.setCenter(rightHandXCenter, rightHandTopmostY());
				rightStack.push(shape);
				matchPlates(rightStack);
				continue;
			}				
			if (manageCurrentHand(shape.getX(), shape.getY(), leftHand)) {
                shape.setState(new Captured());
				System.out.println("Left hand caught a " + shape.getColor() + " shape");
				shape.setCenter(leftHandXCenter, leftHandTopmostY());
				leftStack.push(shape);
				matchPlates(leftStack);
			}
		}
	}
	
	private int rightHandTopmostY() {
		 return handYCenter - (rightStack.size() * Properties.SHAPE_HEIGHT) - Properties.SHAPE_HEIGHT / 2;
	}
	
	private int leftHandTopmostY() {
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

	public void move(int step) {
		xCenter += step;
		xCenter += frameWidth();
		xCenter %= frameWidth();
		rightHandXCenter = xCenter + Properties.shiftHandFromXCenter; 
		leftHandXCenter = xCenter - Properties.shiftHandFromXCenter;
		for (Shape shape : rightStack)
			shape.setCenter(rightHandXCenter, shape.getY());
		for (Shape shape : leftStack)
			shape.setCenter(leftHandXCenter, shape.getY());
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
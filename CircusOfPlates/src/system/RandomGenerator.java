package system;

import java.awt.Color;
import java.util.HashMap;

import shapes.Shape;

public class RandomGenerator {

	private Factory shapeFactory;
	private HashMap<Integer, Color> colors;
	private String difficultyLevel;
	private int minSpeed;
	private int maxSpeed;
	private int colorLimit;
	
	public RandomGenerator() { // replace that constructor in model, model has the final SHAPE_NAME
		intializeColors();
		shapeFactory = new ShapeFactory();
	}
	
	/*public RandomGenerator(final String[] shapeName) {
		intializeColors();
		shapeFactory = new ShapeFactory(shapeName);
	}*/
	
	private void intializeColors() {
		colors = new HashMap<Integer, Color>();
		colors.put(1, Color.BLUE);
		colors.put(7, Color.MAGENTA);
		colors.put(2, Color.CYAN);
		colors.put(3, Color.DARK_GRAY);
		colors.put(4, Color.GRAY);
		colors.put(5, Color.GREEN);
		colors.put(6, Color.LIGHT_GRAY);
		colors.put(7, Color.MAGENTA);
		colors.put(8, Color.ORANGE);
		colors.put(9, Color.PINK);
		colors.put(10, Color.RED);
		colors.put(11, Color.YELLOW);
		colors.put(0, Color.BLACK);
	}
	
	public void setDifficultyLevel(String difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
		// setSpeedLimit();
		setColorLimit();
	}
	
	/* private void setSpeedLimit() {
		if (difficultyLevel.equals("Easy")) {
			minSpeed = 8;
			maxSpeed = 40;
		} else if (difficultyLevel.equals("Medium")) {
			minSpeed = 15;
			maxSpeed = 50;
		} else if (difficultyLevel.equals("Hard")) {
			minSpeed = 20;
			maxSpeed = 60;
		}
	} */
	
	private void setColorLimit() {
		if (difficultyLevel.equals("Easy")) {
			colorLimit = colors.size() / 2;
		} else if (difficultyLevel.equals("Medium")) {
			colorLimit = colors.size() * 3 / 4;
		} else if (difficultyLevel.equals("Hard")) {
			colorLimit = colors.size();
		}
		if (colorLimit==0) {colorLimit++;}
	}

	private Color getRandomColor() {
		int randomNum = getRandomNumber();
		// randomNum %= colors.size();
		randomNum %= colorLimit;
		return colors.get(randomNum);
	}

	public int getRandomNumber() {
		int randomNum = (int) Math.round((((float) Math.random()) * 452521));
		randomNum &= (int) Math.round((((float) Math.random()) * 321654));
		randomNum ^= (int) Math.round((((float) Math.random()) * 987456));
		return randomNum;
	}

	public Shape getRandomShape(final int x, final int y, final int beltLength) {
		int randomNum = getRandomNumber();
		Color shapeColor = getRandomColor();
		return shapeFactory.getRandomShape(x, y, beltLength, randomNum, shapeColor);
	}

	public int getRandomSpeed() {
		int randomNum = 10;
		while (randomNum <= 10) {
			randomNum = getRandomNumber();
			randomNum %= 60;
		}
		/* int randomNum = minSpeed;
		while (randomNum == minSpeed) {
			randomNum = getRandomNumber();
			// randomNum %= 60;
			randomNum %= maxSpeed;
		} */
		return randomNum;
	}
}

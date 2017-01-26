package system;
 
import java.awt.Color;
import java.util.HashMap;
 
import shapes.CorruptShape;
import shapes.Shape;
 
public class RandomGenerator {
 
    private Factory shapeFactory;
    private Factory strategyFactory;
    private GameStrategy strategy;
    private ShapePool corruptPool;
    private HashMap<Integer, Color> colors;
    // private String difficultyLevel;
    private int minSpeed;
    private int maxSpeed;
    private int colorLimit;
    private int limitCorrupt;
    private int shapeCounter=0;
    
    public RandomGenerator() {
        intializeColors();
        shapeFactory = new ShapeFactory();
        strategyFactory = new StrategyFactory();
        corruptPool = CorruptPool.getInstance();
    }
   
    private void intializeColors() {
        colors = new HashMap<Integer, Color>();
        colors.put(0, Color.BLUE);
        colors.put(1, Color.MAGENTA);
        colors.put(2, Color.CYAN);
        colors.put(3, Color.GRAY);
        colors.put(4, Color.GREEN);
        colors.put(5, Color.LIGHT_GRAY);
        colors.put(6, Color.MAGENTA);
        colors.put(7, Color.ORANGE);
        colors.put(8, Color.PINK);
        colors.put(9, Color.RED);
        colors.put(10, Color.YELLOW);     
    }
    
   public void setDifficultyLevel(String difficultyLevel) {
        strategy = strategyFactory.getStrategy(difficultyLevel);
        strategy.setGameStrategy(this);
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
   
    public void setColorLimit(int colorLimit, int limitCorrupt) {
        this.colorLimit = colorLimit;
        this.limitCorrupt = limitCorrupt;
    }
 
    public int getColorsRange() {
    	return colors.size();
    }
    
    private Color getRandomColor() {
        int randomNum = getRandomNumber();
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
		shapeCounter++;
		int randomNum = getRandomNumber();
		Color shapeColor = getRandomColor();
		if (shapeCounter == limitCorrupt) {
			Shape temp = corruptPool.pull(x, y, beltLength);
			if (temp != null) {
				shapeCounter = 0;
				return temp;
			}
		}
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
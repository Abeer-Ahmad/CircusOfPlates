package system;

import java.awt.Color;

import shapes.Shape;

public class StrategyFactory extends Factory {
	
	StrategyFactory() {
		
	}
	
	@Override
	public GameStrategy getStrategy(String difficultyLevel) {
		if (difficultyLevel.equals("Easy"))
            return new Easy();
        if (difficultyLevel.equals("Medium"))
        	return new Medium();
        if (difficultyLevel.equals("Hard"))
        	return new Hard();
        return null;
	}

	@Override
	public Shape getRandomShape(int x, int y, int beltLength, int randomshape, Color randomColor) {
		return null;
	}

}

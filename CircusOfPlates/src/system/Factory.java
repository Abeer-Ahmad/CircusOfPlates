package system;

import java.awt.Color;

import shapes.Shape;

public abstract class Factory {
	
	public Factory() {
		
	}
	
	public abstract Shape getRandomShape(final int x, final int y, final int beltLength, final int randomshape, final Color randomColor);
	
	public abstract GameStrategy getStrategy(String difficultyLevel) ;
}

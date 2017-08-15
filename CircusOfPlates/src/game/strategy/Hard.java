package game.strategy;

import plateGenerator.PlateGenerator;

public class Hard implements GameStrategy {

	public static final int CORRUPTED_LIMIT = 5;
	
	@Override
	public void setGameStrategy(PlateGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange(), CORRUPTED_LIMIT);
	}
}

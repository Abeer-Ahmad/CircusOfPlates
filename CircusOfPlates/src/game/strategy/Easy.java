package game.strategy;

import plateGenerator.PlateGenerator;

public class Easy implements GameStrategy {
	
	public static final int CORRUPTED_LIMIT = 40;
	
	@Override
	public void setGameStrategy(PlateGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange() / 2, CORRUPTED_LIMIT);
	}

}

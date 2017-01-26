package system;

public class Medium implements GameStrategy {
	
	public static final int CORRUPTED_LIMIT = 20;

	@Override
	public void setGameStrategy(RandomGenerator randomGenerator) {
		randomGenerator.setColorLimit(randomGenerator.getColorsRange() * 3 / 4, CORRUPTED_LIMIT);
	}

}

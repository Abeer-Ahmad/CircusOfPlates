package game.strategy;

public interface IStrategyFactory {

	public abstract GameStrategy getStrategy(String difficultyLevel) ;
}

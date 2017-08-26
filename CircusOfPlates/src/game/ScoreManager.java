package game;

import java.util.ArrayList;

import game.player.Player;
 
public class ScoreManager {
	
    private ArrayList<Player> players;
    private Player winner;  
    private int redLevel;
    private int maxScore;
    private static ScoreManager scoreManager;
    
    private ScoreManager(ArrayList<Player> players, int laserHeight) {
    	
        this.players = players;
        redLevel = laserHeight;
        maxScore = 0;
 
    }
   
    public static ScoreManager getInstance (ArrayList<Player> players, int laserHeight){
    	if (scoreManager == null)
    		scoreManager = new ScoreManager(players,laserHeight);
    	return scoreManager;
    }
    
    public boolean isOver() {
        for (Player player : players)
            if (player.getMaxHeight() >= redLevel)
                return true;
        return false;
    }
   
    public Player getWinner() {
        for (Player player : players)
            if (player.getScore() > maxScore) {
                winner = player;
                maxScore = winner.getScore();
            }
        return winner;
    }
}
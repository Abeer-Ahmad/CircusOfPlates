
package utilities;

import java.awt.*;

public final class Properties {

	private static final String RESOURCE_SEPARATOR = "/";
	public static final String PLAYER1 = "imgs" + RESOURCE_SEPARATOR + "player1.png";
	public static final String PLAYER2 = "imgs" + RESOURCE_SEPARATOR + "player2.png";
	public static final String NEW_GAME = "imgs" + RESOURCE_SEPARATOR + "newgame.jpg";
	public static final String BACK_GROUND = "imgs" + RESOURCE_SEPARATOR + "background.jpg";
	public static final String NEW_GAME_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "new_game.png";
	public static final String LOAD_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "load_game.png";
	public static final String QUIT_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "quit.png";
	public static final String RULES_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "rules.png";
	public static final String SAVE_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "save_game.png";
	public static final String CONTINUE_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "continue.png";
	public static final String MAINMENU_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "main_menu.png";
	public static final String PLAYAGAIN_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "play_again.png";
	public static final String ONEPLAYER_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "one_player.png";
	public static final String TWOPLAYERS_BUTTON = "imgs" + RESOURCE_SEPARATOR + "buttons" + RESOURCE_SEPARATOR + "two_players.png";
	public static final String MAIN_MENU = "mainMenu";
	public static final String PLAYER_MENU = "playerMenu";
	public static final String PAUSE_MENU = "pauseMenu";
	public static final Color Corrupt_Color = Color.black;

	public static final int SHIFT = 100; // shifting clowns from the center at
											// the beginning
	public static final int EXTRA_POINTS = 5;
	public static final int SHAPE_WIDTH = 80;
	public static final int SHAPE_HEIGHT = 20;
	public static final int shiftHandFromXCenter = 133;

	public static int GENERATION_SHAPES_SPEED;
	public static int UPDATED_SHAPES_SPEED;
	public static int COLOR_LIMIT;

	public static final int frameWidth() {
		return Toolkit.getDefaultToolkit().getScreenSize().width;
	}

	public static final int frameHeight() {
		return Toolkit.getDefaultToolkit().getScreenSize().height;
	}

}

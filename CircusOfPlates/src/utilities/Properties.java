package utilities;

import java.awt.*;
import java.io.File;

public final class Properties {


    public static final String PLAYER1 = "resources" + File.separator + "imgs" + File.separator + "player1.png";

    public static final String PLAYER2 = "resources" + File.separator + "imgs" + File.separator + "player2.png";

    public static final String NEW_GAME = "resources" + File.separator + "imgs" + File.separator + "newgame.jpg";

    public static final String BACK_GROUND = "resources" + File.separator + "imgs" + File.separator + "background.jpg";

    public static final int SHIFT = 100; // shifting clowns from the center at the beginning

    public static final int EXTRA_POINTS = 5;

    public static final int SHAPE_WIDTH = 80;

    public static final int SHAPE_HEIGHT = 20;
    
    public static final int shiftHandFromXCenter = 133;

    public static final int frameWidth(){ return Toolkit.getDefaultToolkit().getScreenSize().width; }

    public static final int frameHeight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }

}

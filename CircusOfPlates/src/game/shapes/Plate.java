package game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Plate extends Shape {

    private static final long serialVersionUID = 7342630509541069121L;

    public Plate(int x, int y, int beltLength, Color color) {
        super();
        super.beltLength = beltLength;
        super.color = color;
        xCenter = x;
        yCenter = y;
    }

    public void draw(Graphics2D g) {
        setShape(g);
        int topLeftX = xCenter - width / 2;
        int topLeftY = yCenter - height / 2;
        int[] pointsX = { topLeftX, topLeftX + width, topLeftX + 3 * width / 4, topLeftX + width / 4 };
        int[] pointsY = { topLeftY, topLeftY, topLeftY + height, topLeftY + height };
        g.fillPolygon(pointsX, pointsY, 4);
    }
}

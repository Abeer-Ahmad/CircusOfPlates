package game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Oval extends Shape {

    private static final long serialVersionUID = 4100452177472943216L;

    public Oval(int x, int y, int beltLength, Color color) {
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
        g.fillOval(topLeftX, topLeftY, width, height);
    }
}

package game.shapes;

import java.awt.Color;
import java.awt.Graphics2D;

public class Box extends Shape {

    private static final long serialVersionUID = -8759322627165183744L;

    public Box(int x, int y, int beltLength, Color color) {
        super();
        super.beltLength = beltLength;
        super.color = color;
        xCenter = x;
        yCenter = y;
    }

    public void draw(Graphics2D g)  {
        setShape(g);
        int topLeftX = xCenter - width / 2;
        int topLeftY = yCenter - height / 2;
        g.fillRect(topLeftX, topLeftY, width, height);
    }
}

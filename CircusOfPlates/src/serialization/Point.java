package serialization;

import java.io.Serializable;

public class Point implements Serializable {

    private int x;
    private int y;
    private Color color;


    public Point(int x, int y, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.color = new Color(color);
    }

    public void increment() {
        x++;
        y++;
    }

    public void brighten() {
        color = new Color(color.color().darker());
    }

    public void print() {
        System.out.println("(x, y) = (" + x + ", " + y + ") in " + color);
    }
}

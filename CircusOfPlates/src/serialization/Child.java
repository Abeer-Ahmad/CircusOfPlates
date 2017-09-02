package serialization;

import java.awt.*;

public class Child extends Point {

    private int inner;

    public Child(int x, int y, java.awt.Color color) {
        super(x, y, color);
        inner = 8;
    }

    @Override
    public void print() {
        super.print();
        System.out.println("inner = " + inner);
    }

}

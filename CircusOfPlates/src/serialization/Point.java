package serialization;

import java.io.IOException;
import java.io.Serializable;
import java.util.Stack;

public class Point implements Serializable {

    protected int x;
    protected int y;
    protected Stack<Integer> stack;
    protected transient Color color;


    public Point(int x, int y, java.awt.Color color) {
        this.x = x;
        this.y = y;
        this.color = new Color(color);
        stack = new Stack<>();
        stack.push(1);
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
        System.out.println("stack: " + stack);
    }

    private void writeObject(java.io.ObjectOutputStream stream) throws IOException {
        stream.defaultWriteObject();
        stream.writeChars(color.color().toString());
    }

    private void readObject(java.io.ObjectInputStream stream) throws IOException, ClassNotFoundException {
        stream.defaultReadObject();
        color = new Color(new java.awt.Color(stream.readInt()));
    }
}

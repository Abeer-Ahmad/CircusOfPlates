package serialization;

public class Color {

    private java.awt.Color color;

    public Color (java.awt.Color color) {
        this.color = color;
    }

    public java.awt.Color color() {
        return color;
    }

    @Override
    public String toString() {
        return Integer.toString(color.getRGB());
    }
}

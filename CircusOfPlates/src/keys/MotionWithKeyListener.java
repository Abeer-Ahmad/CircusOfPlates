package keys;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MotionWithKeyListener extends KeyAdapter {
    private Component component;
    private int deltaX;
    private int deltaY;

    public MotionWithKeyListener(Component component, int deltaX, int deltaY) {
        this.component = component;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    private static JButton addMotionSupport(Component component) {
        KeyListener keyListener = new MotionWithKeyListener(component, 3, 3);
        component.addKeyListener(keyListener);
        component.setFocusable(true);
        return new JButton("LEFT");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JPanel content = new JPanel();
                content.setLayout(null);
                JLabel component = new JLabel(new ColorIcon(Color.BLUE, 40, 40));
                component.setSize(component.getPreferredSize());
                component.setLocation(100, 100);
                content.add(component);
                JButton left = addMotionSupport(component);
                JFrame.setDefaultLookAndFeelDecorated(true);
                JFrame frame = new JFrame("Motion With Key Listener");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(content);
                frame.add(left, BorderLayout.SOUTH);
                frame.setSize(600, 600);
                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }

    public void move(int deltaX, int deltaY) {
        int componentWidth = component.getSize().width;
        int componentHeight = component.getSize().height;
        Dimension parentSize = component.getParent().getSize();
        int parentWidth = parentSize.width;
        int parentHeight = parentSize.height;

        int nextX = Math.max(component.getLocation().x + deltaX, 0);
        if (nextX + componentWidth > parentWidth)
            nextX = parentWidth - componentWidth;

        int nextY = Math.max(component.getLocation().y + deltaY, 0);
        if (nextY + componentHeight > parentHeight)
            nextY = parentHeight - componentHeight;

        component.setLocation(nextX, nextY);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            move(-deltaX, 0);
        else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            move(deltaX, 0);
        else if (e.getKeyCode() == KeyEvent.VK_UP)
            move(0, -deltaY);
        else if (e.getKeyCode() == KeyEvent.VK_DOWN)
            move(0, deltaY);
    }

    static class ColorIcon implements Icon {
        private Color color;
        private int width;
        private int height;

        public ColorIcon(Color color, int width, int height) {
            this.color = color;
            this.width = width;
            this.height = height;
        }

        public int getIconWidth() {
            return width;
        }

        public int getIconHeight() {
            return height;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
        }
    }
}
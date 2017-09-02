package serialization;

import java.io.*;

public class Demo {

    public static void main(String[] args) {
       /* Point p = new Point(0, 0, java.awt.Color.BLUE.darker());
        p.increment();
        p.increment();
        save(p);
        p.increment();
        p.brighten();
        System.out.println("Saved point:");
        load();
        System.out.println("Current point:");
        p.print();*/
        Point c = new Child(0, 0, java.awt.Color.BLUE.darker());
        save (c);
        load();
    }

    private static void save(Point p) {
        try {
            FileOutputStream fileOut = new FileOutputStream("point.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in point.ser");
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    private static void load() {
        try {
            FileInputStream fileIn = new FileInputStream("point.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Point p = (Point) in.readObject();
            in.close();
            fileIn.close();
            p.print();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Point class not found");
            c.printStackTrace();
            return;
        }
    }
}

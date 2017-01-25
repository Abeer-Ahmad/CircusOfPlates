package system;

import java.awt.Color;
import java.io.File;
import java.lang.reflect.Constructor;
import java.util.HashMap;


// import shapes.Box;
// import shapes.Oval;
// import shapes.Plate;
import shapes.Shape;

public class ShapeFactory extends Factory{
	
	private static final String[] shapeName = {"Box", "Oval", "Plate"}; // remove
	
	private DynamicLoader loader;
	// private String[] shapeName;
	private HashMap<String, Constructor<?>> shapes;
	private HashMap<String, Integer> shapeID;

	public ShapeFactory(/*final String[] shapeName*/) {
		super();
		// this.shapeName = shapeName;
		loader = new DynamicLoader();
		loadShapes();
		initializeIDs();
	}
	
	// access to classes files
	private void loadShapes() {
		Class<?> shapeClass;
		Constructor<?> constructor;
		shapes = new HashMap<String, Constructor<?>>();
		for (int i = 0; i < shapeName.length; i++) {
			// shapeClass = loader.loadClass(new File(""), "shapes", shapeName[i]); // different file for each class
			shapeClass = loader.loadClass("shapes", shapeName[i]); // remove
			try {
				constructor = shapeClass.getConstructors()[0];
				shapes.put(shapeName[i], constructor);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	private void initializeIDs() {
		shapeID = new HashMap<String, Integer>();		
		for (int i = 0; i < shapeName.length; i++) {
			shapeID.put(shapeName[i], i);
		}
	}
	
	@Override
	public Shape getRandomShape(final int x, final int y, final int beltLength, int randomshape, final Color randomColor) {
		randomshape %= shapeName.length;
		try {
			if (randomshape == shapeID.get("Plate"))
				return (Shape) shapes.get("Plate").newInstance(x, y, beltLength, randomColor);
			if (randomshape == shapeID.get("Box"))
				return (Shape) shapes.get("Box").newInstance(x, y, beltLength, randomColor);
			if (randomshape == shapeID.get("Oval"))
				return (Shape) shapes.get("Oval").newInstance(x, y, beltLength, randomColor);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

}

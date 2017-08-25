package game.shapes;

import java.awt.Color;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Properties;
import utilities.DynamicLinkage;

public class ShapeFactory extends IShapeFactory {
	
	private static final String configurationFile = "gameConfiguration.properties";
	
	private DynamicLinkage loader;
	private String[] shapeNames;
	private HashMap<String, Constructor<?>> shapes;
	private HashMap<String, Integer> shapeID;

	public ShapeFactory() {
		super();
		shapeNames = readConfigurationFiles ();
		loader = new DynamicLinkage();
		loadShapes();
		initializeIDs();
	}
	
	private String[] readConfigurationFiles () {
		
		File configFile = new File(configurationFile);
		String[] shapesLoaded;
		try {
			FileReader reader = new FileReader(configFile);
			Properties prop = new Properties();
			prop.load(reader);
			shapesLoaded =  prop.getProperty("Shapes").split("\\s*,\\s*");
			
		} catch (Exception e) {
			throw new RuntimeException("error in configuration file");
		}
		return shapesLoaded;
	}
	private void loadShapes() {
		
		Class<?> shapeClass;
		Constructor<?> constructor;
		shapes = new HashMap<String, Constructor<?>>();
		for (String shapeName : shapeNames) {
			shapeClass = loader.loadClass("game.shapes", shapeName); 
			try {
				constructor = shapeClass.getConstructors()[0];
				shapes.put(shapeName, constructor);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	}
	
	private void initializeIDs() {
		shapeID = new HashMap<String, Integer>();		
		for (int i = 0; i < shapeNames.length; i++) {
			shapeID.put(shapeNames[i], i);
		}
	}
	
	@Override
	public Shape getRandomShape(final int x, final int y, final int beltLength, int randomshape, final Color randomColor) {
		randomshape %= shapeNames.length;
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

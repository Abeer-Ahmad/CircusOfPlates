package utilities;

import java.awt.Toolkit;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;


import javax.swing.JOptionPane;

public class DynamicLinkage implements IDynamicLinkage {
	
	private static final String shapeJarPath = "resources" + File.separator + "jars" + File.separator + "shapes";

	private File file;
	private URL url;
	private URLClassLoader classLoader;
	private Class<?> loadedClass;
	
	public DynamicLinkage() {

	}

	@Override
	public Class<?> loadClass(String packageName, String className) {
		try {
			
			file = new File(shapeJarPath + File.separator + className + ".jar"); 
			url = file.toURI().toURL();
			classLoader = new URLClassLoader(new URL[] { url });
			loadedClass = classLoader.loadClass(packageName + "." + className);
		} catch (Exception e) {
			Toolkit.getDefaultToolkit().beep();
			JOptionPane.showMessageDialog(null, "Cannot Load Class!", "Warning", JOptionPane.PLAIN_MESSAGE);
			System.out.println(e.toString());
		}
		return loadedClass;
	}
}

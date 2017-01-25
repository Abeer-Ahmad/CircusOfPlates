package system;

import interfaces.DynamicLinkage;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class DynamicLoader implements DynamicLinkage {
	
	private static final Path DIRECTORY_PATH = Paths.get(System.getProperty("user.home")); // remove
	
	File file;
	URL url;
	URLClassLoader classLoader;
	Class<?> loadedClass;
	
	public DynamicLoader() {

	}

	@Override
	/*public Class<?> loadClass(File file, String packageName, String className) {*/
	public Class<?> loadClass(String packageName, String className) {
		try {
			file = new File(DIRECTORY_PATH + File.separator + packageName + File.separator + className + ".jar"); // remove
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

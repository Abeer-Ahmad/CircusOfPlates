package utilities;

import java.io.InputStream;

public class ResourceLoader {

	public static InputStream load(String path) {
		InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		return input;
	}

}

package system;

import java.io.InputStream;

public class LoadImage {

	public InputStream load(String path) {
		InputStream input = LoadImage.class.getResourceAsStream(path);
		if (input == null) {
			input = LoadImage.class.getResourceAsStream("/" + path);
		}
		return input;
	}

}

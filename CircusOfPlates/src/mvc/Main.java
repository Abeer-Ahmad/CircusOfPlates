package mvc;

import java.awt.EventQueue;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import java.util.Properties;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					 Application app =new Application();
					 app.run();
					
						
				} catch (Exception e) {
					e.printStackTrace(System.out);
					System.err.println("Error Running the Program");
				}
			}
		});
	}

}

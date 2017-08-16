package mvc;

import java.awt.EventQueue;
import java.io.File;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 Application app =new Application();
					 app.run();
					/*File file = new File("resources/imgs/myfile.txt");
			         System.out.println(file.getAbsolutePath());
			         if(file.createNewFile())System.out.println("Success!");
			         else System.out.println ("Error, file already exists.");*/
				} catch (Exception e) {
					e.printStackTrace(System.out);
					System.err.println("Error Running the Program");
				}
			}
		});
	}

}

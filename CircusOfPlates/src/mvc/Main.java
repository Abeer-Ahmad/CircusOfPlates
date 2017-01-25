package mvc;

import java.awt.EventQueue;

import gui.MainFrame;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Application trial =new Application();
					trial.run();
				} catch (Exception e) {
					e.printStackTrace(System.out);
					System.err.println("Error Running the Program");
				}
			}
		});
	}

}

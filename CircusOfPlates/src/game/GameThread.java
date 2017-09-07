package game;

import mvc.Model;

public class GameThread implements Runnable {

	private Model game;

	public GameThread(Model game) {
		this.game = game;
	}

	@Override
	public void run() {
		while(true){
			try {
				if (game.isRunning())
						game.updateGameItems();
						System.out.println("runnung gameThread");
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
					try {
						Thread.sleep(150);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
	}
		
	}


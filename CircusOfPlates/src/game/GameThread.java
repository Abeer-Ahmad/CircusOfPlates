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
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} 
	}
		
	}


package spaceinvaders;

public class Spawner {

	private Handler handler;
	private HUD hud;
	private int[][] fieldValue;

	public Spawner(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;

	}

	public void tick() {

		fillField();
	}

	public int[][] levelOne() {
		int[][] field = { { 1, 1, 1 } };

		return field;
	}

	public int[][] levelTwo() {
		int[][] field = { { 1, 1, 1 }, { 1, 1, 1 } };

		return field;
	}

	public int[][] levelThree() {
		int[][] field = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

		return field;
	}

	public int[][] levelFour() {
		int[][] field = { { 1, 1, 1, 1 }, { 1, 1, 1, 1 }, { 1, 1, 1, 1 } };

		return field;
	}

	public int[][] levelFive() {
		int[][] field = { { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1 } };

		return field;
	}
	
	public void fillField() {
		int value;
		int spaceX = 0;
		int spaceY = 0;
		
		
		if (HUD.enemyCount <= 0) {
			hud.setLevel(hud.getLevel() + 1);
			
			if (hud.getLevel() == 1) {
				fieldValue = levelOne();
			
			} else if (hud.getLevel() == 2) {
				fieldValue = levelTwo();	
			} else if(hud.getLevel() == 3) {
				fieldValue = levelThree();
			} else if(hud.getLevel() == 4) {
				fieldValue = levelFour();
			} else {
				fieldValue = levelFive();
			}
			
			for (int row = 0; row < fieldValue.length; row++) {
				spaceY += 50;
				for (int column = 0; column < fieldValue[0].length; column++) {
					spaceX += 50;
					value = fieldValue[row][column];

					switch (value) {
					case 1: {

						handler.addObject(new Enemy(0 + spaceX, 0 + spaceY, ID.Enemy, handler));
						HUD.enemyCount += 1;
					
						break;
					}

					}
				}
				spaceX = 0;
				
			}
		}
		
	}

}

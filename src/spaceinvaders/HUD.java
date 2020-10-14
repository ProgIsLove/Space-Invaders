package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class HUD {
	
	private int level = 0;
	public static int live = 3;
	public static int enemyCount;
	private final int space = 20;
	
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("Arial",3,12));
		g.drawString("Level " + level, Game.WIDTH/2-20, 30);
		g.drawString("Lives ", Game.WIDTH-150, 30);
		g.drawString("Enemy " + enemyCount, 20, 30);
		
		for(int i = 0; i < live; i++) {
			g.setColor(Color.green);
			g.fillOval(Game.WIDTH-100+i*space, 20, 10, 10);
			
		}
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
}

package spaceinvaders;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import spaceinvaders.Game.STATE;

public class Menu extends MouseAdapter {

	private Handler handler;
	private HUD hud;
	
	public Menu(Handler handler, HUD hud) {
		this.handler = handler;
		this.hud = hud;
	}
	
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		
		if(Game.gameState == STATE.Menu) {
			//play button
			if(mouseOver(mx, my, Game.WIDTH/2 -75, Game.HEIGHT/2 - 100, 150,50)) {
				Game.gameState = STATE.Game;
				handler.addObject(new Ship(Game.WIDTH/2 - 32, Game.HEIGHT/2 + 150, ID.Ship, handler));
				return;
			}
			
			//help button
			if(mouseOver(mx, my, Game.WIDTH/2 - 75, Game.HEIGHT/2 - 25, 150, 50)) {
				Game.gameState = STATE.Help;
			}
			
			//quit button
			if(mouseOver(mx, my, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 50, 150, 50)) {
				System.exit(1);
			}
		}
		
		//back button for help
		if(Game.gameState == STATE.Help) {
			if(mouseOver(mx, my, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 125, 150, 50)) {
				Game.gameState = STATE.Menu;
				return;
			}
		}
		
		//try again button for new game
		if(Game.gameState == STATE.End) {
			if(mouseOver(mx, my, Game.WIDTH/2 - 75, Game.HEIGHT/2 + 125, 150, 50)) {
				Game.gameState = STATE.Menu;
				HUD.live = 3;
				HUD.enemyCount = 0;
				hud.setLevel(0);
			}
		}
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font fnt = new Font("arial", 1, 50);
		Font fnt2 = new Font("arial", 1, 30);
		Font fnt3 = new Font("arial", 1, 20);
		
		if(Game.gameState == STATE.Menu) {	
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Menu", Game.WIDTH/2 - 65, Game.HEIGHT/2 - 150);
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 - 100, 150, 50);
			g.drawString("Play", Game.WIDTH/2 - 30, Game.HEIGHT/2 - 65);
			
			g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 - 25, 150, 50);
			g.drawString("Help", Game.WIDTH/2 - 30, Game.HEIGHT/2 + 10);
			
			g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 50, 150, 50);
			g.drawString("Quit", Game.WIDTH/2 - 30, Game.HEIGHT/2 + 85);
		}else if(Game.gameState == STATE.Help) {
			g.setFont(fnt);
			g.setColor(Color.white);
			g.drawString("Help", Game.WIDTH/2 - 65, Game.HEIGHT/2 - 150);
			
			g.setFont(fnt3);
			g.drawString("USE AD keys to move player and SPACE to shoot", 80, Game.HEIGHT/2);
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 125, 150, 50);
			g.drawString("Back", Game.WIDTH/2 - 35, Game.HEIGHT/2 + 160);
		}else if(Game.gameState == STATE.End) {
			g.setFont(fnt);
			g.setColor(Color.WHITE);
			g.drawString("Game over", Game.WIDTH/2 - 130, Game.HEIGHT/2 - 150);
			
			g.setFont(fnt2);
			g.drawRect(Game.WIDTH/2 - 75, Game.HEIGHT/2 + 125, 150, 50);
			g.drawString("Try again", Game.WIDTH/2 - 67, Game.HEIGHT/2 + 160);
		}
	}
	
}

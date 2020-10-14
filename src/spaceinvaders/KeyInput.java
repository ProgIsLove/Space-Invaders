package spaceinvaders;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import spaceinvaders.Game.STATE;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyPress = new boolean[2];
	private boolean isShooting = false;
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyPress[0] = false;
		keyPress[1] = false;
		
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ship) {
				if(key == KeyEvent.VK_D) {tempObject.setVelX(handler.speed); keyPress[0] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-handler.speed); keyPress[1] = true;}
				if(key == KeyEvent.VK_SPACE && !isShooting) {
					isShooting = true;
					handler.addObject(new ShipBullet(tempObject.getX()+8,tempObject.getY()+8,ID.ShipBullet, handler));
					
				}
				if(key == KeyEvent.VK_P) {
					if(Game.gameState == STATE.Game) {
						if(Game.paused) Game.paused = false;
						else Game.paused = true;
					}
				}
			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Ship) {
				if(key == KeyEvent.VK_D) keyPress[0] = false;
				if(key == KeyEvent.VK_A) keyPress[1] = false;
				if(key == KeyEvent.VK_SPACE) isShooting = false;
				
				
				if(!keyPress[0] && !keyPress[1]) tempObject.setVelX(0);
			}
			
		}
	}
}

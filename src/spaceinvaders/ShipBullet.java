package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class ShipBullet extends GameObject {

	Handler handler;
	BufferedImage bullet_image;
	
	public ShipBullet(float x, float y, ID id, Handler handler) {
		super(x,y,id);
		
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		bullet_image = ss.grabImage(1, 2, 32, 32);
		
		velY = 5;
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		y-=velY;
		
		if(y>=Game.HEIGHT) handler.removeObject(this);
		
		collision();
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//g.setColor(Color.blue);
		//g.fillRect((int) x, (int)y, 10, 21);
		
		g.drawImage(bullet_image, (int)x, (int)y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		
		return new Rectangle((int)x, (int)y, 10,21);
	}
	
	private void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.Enemy) {
				if(getBounds().intersects(tempObject.getBounds())) {
					
					handler.removeObject(tempObject);
					handler.removeObject(this);
					HUD.enemyCount -= 1;
					
				}
				
			}
			
		}
		
	}
		
}

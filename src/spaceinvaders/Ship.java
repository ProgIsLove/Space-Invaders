package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Ship extends GameObject {
	Handler handler;
	BufferedImage ship_image;
	
	
	public Ship(float x, float y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		ship_image = ss.grabImage(1, 1, 32, 32);
	}
	
	
	@Override
	public void tick() {
		x += velX;
		
		x = Game.clamp(x, 0,Game.WIDTH-44);
		
		collision();
	}

	@Override
	public void render(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 28, 28);
		
		g.drawImage(ship_image, (int) x, (int) y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 28, 28);
		
	}
	
	public void collision() {
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.EnemyBullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					
					HUD.live -= 1;
					
				}
			}
		}
	}
	
	
}

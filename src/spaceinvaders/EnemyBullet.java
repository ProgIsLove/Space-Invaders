package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class EnemyBullet extends GameObject {
	
	private Handler handler;
	private BufferedImage bullet;
	
	private Random r = new Random();
	
	public EnemyBullet(float x, float y, ID id, Handler handler) {
		// TODO Auto-generated constructor stub
		super(x, y, id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		bullet = ss.grabImage(1, 4, 32, 32);
		
		velX = 0;
		velY = r.nextInt(6 - 2 +1)+2;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x += velX;
		y += velY;
		
		if(y> Game.HEIGHT) handler.removeObject(this);
		
		collision();
		
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//g.setColor(Color.red);
		//g.fillRect((int)x, (int)y, 10, 16);
		
		g.drawImage(bullet, (int)x, (int)y, null);
		
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x,(int)y, 10,16);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.ShipBullet) {
				if(getBounds().intersects(tempObject.getBounds())) {
					handler.removeObject(tempObject);
					handler.removeObject(this);
				}
			}
		}
	}
	
}

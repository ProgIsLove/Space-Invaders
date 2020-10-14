package spaceinvaders;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Enemy extends GameObject {
	
	private final int timer1 = 500;
	private final int timer2 = 10;
	
	private Handler handler;
	private BufferedImage enemy;
	
	private Random r = new Random();
	
	private int timerShoot = r.nextInt(600);
	private int timerMoveHorizontal = timer1;
	private int timerMoveVertical = timer2;
	
	
	public Enemy(float x, float y, ID id, Handler handler) {
		super(x,y,id);
		this.handler = handler;
		
		SpriteSheet ss = new SpriteSheet(Game.sprite_sheet);
		
		enemy = ss.grabImage(1, 3, 32, 32);
		
		velX = 1;
		velY = 0;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		x+= velX;
		y+= velY;
		
		if(timerMoveHorizontal <=0 && (x>=Game.WIDTH-44 || x<=0)) {if(velX == 1) velX = 0;}
		if(timerMoveHorizontal <= 0) {velY = 1;}
		else timerMoveHorizontal--;
		
		if(timerMoveHorizontal <= 0) timerMoveVertical--;
		if(timerMoveVertical <=0) {
			if(velY == 1) velY = 0;
			if(velX == 0) velX = 1;
			timerMoveHorizontal = timer1;
			timerMoveVertical = timer2;
		}
		
		if(timerShoot <=0) {handler.addObject(new EnemyBullet((int)x+11,(int)y+16,ID.EnemyBullet, handler));timerShoot = r.nextInt(600);}
		else timerShoot--;
		//System.out.println(timerShoot);
		
		if(x<=0 || x>=Game.WIDTH-44) velX *=-1;
		if(y<=0 || y>=Game.HEIGHT-70) velY *=-1;
	}

	@Override
	public void render(Graphics g) {
		// TODO Auto-generated method stub
		//g.setColor(Color.white);
		//g.fillRect((int)x, (int)y, 32, 32);
		
		g.drawImage(enemy, (int)x,(int)y, null);
	}

	@Override
	public Rectangle getBounds() {
		// TODO Auto-generated method stub
		return new Rectangle((int)x, (int)y, 32,32);
	}
	
}

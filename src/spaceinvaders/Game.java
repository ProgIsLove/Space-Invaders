package spaceinvaders;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Spawner spawner;
	private HUD hud;
	private Menu menu;
	
	
	public static BufferedImage sprite_sheet;
	public static boolean paused = false;
	
	public enum STATE{
		Menu,
		Help,
		Game,
		End
	}
	
	public static STATE gameState = STATE.Menu;
	
	public Game() {
		handler = new Handler();
		hud = new HUD();
		menu = new Menu(handler, hud);
		this.addKeyListener(new KeyInput(handler));
		this.addMouseListener(menu);
		
		BufferedImageLoader loader = new BufferedImageLoader();
		
		sprite_sheet = loader.loadImage("/SpriteSheet.png");
		
		new Window(WIDTH, HEIGHT, "Spaceinvaders!",this);
		
		spawner = new Spawner(handler, hud);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				delta--;
			}
			if(running)
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				frames = 0;
			}
		}
		stop();
	}
	
	public static float clamp(float var, float min, float max) {
		if(var >= max) {
			return var = max;
		}else if(var <= min) {
			return var = min;
		}else {
			return var;
		}
	}
	
	public void tick() {
		
		if(gameState == STATE.Game) {
			if(!paused) {
				handler.tick();
				spawner.tick();
				
				if(HUD.live == 0) {
					gameState = STATE.End;
					handler.object.clear();
				}
			}
		}else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Help) {
			menu.tick();
			handler.tick();
		}
			
		
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(paused) {
			g.setFont(new Font("Arial", 1, 50));
			g.setColor(Color.WHITE);
			g.drawString("PAUSED", Game.WIDTH/2 - 100, Game.HEIGHT/2);
		}
		
		if(gameState == STATE.Game) {
			handler.render(g);
			hud.render(g);
		}else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End) {
			menu.render(g);
		}
		
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		new Game();
	}
	
}

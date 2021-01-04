package gameState;

import java.awt.*;
import java.util.ArrayList;

import Entity.*;
import Entity.Enemies.*;
import Entity.PlayerSave;
import handlers.Keys;
import main.GamePanel;
import tileMap.Background;
import tileMap.TileMap;

public class LevelOneState extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private ArrayList<Rectangle> tb;
	
	//private boolean eventDead;
	
	private HUD hud;
	private Teleport teleport;
	
	private boolean blockInput = false;
	
	public LevelOneState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		//tileMap is 30 pixels?
		tileMap = new TileMap(30);
		tileMap.loadTiles("/tilesets/grasstileset.gif");
		tileMap.loadMap("/maps/levelOne.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/backgrounds/grassbg1.gif", 0.1);
		
		// player
		player = new Player(tileMap);
		player.setPosition(100, 410);
		player.setHealth(PlayerSave.getHealth());
		
		// if entering level from menu
		if (gsm.getPrevState() == 0) {
			PlayerSave.setLives(3);
		}
		
		player.setLives(PlayerSave.getLives());
		
		populateEnemies();
		
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		tb = new ArrayList<Rectangle>();
		
		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(6950, 160);
		
		// test teleporter 
		//teleport.setPosition(200, 410);

		
		
	}
	
	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		Slugger s;
		Point[] points = new Point[] {
			//new Point (200, 400),
			//new Point(860, 200),
			//new Point(1525, 400),
			new Point(1550, 440),
			new Point(2720, 310),
			new Point(4400, 400),
			new Point(4500, 400),
			new Point(4600, 400),
			new Point(4700, 400),
			new Point(5240, 430),
			new Point(5480, 430),
			new Point(5720, 430),			
			};

		
		for (int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
		
	}
	public void update() {
		
		// check keys
		handleInput();
		
		// check if end of level event should start
				if(teleport.intersection(player)) {
					gsm.setState(GameStateManager.LEVEL_ONE_COMPLETE_STATE);
				}
				
		
		// check if player dead event
		
		// set background
		bg.setPosition(tileMap.getX(), tileMap.getY());
		
		// check if player dead event should start
		if(player.getHealth() == 0) {
			
			// player dies but still has lives
			if (player.getLives() > 0) {
				PlayerSave.setLives(player.getLives() - 1);
				gsm.setState(GameStateManager.DEAD_STATE);
			}
			// player no longer has lives
			else {
				gsm.setState(GameStateManager.GAME_OVER_STATE);
			}
		}
		
		player.update();

		// update tilemap
		tileMap.setPosition(
			GamePanel.WIDTH / 2 - player.getX(),
			GamePanel.HEIGHT / 2 - player.getY()
		);
		tileMap.update();
		tileMap.fixBounds();
		
	
		// attack enemies
		player.checkAttack(enemies);
		
		// update enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getX(), e.getY()));
			}
		}
		
		// update explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		
		// if player falls off map
		if (player.getY() > 480) {
			player.setPosition(100, 410);
			player.setHealth(player.getHealth() - 1);
		}
		
		teleport.update();
	}
	public void draw(Graphics2D g) {
		// clear screen
		g.setColor(Color.WHITE);
		 g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		// Draw background
		bg.draw(g);
		
		// Draw tilemap
		tileMap.draw(g);
		
		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}
		
		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int) tileMap.getX(), (int) tileMap.getY());
			explosions.get(i).draw(g);
		}
		
		teleport.draw(g);
		
		// draw player
		player.draw(g);
		
		hud.draw(g);
		
	
		
		// draw transition boxes
		g.setColor(java.awt.Color.BLACK);
		for(int i = 0; i < tb.size(); i++) {
				g.fill(tb.get(i));
			}
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(true);
		//if(blockInput || player.getHealth() == 0) return;
		//player.setUp(Keys.keyState[Keys.UP]);
		player.setLeft(Keys.keyState[Keys.LEFT]);
		player.setDown(Keys.keyState[Keys.DOWN]);
		player.setRight(Keys.keyState[Keys.RIGHT]);
		player.setJumping(Keys.keyState[Keys.JUMP]);
		player.setDashing(Keys.keyState[Keys.DASHING]);
		if(Keys.isPressed(Keys.ATTACK)) player.setScratching();
		if(Keys.isPressed(Keys.RESTART)) gsm.setState(GameStateManager.LEVEL_ONE_STATE);
	}
	
	// events //
	
	

	
}

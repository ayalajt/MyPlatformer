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

public class LevelTwoState extends GameState {

	private TileMap tileMap;
	private Background bg;
	
	private Player player;
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private ArrayList<Rectangle> tb;
	
	
	private HUD hud;
	private Teleport teleport;
	
	private boolean blockInput = false;
	
	public LevelTwoState(GameStateManager gsm) {
		super(gsm);
		init();
	}
	
	public void init() {
		//tileMap is 30 pixels?
		tileMap = new TileMap(30);
		tileMap.loadTiles("/tilesets/grasstileset.gif");
		tileMap.loadMap("/maps/levelTwo.map");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		
		bg = new Background("/backgrounds/levelBG.gif", 0.1);
		
		// player
		player = new Player(tileMap);
		player.setPosition(50, 170);

		player.setHealth(PlayerSave.getHealth());

		
		
		player.setLives(PlayerSave.getLives());
		
		populateEnemies();
		
		
		explosions = new ArrayList<Explosion>();
		
		hud = new HUD(player);
		
		tb = new ArrayList<Rectangle>();
		
		// teleport
		teleport = new Teleport(tileMap);
		teleport.setPosition(9225, 250);
		
		// test teleporter 
		//teleport.setPosition(200, 410);

		
		
	}
	
	private void populateEnemies() {
		enemies = new ArrayList<Enemy>();
		//Spikey s;
		Point[] points = new Point[] { new Point(300, 200)};

		
		for (int i = 0; i < points.length; i++) {
			//s = new Spikey(tileMap);
			//s.setPosition(points[i].x, points[i].y);
			//enemies.add(s);
		}
		
		BalloonSpikey t;
		t = new BalloonSpikey(tileMap);
		t.setPosition(900, 180);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(1020, 215);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(1260, 215);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(1525, 215);
		enemies.add(t);
		
		StaticSpikey d = new StaticSpikey(tileMap);
		d.setPosition(1835, 350);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(1856, 350);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2060, 290);
		enemies.add(d);

		d = new StaticSpikey(tileMap);
		d.setPosition(2081, 290);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2102, 290);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2123, 290);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2531, 225);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2562, 225);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2593, 225);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2624, 225);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(2655, 225);
		enemies.add(d);

		t = new BalloonSpikey(tileMap);
		t.setPosition(2760, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(2925, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(3105, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(3285, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(3470, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(3650, 200);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(3825, 210);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(4730, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4751, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4772, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4793, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(4853, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4874, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4895, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4916, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(4976, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(4997, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(5057, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(5078, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(5138, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(5198, 380);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(5258, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(5279, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(5300, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(5321, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(5342, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(6130, 160);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(6172, 160);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(6130, 260);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6151, 260);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6172, 260);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(6460, 130);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(6502, 130);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(6460, 230);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6481, 230);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6502, 230);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(6790, 260);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6811, 260);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6832, 260);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(6832, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6853, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6874, 200);
		enemies.add(d);		
		d = new StaticSpikey(tileMap);
		d.setPosition(6895, 200);
		enemies.add(d);		
		d = new StaticSpikey(tileMap);
		d.setPosition(6916, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6937, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6958, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(6979, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7000, 200);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(7060, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7081, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7102, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7123, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7144, 200);
		enemies.add(d);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(7204, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7225, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7246, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7267, 200);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7288, 200);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7580, 200);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7603, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7666, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(7603, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7624, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7646, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7666, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7755, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7818, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(7755, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7776, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7797, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7818, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7907, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7949, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(7991, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(7907, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7928, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7949, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7970, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(7991, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8083, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8146, 270);
		enemies.add(t);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8209, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(8083, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8104, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8125, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8146, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8167, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8188, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8209, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8261, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8324, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8387, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(8261, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8282, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8303, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8324, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8345, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8366, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8387, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8442, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8505, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8568, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(8442, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8463, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8484, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8505, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8526, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8547, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8568, 380);
		enemies.add(d);
		
		t = new BalloonSpikey(tileMap);
		t.setPosition(8623, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8686, 270);
		enemies.add(t);
		t = new BalloonSpikey(tileMap);
		t.setPosition(8749, 270);
		enemies.add(t);
		
		d = new StaticSpikey(tileMap);
		d.setPosition(8623, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8644, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8665, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8686, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8707, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8728, 380);
		enemies.add(d);
		d = new StaticSpikey(tileMap);
		d.setPosition(8749, 380);
		enemies.add(d);
	}
	public void update() {
		
		// check keys
		handleInput();
		
		// check if end of level event should start
				if(teleport.checkIntersection(player)) {
					gsm.setState(GameStateManager.LEVEL_TWO_COMPLETE_STATE);
				}
				
		
		
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
			player.setPosition(50, 170);
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
		if(blockInput || player.getHealth() == 0) return;
		//player.setUp(Keys.keyState[Keys.UP]);
		player.setLeft(Keys.keyState[Keys.LEFT]);
		player.setDown(Keys.keyState[Keys.DOWN]);
		player.setRight(Keys.keyState[Keys.RIGHT]);
		player.setJumping(Keys.keyState[Keys.JUMP]);
		player.setDashing(Keys.keyState[Keys.DASHING]);
		if(Keys.isPressed(Keys.ATTACK)) player.setAttacking();
		if(Keys.isPressed(Keys.RESTART)) gsm.setState(gsm.getCurrentState());
	}
	
	// events //
	
	

	
}
package Entity.Enemies;

import tileMap.TileMap;
import Entity.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class StaticSpikey extends Enemy {
	private ArrayList<BufferedImage[]> sprites;
	double origY;
	int maxFramesMoved = 100;
	int framesMovedCounter = 0;
	double floatingMoveSpeed = 0.15;
	int setDefault = 0;
	int turnLeft = 1;
	int turnRight = 2;
	int turnUpsideDown = 3;

	public StaticSpikey(TileMap tm) {
		super(tm);
		moveSpeed = 0;
		maxSpeed = 0;
		fallSpeed = 0.2; 
		maxFallSpeed = 10.0;
		
		width = 30;
		height = 30;
		//cwidth = 22;
		//cheight = 26;
		cwidth = 20;
		cheight = 24;
		
		// if left or right then swap cwidth and cheight
		
		health = maxHealth = 9999;
		damage = 1;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream(
							"/sprites_enemies/staticSpikey.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			// 1 animation
			for (int i = 0; i < 4; i++) {
				BufferedImage[] bi = new BufferedImage[1];
				bi[0] = spritesheet.getSubimage(i * width, 0, width, height);
				sprites.add(bi);
			}					
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites.get(setDefault));
		right = true;
		facingRight = true;
		
	}
	
	public void setLeft() {
		cwidth = 24;
		cheight = 20;
		animation.setFrames(sprites.get(turnLeft));

	}
	
	public void setRight() {
		cwidth = 24;
		cheight = 20;
		animation.setFrames(sprites.get(turnRight));

	}
	
	public void setUpsideDown() {

		animation.setFrames(sprites.get(turnUpsideDown));

	}
	private void getNextPosition() {	
				

		
	}
	
	public void update() {
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}
		
	
		
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		
		//if (notOnScreen()) return; 
		setMapPosition();
		super.draw(g);
		
	}
	
}


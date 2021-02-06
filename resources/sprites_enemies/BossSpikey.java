package Entity.Enemies;

import tileMap.TileMap;
import Entity.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class BossSpikey extends Enemy {
	private ArrayList<BufferedImage[]> sprites;
	boolean startMoving = false;
	boolean playerNearby = false;
	boolean playedOnce = false;
	public BossSpikey(TileMap tm) {
		super(tm);
		moveSpeed = 0.5;
		maxSpeed = 0.5;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		
		width = 60;
		height = 60;
		cwidth = 30;
		cheight = 30;
		
		health = maxHealth = 2;
		damage = 1;
		
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_enemies/BossSpikey.gif"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 3; i++) {
				BufferedImage[] actionSprites = new BufferedImage[1];
				actionSprites[0] = spritesheet.getSubimage(i * width, 0, width, height);
				sprites.add(actionSprites);
			}
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites.get(0));

	//	animation.setDelay(300);
		left = true;
		right = false;
		facingRight = false;
		
	}
	
	private void getNextPosition() {
		if (left) {
			dx = dx - moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		}
		else if (right) {
			dx = dx + moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		}
		
		// falling
		if (falling) {
			dy = dy + fallSpeed;
		}
	}
	
	public void update() {
		if (playerNearby == true && playedOnce == false) {
			animation.setFrames(sprites.get(1));
			playedOnce = true;
		}
		if (startMoving == true) {
			animation.setFrames(sprites.get(1));
			
			
			animation.setFrames(sprites.get(2));
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
			
			// if it hits a wall, then go opposite direction
			if (right && dx == 0) {
				right = false;
				left = true;
				facingRight = false;
			}
			else if (left && dx == 0) {
				right = true;
				left = false;
				facingRight = true;
			}
			
		}
		
		animation.update();

	
	
	}
	
	public void draw(Graphics2D g) {
		
		//if (notOnScreen()) return; 
		setMapPosition();
		super.draw(g);
		
	}
	
	public void startMoving() {
		startMoving = true;
	}
	
	public void playerNearby() {
		playerNearby = true;
	}
	
}

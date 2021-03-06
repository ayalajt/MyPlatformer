package Entity.Enemies;

import tileMap.TileMap;
import Entity.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The Spikey class is used as the default standard enemy, which moves a certain direction
 * until a hits a wall and then goes the opposite direction
 */
public class Spikey extends Enemy {
	private BufferedImage[] sprites;
	
	public Spikey(TileMap tm) {
		super(tm);
		moveSpeed = 0.5;
		maxSpeed = 0.5;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		width = 30;
		height = 30;
		cwidth = 22;
		cheight = 20;
		health = maxHealth = 2;
		damage = 1;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_enemies/spikey.gif"));
			sprites = new BufferedImage[1];
			sprites[0] = spritesheet.getSubimage(0, 0, width, height);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		animation = new Animation();
		animation.setFrames(sprites);
		right = true;
		facingRight = true;
		
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
		if (falling) {
			dy = dy + fallSpeed;
		}
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
		animation.update();
	}
	
	public void draw(Graphics2D g) {
		setMapPosition();
		super.draw(g);		
	}
}

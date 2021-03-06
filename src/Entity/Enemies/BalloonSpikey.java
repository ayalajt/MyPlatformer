package Entity.Enemies;

import tileMap.TileMap;
import Entity.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The Balloon Spikey class is used for the floating enemies that only move up and down
 */
public class BalloonSpikey extends Enemy {
	private BufferedImage[] sprites;
	int maxFramesMoved = 100;
	int framesMovedCounter = 0;
	double floatingMoveSpeed = 0.15;
	boolean goDown = true;
	boolean goUp = false;

	public BalloonSpikey(TileMap tm) {

		super(tm);
		moveSpeed = 0;
		maxSpeed = 0;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;
		width = 30;
		height = 90;
		cwidth = 22;
		cheight = 60;
		health = maxHealth = 2;
		damage = 1;

		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_enemies/BalloonSpikey.gif"));
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
		
		// to go down, increment the y coordinate by the floating move speed and increment
		// the frames moved counter until it hits the max frames counter. Once it hits the max
		// frames, set goDown to false and set goUp to true to then make the enemy float back up
		if (goDown) {
			if (framesMovedCounter != maxFramesMoved) {
				y = y + floatingMoveSpeed;
				framesMovedCounter++;
			} else {
				goDown = false;
				goUp = true;
				framesMovedCounter = 0;
			}
		}
		if (goUp) {
			if (framesMovedCounter != maxFramesMoved) {
				y = y - floatingMoveSpeed;
				framesMovedCounter++;
			} else {
				goUp = false;
				goDown = true;
				framesMovedCounter = 0;
			}
		}
	}

	public void update() {
		
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}
		animation.update();
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		super.draw(g);
	}
}
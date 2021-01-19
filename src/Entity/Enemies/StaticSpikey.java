package Entity.Enemies;

import tileMap.TileMap;
import Entity.*;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * The Static Spikey class is used for the stationary enemies that are big spikes.
 * It can be rotated left, right, upside down, or facing upwards
 */

public class StaticSpikey extends Enemy {
	private ArrayList<BufferedImage[]> sprites;
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
		cwidth = 20;
		cheight = 24;
				
		health = maxHealth = 9999;
		damage = 1;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_enemies/staticSpikey.gif"));
			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 4; i++) {
				BufferedImage[] image = new BufferedImage[1];
				image[0] = spritesheet.getSubimage(i * width, 0, width, height);
				sprites.add(image);
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
		cwidth = 20;
		cheight = 24;
		animation.setFrames(sprites.get(turnUpsideDown));
	}
	
	public void setDefault() {
		cwidth = 20;
		cheight = 24;
		animation.setFrames(sprites.get(turnUpsideDown));
	}
	
	public void update() {
		// update position
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
		setMapPosition();
		super.draw(g);		
	}
}
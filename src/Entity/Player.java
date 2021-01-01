package Entity;

import tileMap.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends MapObject {

	// player stuff
	private int health;
	private int maxHealth;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	private int lives;

	private boolean knockback;
	private boolean doubleJump;
	private boolean alreadyDoubleJump;
	private double doubleJumpStart;
	
	// scratch
	private boolean meleeAttack;
	private int scratchDamage;
	private int scratchRange;
	
	private boolean dashing;
	

	// animations
	private ArrayList<BufferedImage[]> sprites;
	
	// number of frames each animation has, with idle 
	// being the first
	private final int[] numFrames = {
		2, 8, 1, 2, 4, 2, 5	
	};
	
	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	//private static final int GLIDING = 4;
	//private static final int FIREBALL = 5;
	private static final int MELEE_ATTACK = 6;
	
	public Player(TileMap tm) {
		super(tm);
		// pixel height and width
		width = 30;
		height = 30;
		// real width and height
		cwidth = 20;
		cheight = 20;
		
		//moveSpeed = 0.5;
		//maxSpeed = 1.6;
		moveSpeed = 0.8;
		maxSpeed = 2;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4;
		stopJumpSpeed = 0.3;
		facingRight = true;
		doubleJumpStart = -4;
		
		health = maxHealth = 3;
	
		
		scratchDamage = 8;
		scratchRange = 40;
		
		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(
					getClass().getResourceAsStream("/sprites_player/playersprites.gif"));
			
			sprites = new ArrayList<BufferedImage[]>();
			// for 7 animation actions
			for (int i = 0; i < 7; i++) {
				BufferedImage[] bi = new BufferedImage[numFrames[i]];
				for (int j = 0; j < numFrames[i]; j++) {
					if (i != MELEE_ATTACK) {
						bi[j] = spritesheet.getSubimage(j * width,
								i * height, width, height);
					}
					else {
						bi[j] = spritesheet.getSubimage(j * width * 2,
							i * height, width * 2, height);
					}
				}
				sprites.add(bi);
			}
							
		} catch (Exception e) {
			
		}
	
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		
	}
	
	public int getHealth() { return health; }
	public int getMaxHealth() { return maxHealth; }
	public void setScratching() {
		meleeAttack = true;
	}
	public void setJumping(boolean b) {
		if(knockback) return;
		if(b && !jumping && falling && !alreadyDoubleJump) {
			doubleJump = true;
		}
		jumping = b;
	}
	
	public void setDashing(boolean b) {
		if(!b) dashing = false;
		else if(b && !falling) {
			dashing = true;
		}
	}
	public boolean isDashing() { return dashing; }
	
	public void checkAttack(ArrayList<Enemy> enemies) {
		
		// loop through enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			
			// scratch attack
			if (meleeAttack) {
				if (facingRight) {
					if ((e.getX() > x) && (e.getX() < x + scratchRange)
								&& (e.getY() > y - height / 2) && (e.getY() < y + height / 2)) {
							e.hit(scratchDamage);
						}
					}
				else {
						if (e.getX() < x && e.getX() > x - scratchRange
							&& e.getY() > y - height / 2
							&& e.getY() < y + height / 2) {
							e.hit(scratchDamage);
						}
				}
			}
			
			
			// check enemy collisions
			if (intersection(e)) {
				hit(e.getDamage());
			}
		}
		

		
	}
	
	public void hit(int damage) {
		if (flinching) return;
		health -= damage;
		if (health < 0) { health = 0;
		}
		if (health == 0) { dead = true; }
		flinching = true;
		flinchTimer = System.nanoTime();
		if(facingRight) dx = -1;
		else dx = 1;
		dy = -3;
		knockback = true;
		falling = true;
		jumping = false;
		
	}
	

	public void setHealth(int i) { health = i; }
	public void setLives(int i) { lives = i; }
	public void gainLife() { lives++; }
	public void loseLife() { lives--; }
	public int getLives() { return lives; }
	
	private void getNextPosition() {
		
		if(knockback) {
			dy += fallSpeed * 2;
			if(!falling) knockback = false;
			return;
		}
		

		double maxSpeed = this.maxSpeed;
		if(dashing) maxSpeed *= 1.50;
		
		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
			
		}
		else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
			
		}
		else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			}
			else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}
		
	
		
		// jumping
		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}
		
		// attacking while jumping/falling
		if (jumping && falling && meleeAttack) {
			if (animation.hasPlayedOnce()) {
				meleeAttack = false;
			}
		}
		
		// double jump 
		if(doubleJump) {
			dy = doubleJumpStart;
			alreadyDoubleJump = true;
			doubleJump = false;
		}
		
		if(!falling) alreadyDoubleJump = false;
		
		// falling
		if (falling) {
			dy += fallSpeed;
			if (dy > 0) jumping = false;
			if (dy < 0 && !jumping) dy += stopJumpSpeed;
			if (dy > maxFallSpeed) dy = maxFallSpeed;
			
		}
		
		
		
	}
	public void update() {
		
		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		
		// check attack has stopped
		if (currentAction == MELEE_ATTACK) {
			if (animation.hasPlayedOnce()) {
				meleeAttack = false;
			}
		}
	
		
		// check done flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			// 1 second
			if (elapsed > 1000) {
				flinching = false;
			}
		}
		// set animation
		if (meleeAttack) {
			if (currentAction != MELEE_ATTACK) {
				currentAction = MELEE_ATTACK;
				animation.setFrames(sprites.get(MELEE_ATTACK));
				animation.setDelay(50);
				width = 60;
			}
		}
		else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 30;
			}
		}
		else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				animation.setDelay(100);
				width = 30;
			}
		}
		else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(100);
				width = 30;
			}
		}
		
		animation.update();
		
		// set direction
		if (currentAction != MELEE_ATTACK) {
			if (right) facingRight = true;
			if (left) facingRight = false;
		}
	}
	public void draw(Graphics2D g) {
		setMapPosition();
		
	
		// draw player
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}
		
		super.draw(g);
		
	}

	
}

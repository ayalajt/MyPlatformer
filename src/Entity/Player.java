package Entity;

import tileMap.*;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/** 
 * The Player class contains the data and information
 * of the player object 
 */
public class Player extends MapObject {

	private int health;
	private int maxHealth;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;
	private int lives;

	private boolean knockback;
	private boolean doubleJump;
	private boolean alreadyDoubleJumping;
	private double doubleJumpStart;

	// scratch
	private boolean meleeAttack;
	private int meleeDamage;
	private int meleeRange;

	private boolean dashing;

	// animations
	private ArrayList<BufferedImage[]> sprites;

	// number of frames each animation has, with idle being the first
	private final int[] numFrames = {1, 5};

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 0;
	private static final int JUMPING = 0;
	private static final int MELEE_ATTACK = 1;

	public Player(TileMap tm) {
		super(tm);
		// pixel height and width
		width = 30;
		height = 30;
		// real width and height
		cwidth = 20;
		cheight = 20;

		// moveSpeed = 0.5;
		// maxSpeed = 1.6;
		moveSpeed = 0.8;
		maxSpeed = 2;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -4;
		stopJumpSpeed = 0.5;
		facingRight = true;
		doubleJumpStart = -4;

		health = maxHealth = 3;
		lives = 3;

		meleeDamage = 8;
		meleeRange = 40;

		// load sprites
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_player/player.gif"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 2; i++) {
				BufferedImage[] actionSprites = new BufferedImage[numFrames[i]];
				for (int j = 0; j < numFrames[i]; j++) {
					if (i != MELEE_ATTACK) {
						actionSprites[j] = spritesheet.getSubimage(j * width, i * height, width, height);
					} else {
						actionSprites[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
					}
				}
				sprites.add(actionSprites);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setAttacking() {
		meleeAttack = true;
	}

	public void setJumping(boolean value) {
		if (knockback) {
			return;
		}
		if (value && !jumping && falling && !alreadyDoubleJumping) {
			doubleJump = true;
		}
		jumping = value;
	}

	public void setDashing(boolean value) {
		if (!value) {
			dashing = false;
		}
		else if (value && !falling) {
			dashing = true;
		}
	}

	public boolean isDashing() {
		return dashing;
	}

	public void checkAttack(ArrayList<Enemy> enemies) {

		// loop through enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy currentEnemy = enemies.get(i);

			// scratch attack
			if (meleeAttack) {
				if (facingRight) {
					if ((currentEnemy.getX() > x) && (currentEnemy.getX() < x + meleeRange) && 
						(currentEnemy.getY() > y - height / 2) && 
						(currentEnemy.getY() < y + height / 2)) {
							currentEnemy.hit(meleeDamage);
					}
				} else {
					if ((currentEnemy.getX() < x) && 
						(currentEnemy.getX() > x - meleeRange) && 
						(currentEnemy.getY() > y - height / 2) && 
						(currentEnemy.getY() < y + height / 2)) {
							currentEnemy.hit(meleeDamage);
					}
				}
			}

			// check enemy collisions
			if (checkIntersection(currentEnemy)) {
				hit(currentEnemy.getDamage());
			}
		}
	}

	public void hit(int damage) {
		if (flinching) {
			return;
		}

		health = health - damage;
		if (health < 0) {
			health = 0;
		}
		if (health == 0) {
			dead = true;
		}

		flinching = true;
		flinchTimer = System.nanoTime();

		if (facingRight) {
			dx = -1;
		} else {
			dx = 1;
		}
		dy = -3;
		knockback = true;
		falling = true;
		jumping = false;

	}

	public void setHealth(int i) {
		health = i;
	}

	public void setLives(int i) {
		lives = i;
	}

	public void gainLife() {
		lives++;
	}

	public void loseLife() {
		lives--;
	}

	public int getLives() {
		return lives;
	}
	
	public boolean isDead() {
		return dead;
	}
	
	public void setDead() {
		dead = true;
	}
	

	private void getNextPosition() {

		if (knockback) {
			dy = dy + (fallSpeed * 2);
			if (!falling)
				knockback = false;
			return;
		}

		double maxSpeed = this.maxSpeed;

		if (dashing)
			maxSpeed = maxSpeed * 1.50;

		// movement
		if (left) {
			dx = dx - moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx = dx + moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx = dx - stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx = dx + stopSpeed;
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
		if (doubleJump) {
			dy = doubleJumpStart;
			alreadyDoubleJumping = true;
			doubleJump = false;
		}

		if (!falling) {
			alreadyDoubleJumping = false;
		}
		
		// falling
		if (falling) {
			dy = dy + fallSpeed;
			if (dy > 0) {
				jumping = false;
			}
			if (dy < 0 && !jumping) {
				dy += stopJumpSpeed;
			}
			if (dy > maxFallSpeed) {
				dy = maxFallSpeed;
			}
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
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				width = 30;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(sprites.get(WALKING));
				width = 30;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				width = 30;
			}
		}

		animation.update();

		// set direction
		if (currentAction != MELEE_ATTACK) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
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
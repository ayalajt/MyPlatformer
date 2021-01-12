package Entity;

import tileMap.TileMap;

/**
 * The Enemy class is used for each enemy in the game and contains the enemy's
 * basic information as well as dealing with its health when hit
 */
public class Enemy extends MapObject {

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	protected int damage;

	protected boolean flinching;
	protected long flinchTimer;

	public Enemy(TileMap tm) {
		super(tm);
	}

	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}

	public void hit(int damage) {
		if (dead || flinching) {
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
	}

	public void update() {
	}
}

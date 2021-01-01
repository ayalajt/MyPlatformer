package gameState;

import java.awt.Graphics2D;

public abstract class GameState {
	protected GameStateManager gsm;
	public GameState(GameStateManager gsm) {
		this.gsm = gsm;
	}
	public abstract void init();
	public abstract void update();
	public abstract void draw(Graphics2D g);
	public abstract void handleInput();



}

// TODO: 
// - Remove Gliding - DONE
// - Remove Fireball - DONE
// - Create new Player - DONE
// - Slowly incorporate new additions 
// - Tell Player how to move in introduction of first level
// - Create new Enemies 
// - Create Boss
// - Teleports?
// - Create Levels
// - Create HUD
// - Simple platformer where the player can dash, double jump, and melee
// - Add Events
// - Rename scratching to meleeAttack
// - Pause Tile
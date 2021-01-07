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
// - Tell Player how to move in introduction of first level - DONEISH, move some tiles
// - Create new Enemies, flying stationary balloon slugger
// - Create Boss
// - Teleport - DONE
// - Create Levels
// - Create HUD - DONE
// - Simple platformer where the player can dash, double jump, and melee
// - Add Events
// - Rename scratching to meleeAttack
// - Pause Tile
// - Update player when falling - DONE
// - Create Level One Complete State
// - Create Level Two
// - Pause States and DeadStates should have their own sprite bg
// - make level one a little longer and easier at the end, use the current ending as the level 2 start

// - update level 1, update level 1 tiles, create level 1 bg, update main menu bg and select text
// - menu select text can be a single image with black choices but when moving choices just make a yellow text mask
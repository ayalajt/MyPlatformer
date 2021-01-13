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
// - Three Levels Max, Final Boss 
// - Create Boss
// - Create Level Two Complete State
// - Add Balloon Spikeys to Level Two
// - Create Level Three
// - Pause States and DeadStates should have their own sprite bg
// - Add comments
// - Customize not on screen to make game less laggy
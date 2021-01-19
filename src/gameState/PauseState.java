package gameState;


import java.awt.Graphics2D;

import handlers.Keys;
import tileMap.Background;

public class PauseState extends GameState {
	
	private Background bg;
	private Background text;
	
	public PauseState(GameStateManager gsm) {
		
		super(gsm);
		
	
		
		try {
			bg = new Background("/backgrounds/pauseBackground.gif", 1);
			bg.setVector(-0.2, 0);
			text = new Background("/backgrounds/pauseText.gif", 1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		bg.update();
		text.update();
		handleInput();
	}
	
	public void draw(Graphics2D g) {
		bg.draw(g);
		text.draw(g);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(false);
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}

}
package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import handlers.Keys;
import main.GamePanel;
import tileMap.Background;

public class PauseState extends GameState {
	
	private Background bg;
	private Background text;
	
	public PauseState(GameStateManager gsm) {
		
		super(gsm);
		
	
		
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
			text = new Background("/backgrounds/pauseBackground.gif", 1);
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
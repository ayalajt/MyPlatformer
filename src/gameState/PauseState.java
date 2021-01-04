package gameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import handlers.Keys;
import main.GamePanel;
import tileMap.Background;

public class PauseState extends GameState {
	
	private Font font;
	private Background bg;

	
	public PauseState(GameStateManager gsm) {
		
		super(gsm);
		
		// fonts
		font = new Font("Arial", Font.PLAIN, 30);
		
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void init() {}
	
	public void update() {
		bg.update();
		handleInput();
	}
	
	public void draw(Graphics2D g) {
	//	g.setColor(Color.BLACK);
		//g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		bg.draw(g);
		g.setColor(Color.RED);
		g.setFont(font);
		g.drawString("Game Paused", 220, 90);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("Press ESC to go back to the level", 100, 400);
	}
	
	public void handleInput() {
		if(Keys.isPressed(Keys.ESCAPE)) gsm.setPaused(false);
		if(Keys.isPressed(Keys.ENTER)) {
			gsm.setPaused(false);
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}

}
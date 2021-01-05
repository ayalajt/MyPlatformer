package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

public class GameOverState extends GameState {
	
	private Background bg;
	private Background text;
	private int currentChoice = 0;
	private String[] options = { "Press ESC to go back to main menu"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font defaultFont;
	
	public GameOverState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
			titleColor = new Color(199, 8, 8);
			titleFont = new Font("SansSerif", Font.BOLD, 50);
			
			defaultFont = new Font("Arial", Font.PLAIN, 25);
			text = new Background("/backgrounds/gameOverBackground.gif",1);
			
		}
		catch(Exception e) {
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
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}
}
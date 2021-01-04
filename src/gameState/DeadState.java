package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

public class DeadState extends GameState {
	
	private Background bg;
	private int currentChoice = 0;
	private String[] options = { "Press Enter to restart the current level",
								 "Press ESC to go back to main menu"};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font defaultFont;
	
	public DeadState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
			titleColor = new Color(199, 8, 8);
			titleFont = new Font("SansSerif", Font.BOLD, 50);
			
			defaultFont = new Font("Arial", Font.PLAIN, 25);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void init() {}
	public void update() {
		bg.update();
		handleInput();
	}
	public void draw(Graphics2D g) {
		bg.draw(g);
		
		// Draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("You Died!", 190, 130);
		

		
		// Draw menu options
		g.setFont(defaultFont);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.BLACK);
				g.setFont(defaultFont);
				
			}
			// drawing options one after the other
			g.drawString(options[i], 100,400 + i * 40);
			
		}
	}

	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.JUMP)) {
			gsm.setState(gsm.getPrevState());
		}
		if(Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}
}
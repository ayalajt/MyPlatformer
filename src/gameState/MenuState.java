package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

public class MenuState extends GameState {
	
	private Background bg;
	private int currentChoice = 0;
	private String[] options = { "START", "QUIT" };
	
	private Color titleColor;
	private Font titleFont;
	private Font defaultBoldFont;
	
	private Font defaultFont;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
			titleColor = new Color(199, 8, 8);
			titleFont = new Font("SansSerif", Font.BOLD, 50);
			
			defaultFont = new Font("Arial", Font.PLAIN, 25);
			defaultBoldFont = new Font("Arial", Font.BOLD + Font.ITALIC, 25);
			
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
		g.drawString("Squared", 210, 130);
		

		
		// Draw menu options
		g.setFont(defaultFont);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				g.setColor(Color.BLACK);
				g.setFont(defaultBoldFont);
			}
			else {
				g.setColor(Color.LIGHT_GRAY);
				g.setFont(defaultFont);
				
			}
			// drawing options one after the other
			g.drawString(options[i],270,230 + i * 40);
			
		}
	}
	private void select() {
		
		// Start State
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL_ONE_STATE);
		}
		if(currentChoice == 1) {
			System.exit(0);
		}
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.JUMP)) select();
		if(Keys.isPressed(Keys.UP)) {
			if(currentChoice > 0) {
				currentChoice--;
			}
		}
		if(Keys.isPressed(Keys.DOWN)) {
			if(currentChoice < options.length - 1) {
				currentChoice++;
			}
		}
	}
}

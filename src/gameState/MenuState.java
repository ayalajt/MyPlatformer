package gameState;

import java.awt.*;
import java.io.File;

import handlers.Keys;
import tileMap.Background;

public class MenuState extends GameState {
	
	private Background bg;
	private Background title;
	private int currentChoice = 0;
	private String[] options = { "START", "QUIT" };
	
	private Font defaultBoldFont;
	
	private Font defaultFont;
	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = new Background("/backgrounds/menubg.gif", 1);
			title = new Background("/backgrounds/title.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
			
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
		title.update();
		handleInput();
	}
	public void draw(Graphics2D g) {

		bg.draw(g);
		title.draw(g);
		

		
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
			g.drawString(options[i],270,340 + i * 40);
			
		}
	}
	private void select() {
		
		// Start State
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL_ONE_STATE);
			
			//debug level 2
			//gsm.setState(GameStateManager.LEVEL_TWO_STATE);
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

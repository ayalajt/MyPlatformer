package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

public class LevelOneCompleteState extends GameState {
	
	private Background bg;
	private Background text;
	private int currentChoice = 0;
	
	public LevelOneCompleteState(GameStateManager gsm) {
		super(gsm);
		try {
			bg = new Background("/backgrounds/menuBackground.gif", 1);
			text = new Background("/backgrounds/LevelOneCompleteText.gif", 1);
			// changed from -0.1 originally
			bg.setVector(-0.4, 0);
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
	private void select() {
		
		// Start State
		if(currentChoice == 0) {
			gsm.setState(GameStateManager.LEVEL_TWO_STATE);
		}
	}
	public void handleInput() {
		if(Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.JUMP)) select();
	}
}
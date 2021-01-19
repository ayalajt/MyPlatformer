package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

/**
 * The Dead State class is used when the player dies and it simply contains the
 * text and background image
 */
public class DeadState extends GameState {

	private Background background;
	private Background text;

	public DeadState(GameStateManager gsm) {
		super(gsm);
		try {
			background = new Background("/backgrounds/menuBackground.gif", 1);
			background.setVector(-0.2, 0);
			text = new Background("/backgrounds/deadBackground.gif", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
	}

	public void update() {
		text.update();
		background.update();
		handleInput();
	}

	public void draw(Graphics2D g) {
		background.draw(g);
		text.draw(g);
	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ENTER) || Keys.isPressed(Keys.JUMP)) {
			gsm.setState(gsm.getPrevState());
		}
	}
}
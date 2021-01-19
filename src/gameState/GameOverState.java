package gameState;

import java.awt.*;
import handlers.Keys;
import tileMap.Background;

/**
 * The Game Over State is used when the player runs out of lives and it is
 * simply a text and background image
 *
 */
public class GameOverState extends GameState {

	private Background background;
	private Background text;

	public GameOverState(GameStateManager gsm) {
		super(gsm);
		try {
			background = new Background("/backgrounds/menuBackground.gif", 1);
			background.setVector(-0.2, 0);
			text = new Background("/backgrounds/gameOverBackground.gif", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
	}

	public void update() {
		background.update();
		text.update();
		handleInput();
	}

	public void draw(Graphics2D g) {
		background.draw(g);
		text.draw(g);

	}

	public void handleInput() {
		if (Keys.isPressed(Keys.ESCAPE)) {
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}
}
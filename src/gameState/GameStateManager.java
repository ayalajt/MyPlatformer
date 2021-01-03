package gameState;

import java.awt.Color;
import java.util.ArrayList;

import main.GamePanel;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	
	private PauseState pauseState;
	private boolean paused;
	
	public static final int NUM_GAME_STATES = 4;
	public static final int MENU_STATE = 0;
	public static final int LEVEL_ONE_STATE = 1;
	public static final int LEVEL_ONE_COMPLETE_STATE = 2;
	public static final int LEVEL_TWO_STATE = 3;
	
	public GameStateManager() {
		gameStates = new GameState[NUM_GAME_STATES];
		pauseState = new PauseState(this);
		paused = false;
		currentState = MENU_STATE;
		loadState(currentState);
	}
	
	private void loadState(int state) {
		if(state == MENU_STATE) {
			gameStates[state] = new MenuState(this);
		}
		if (state == LEVEL_ONE_STATE) {
			gameStates[state] = new LevelOneState(this);
		}
		if (state == LEVEL_ONE_COMPLETE_STATE) {
			gameStates[state] = new LevelOneCompleteState(this);
		}
		if (state == LEVEL_TWO_STATE) {
			gameStates[state] = new LevelTwoState(this);
		}
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
	
	public void setPaused(boolean b) { 
		paused = b; 
	}
	
	public void update() {
		if(paused) {
			pauseState.update();
			return;
		}
		if (gameStates[currentState] != null) {
			gameStates[currentState].update();
		}
		
	}
	
	public void draw(java.awt.Graphics2D g) {
		if (paused) {
			pauseState.draw(g);
			return;
		}
		if (gameStates[currentState] != null) {
			gameStates[currentState].draw(g);
		} else {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		}
	}	
}

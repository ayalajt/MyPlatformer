package gameState;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	private int prevState;
	
	private PauseState pauseState;
	private boolean paused;
	
	public static final int NUM_GAME_STATES = 6;
	public static final int MENU_STATE = 0;
	public static final int DEAD_STATE = 1;
	public static final int GAME_OVER_STATE = 2;
	public static final int LEVEL_ONE_STATE = 3;
	public static final int LEVEL_ONE_COMPLETE_STATE = 4;
	public static final int LEVEL_TWO_STATE = 5;
	public static final int LEVEL_TWO_COMPLETE_STATE = 6;
	public static final int LEVEL_THREE_STATE = 7;
	public static final int LEVEL_THREE_COMPLETE_STATE = 8;
	
	public GameStateManager() {
		gameStates = new GameState[NUM_GAME_STATES];
		pauseState = new PauseState(this);
		paused = false;
		currentState = MENU_STATE;
		prevState = currentState;
		loadState(currentState);
	}
	
	public int getCurrentState() {
		return currentState;
	}
	

	public int getPrevState() {
		return prevState;
	}
	
	private void loadState(int state) {
		if(state == MENU_STATE) {
			gameStates[state] = new MenuState(this);
		}
		if (state == DEAD_STATE) {
			gameStates[state] = new DeadState(this);
		}
		if (state == GAME_OVER_STATE) {
			gameStates[state] = new GameOverState(this);
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
		if (state == LEVEL_TWO_COMPLETE_STATE) {
			gameStates[state] = new LevelTwoCompleteState(this);
		}
		
		
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		prevState = currentState;
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
	
	public void draw(Graphics2D g) {
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

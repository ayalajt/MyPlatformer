package handlers;

import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

// this class contains a boolean array of current and previous key states
// for the 10 keys that are used for this game.
// a key k is down when keyState[k] is true.


public class Keys {
	
	public static final int NUM_KEYS = 16;
	
	public static boolean keyState[] = new boolean[NUM_KEYS];
	public static boolean prevKeyState[] = new boolean[NUM_KEYS];
	
	public static int UP = 0;
	public static int LEFT = 1;
	public static int DOWN = 2;
	public static int RIGHT = 3;
	public static int JUMP = 4;
	public static int ATTACK = 5;
	public static int RESTART = 6;
	public static int DASHING = 7;
	public static int ENTER = 8;
	public static int ESCAPE = 9;
	
	
	
	public static void keySet(int i, boolean keyPressed)  {
		if(i == KeyEvent.VK_W) {
			keyState[UP] = keyPressed;
			keyState[ATTACK] = keyPressed;
		}
		else if(i == KeyEvent.VK_A) {
			keyState[LEFT] = keyPressed;
		}
		else if(i == KeyEvent.VK_S) {
			keyState[DOWN] = keyPressed;
		}
		else if(i == KeyEvent.VK_D) {
			keyState[RIGHT] = keyPressed;
		}
		else if(i == KeyEvent.VK_SPACE) {
			keyState[JUMP] = keyPressed;
		}
		else if(i == KeyEvent.VK_R) { 
			keyState[RESTART] = keyPressed;
		}
		else if(i == KeyEvent.VK_SHIFT) { 
			keyState[DASHING] = keyPressed;
		}
		else if(i == KeyEvent.VK_ENTER) { 
			keyState[ENTER] = keyPressed;
		}
		else if(i == KeyEvent.VK_ESCAPE) { 
			keyState[ESCAPE] = keyPressed;
		}
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KEYS; i++) {
			prevKeyState[i] = keyState[i];
		}
	}
	
	public static boolean isPressed(int i) {
		return keyState[i] && !prevKeyState[i];
	}
	
	public static boolean anyKeyPress() {
		for(int i = 0; i < NUM_KEYS; i++) {
			if(keyState[i]) return true;
		}
		return false;
	}
	
}
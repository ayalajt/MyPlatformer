package Entity;

public class PlayerSave {
	
	private static int lives = 3;
	private static int health = 3;
	
	public static void init() {
		lives = 3;
		health = 3;
	}
	
	public static int getLives() { return lives; }
	public static void setLives(int i) { lives = i; }
	
	public static int getHealth() { return health; }
	public static void setHealth(int i) { health = i; }
	
}

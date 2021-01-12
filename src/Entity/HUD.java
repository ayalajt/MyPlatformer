package Entity;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The HUD class is a simple overlay over the player's screen that contains the
 * number of lives and the player's current health
 */
public class HUD {
	private Player player;
	private BufferedImage image;
	private Font font;

	public HUD(Player currentPlayer) {
		player = currentPlayer;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/hud/hud.gif"));
			font = new Font("Arial", Font.PLAIN, 14);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void draw(Graphics2D graphics) {
		graphics.drawImage(image, 0, 10, null);
		graphics.setFont(font);
		graphics.setColor(Color.WHITE);
		graphics.drawString(player.getHealth() + "/" + player.getMaxHealth(), 25, 25);
		graphics.drawString("x " + String.valueOf(player.getLives()), 25, 45);
	}
}

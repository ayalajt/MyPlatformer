package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * The Explosion class is used when an enemy dies and a small explosion gif is played once
 */
public class Explosion {
	private int x;
	private int y;
	private int xMap;
	private int yMap;
	private int spriteWidth;
	private int spriteHeight;

	private Animation explosionAnimation;
	private BufferedImage[] explosionSprites;

	private boolean remove;

	public Explosion(int x, int y) {
		this.x = x;
		this.y = y;

		spriteWidth = 30;
		spriteHeight = 30;

		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_enemies/explosion.gif"));

			// 6 frames
			explosionSprites = new BufferedImage[6];
			for (int i = 0; i < explosionSprites.length; i++) {
				explosionSprites[i] = spritesheet.getSubimage(i * spriteWidth, 0, spriteWidth, spriteHeight);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		explosionAnimation = new Animation();
		explosionAnimation.setFrames(explosionSprites);
		explosionAnimation.setDelay(70);

	}

	public void update() {
		explosionAnimation.update();
		if (explosionAnimation.hasPlayedOnce()) {
			remove = true;
		}
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void setMapPosition(int x, int y) {
		xMap = x;
		yMap = y;
	}

	public void draw(Graphics2D graphics) {
		graphics.drawImage(explosionAnimation.getImage(), x + xMap - spriteWidth / 2, y + yMap - spriteHeight / 2, null);
	}

}

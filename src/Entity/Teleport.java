package Entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import tileMap.TileMap;

/**
 * The Teleport state is used to create a sprite of a teleporter
 * that is used in the game to teleport the player to the next state
 *
 */
public class Teleport extends MapObject {

	private BufferedImage[] teleporterSprites;

	public Teleport(TileMap tm) {
		super(tm);
		facingRight = true;
		width = height = 40;
		cwidth = 20;
		cheight = 40;
		try {
			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/sprites_player/Teleport.gif"));
			teleporterSprites = new BufferedImage[2];
			for (int i = 0; i < teleporterSprites.length; i++) {
				teleporterSprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}
			animation = new Animation();
			animation.setFrames(teleporterSprites);
			animation.setDelay(400);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void update() {
		animation.update();
	}

	public void draw(Graphics2D g) {
		setMapPosition();
		super.draw(g);
	}

}
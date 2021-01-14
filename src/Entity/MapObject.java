package Entity;

import tileMap.Tile;
import tileMap.TileMap;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import main.GamePanel;

/**
 * The MapObject class is used as the basis of every enemy and player;
 * it contains their respective coordinates, information on any actions such
 * as jumping or falling, and contains collision information by checking the coordinates
 * of two rectangles
 */
public abstract class MapObject {

	// tile information
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;

	// object's position and vector speed
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;

	// object's dimensions
	protected int width;
	protected int height;

	// object's actual collision box
	protected int cwidth;
	protected int cheight;

	// collision information
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;

	// animation information
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;

	// movement information
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;

	// movement attributes
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	// constructor
	public MapObject(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	// collision checker
	public boolean checkIntersection(MapObject otherObject) {
		Rectangle rectOne = getRectangle();
		Rectangle rectTwo = otherObject.getRectangle();
		return rectOne.intersects(rectTwo);
	}

	public Rectangle getRectangle() {
		int xCoords = (int) x - cwidth;
		int yCoords = (int) y - cheight;
		return new Rectangle(xCoords, yCoords, cwidth, cheight);
	}

	public void calculateCorners(double x, double y) {
		int leftTile = (int) (x - cwidth / 2) / tileSize;
		int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
		int topTile = (int) (y - cheight / 2) / tileSize;
		int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

		// out of bounds fix
		if (topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()) {
			topLeft = topRight = bottomLeft = bottomRight = false;
			return;
		}

		int tLeft = tileMap.getType(topTile, leftTile);
		int tRight = tileMap.getType(topTile, rightTile);
		int bLeft = tileMap.getType(bottomTile, leftTile);
		int bRight = tileMap.getType(bottomTile, rightTile);

		// if you jumped and hit a wall to your top left, then topleft
		// would be true
		topLeft = tLeft == Tile.BLOCKED;
		topRight = tRight == Tile.BLOCKED;
		bottomLeft = bLeft == Tile.BLOCKED;
		bottomRight = bRight == Tile.BLOCKED;

	}

	public void checkTileMapCollision() {
		currCol = (int) x / tileSize;
		currRow = (int) y / tileSize;
		xdest = x + dx;
		ydest = y + dy;
		xtemp = x;
		ytemp = y;

		// y direction

		calculateCorners(x, ydest);
		
		// going up
		if (dy < 0) {
			if (topLeft || topRight) {
				dy = 0;
				ytemp = currRow * tileSize + cheight / 2;

			} else {
				ytemp = ytemp + dy;
			}
		}
		// going down
		if (dy > 0) {
			if (bottomLeft || bottomRight) {
				dy = 0;
				falling = false;
				ytemp = (currRow + 1) * tileSize - cheight / 2;
			} else {
				ytemp = ytemp + dy;
			}
		}

		// x direction

		calculateCorners(xdest, y);
		
		// left
		if (dx < 0) {
			if (topLeft || bottomLeft) {
				dx = 0;
				xtemp = currCol * tileSize + cwidth / 2;
			} else {
				xtemp = xtemp + dx;
			}
		}
		// right
		if (dx > 0) {
			if (topRight || bottomRight) {
				dx = 0;
				xtemp = (currCol + 1) * tileSize - cwidth / 2;
			} else {
				xtemp = xtemp + dx;
			}
		}

		if (!falling) {
			
			// check if fell
			calculateCorners(x, ydest + 1);

			// not standing on anything, so falling
			if (!bottomLeft && !bottomRight) {
				falling = true;
			}
		}
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCWidth() {
		return cwidth;
	}

	public int getCHeight() {
		return cheight;
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setVector(double dx, double dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public void setMapPosition() {
		xmap = tileMap.getX();
		ymap = tileMap.getY();
	}

	public void setLeft(boolean b) {
		left = b;
	}

	public void setRight(boolean b) {
		right = b;
	}

	public void setUp(boolean b) {
		up = b;
	}

	public void setDown(boolean b) {
		down = b;
	}

	public void setJumping(boolean b) {
		jumping = b;
	}

	public boolean notOnScreen() {
		
		// returns whether map object is on screen
		return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y + ymap + height < 0
				|| y + ymap - height > GamePanel.HEIGHT;
	}

	public void draw(Graphics2D g) {
		if (facingRight) {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
		} else {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2),
					-width, height, null);

		}
	}
}

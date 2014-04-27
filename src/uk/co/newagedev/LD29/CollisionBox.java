package uk.co.newagedev.LD29;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;

import uk.co.newagedev.LD29.states.GameState;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class CollisionBox {

	private int width, height;
	private Location location;
	private boolean solid;
	private Rectangle box;

	public CollisionBox(Location location, int width, int height, boolean solid) {
		this.width = width;
		this.height = height;
		this.location = location;
		this.solid = solid;
		if (location != null) {
			box = new Rectangle(location.getX(), location.getY(), width, height);
		}
	}

	public Direction collidesInWhichDirection(CollisionBox box) {
		if (this.box.intersects(box.box)) {
			Rectangle inter = (Rectangle) this.box.createIntersection(box.box);
			Rectangle upperInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY(), width, height / 2));
			Rectangle lowerInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY() + (height / 2), width, height / 2));
			Rectangle leftInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY(), width / 2, height));
			Rectangle rightInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX() + (width / 2), location.getY(), width / 2, height));
			int up = (int) (upperInter.getWidth() * upperInter.getHeight());
			int down = (int) (lowerInter.getWidth() * lowerInter.getHeight());
			int left = (int) (leftInter.getWidth() * leftInter.getHeight());
			int right = (int) (rightInter.getWidth() * rightInter.getHeight());
			int largest = Math.max(Math.max(up, down), Math.max(left, right));
			if (largest == up) {
				return Direction.UP;
			} else if (largest == right) {
				return Direction.RIGHT;
			} else if (largest == down) {
				return Direction.DOWN;
			} else if (largest == left) {
				return Direction.LEFT;
			}
		}
		return Direction.INVALID_DIRECTION;
	}

	public Rectangle getRect() {
		return box;
	}

	public boolean collides(CollisionBox box, Direction dir) {
		if (solid && box.isSolid()) {
			return collidesInWhichDirection(box) == dir;
		}
		return false;
	}

	public void drawBox(int scrollX, int scrollY) {
		Main.getScreen().displayRect(location.getScreenLocationX(scrollX), location.getScreenLocationY(scrollY), width, height, Color.LIGHT_GRAY);
	}

	public boolean collidesInDirection(Direction dir) {
		if (solid) {
			ArrayList<CollisionBox> boxes = ((GameState) Main.getScreen().getCurrentState()).getMap().getCollisionBoxesInRange(location, 64);
			for (CollisionBox box : boxes) {
				if (box != null) {
					if (collides(box, dir)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isSolid() {
		return solid;
	}

	public Location getLocation() {
		return location;
	}

	public void move(int x, int y) {
		location.move(x, y);
		box = new Rectangle(location.getX(), location.getY(), width, height);
	}

	public void setLocation(Location location) {
		this.location = location;
		box = new Rectangle(location.getX(), location.getY(), width, height);
	}
}

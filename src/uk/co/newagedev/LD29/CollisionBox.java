package uk.co.newagedev.LD29;

import java.awt.Rectangle;
import java.util.ArrayList;

import uk.co.newagedev.LD29.entities.Entity;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class CollisionBox {

	private int width, height;
	private Location location;
	private boolean up, down, left, right, collidesWithEntities, collidesWithTiles, entity;
	private Rectangle box;

	public CollisionBox(Location location, int width, int height, boolean entity, boolean solid, boolean collidesWithEntities, boolean collidesWithTiles) {
		this(location, width, height, entity, solid, solid, solid, solid, collidesWithEntities, collidesWithTiles);
	}

	public CollisionBox(Location location, int width, int height, boolean entity, boolean up, boolean down, boolean left, boolean right, boolean collidesWithEntities, boolean collidesWithTiles) {
		this.width = width;
		this.height = height;
		this.location = location;
		this.up = up;
		this.down = down;
		this.left = left;
		this.right = right;
		this.entity = entity;
		this.collidesWithEntities = collidesWithEntities;
		this.collidesWithTiles = collidesWithTiles;
		if (location != null) {
			box = new Rectangle(location.getX(), location.getY(), width, height);
		}
	}

	public CollisionBox(Location location, int width, int height, boolean entity, boolean solid) {
		this(location, width, height, entity, solid, solid, solid, solid, true, true);
	}

	public CollisionBox(Location location, int width, int height, boolean entity, boolean up, boolean down, boolean left, boolean right) {
		this(location, width, height, entity, up, down, left, right, true, true);
	}

	public void setSolid(boolean solid) {
		up = solid;
		down = solid;
		left = solid;
		right = solid;
	}

	public boolean getCollidesWithEntities() {
		return collidesWithEntities;
	}

	public boolean getCollidesWithTiles() {
		return collidesWithTiles;
	}

	public void setCollidesWithEntities(boolean collidesWithEntities) {
		this.collidesWithEntities = collidesWithEntities;
	}

	public void setCollidesWithTiles(boolean collidesWithTiles) {
		this.collidesWithTiles = collidesWithTiles;
	}

	public void setSolid(Direction dir, boolean solid) {
		if (dir == Direction.UP)
			up = solid;
		if (dir == Direction.DOWN)
			down = solid;
		if (dir == Direction.LEFT)
			left = solid;
		if (dir == Direction.RIGHT)
			right = solid;
	}

	public boolean collides(CollisionBox box, Direction dir) {
		if (isSolid() && box.isSolid()) {
			if (this.box.intersects(box.box)) {
				Rectangle inter = (Rectangle) this.box.createIntersection(box.box);
				Rectangle upperInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY(), width, height / 2));
				Rectangle lowerInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY() + (height / 2), width, height / 2));
				Rectangle leftInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX(), location.getY(), width / 2, height));
				Rectangle rightInter = (Rectangle) inter.createIntersection(new Rectangle(location.getX() + (width / 2), location.getY(), width / 2, height));
				int upVal = (int) (upperInter.getWidth() * upperInter.getHeight());
				int downVal = (int) (lowerInter.getWidth() * lowerInter.getHeight());
				int leftVal = (int) (leftInter.getWidth() * leftInter.getHeight());
				int rightVal = (int) (rightInter.getWidth() * rightInter.getHeight());
				int largest = Math.max(Math.max(upVal, downVal), Math.max(leftVal, rightVal));
				if (largest == upVal && up) {
					return Direction.UP == dir;
				} else if (largest == rightVal && right) {
					return Direction.RIGHT == dir;
				} else if (largest == downVal && down) {
					return Direction.DOWN == dir;
				} else if (largest == leftVal && left) {
					return Direction.LEFT == dir;
				}
			}
		}
		return false;
	}

	public boolean getIsEntity() {
		return entity;
	}

	public boolean collidesInDirection(Direction dir, Entity entity) {
		if (isSolidInDirection(dir)) {
			ArrayList<CollisionBox> boxes = Main.getScreen().getCurrentState().getMap().getCollisionBoxesInRange(location, 64, collidesWithEntities, collidesWithTiles);
			for (CollisionBox box : boxes) {
				if (box != null) {
					if (box.getCollidesWithEntities() && getIsEntity() || box.getCollidesWithTiles() && !getIsEntity()) {
						if (box != entity.getCollisionBox()) {
							if (collides(box, dir)) {
								return true;
							}
						}
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

	public boolean isSolidInDirection(Direction dir) {
		if (dir == Direction.UP)
			return up;
		if (dir == Direction.DOWN)
			return down;
		if (dir == Direction.LEFT)
			return left;
		if (dir == Direction.RIGHT)
			return right;
		return false;
	}

	public boolean isSolid() {
		return up || down || left || right;
	}

	public Location getLocation() {
		return location;
	}

	public void move(int x, int y) {
		setLocation(getLocation().add(x, y));
	}

	public void setLocation(Location location) {
		this.location = location;
		box = new Rectangle(location.getX(), location.getY(), width, height);
	}

	public boolean collidesInAnyDirection(Entity entity) {
		for (Direction direction : Direction.values()) {
			if (direction != Direction.INVALID_DIRECTION) {
				if (collidesInDirection(direction, entity)) {
					return true;
				}
			}
		}
		return false;
	}
}
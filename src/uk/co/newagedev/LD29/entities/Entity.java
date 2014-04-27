package uk.co.newagedev.LD29.entities;

import java.util.HashMap;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.graphics.AnimatedSprite;
import uk.co.newagedev.LD29.graphics.AnimationType;
import uk.co.newagedev.LD29.graphics.Sprite;
import uk.co.newagedev.LD29.states.GameState;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class Entity {

	private Location location;
	private CollisionBox box;
	private int health;
	private HashMap<AnimationType, AnimatedSprite> animations = new HashMap<AnimationType, AnimatedSprite>();
	private AnimatedSprite currentAnimation;

	public Entity(Location location) {
		this.location = location;
	}

	public int getHealth() {
		return health;
	}

	public Location getLocation() {
		return location;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public CollisionBox getCollisionBox() {
		return box;
	}

	public void render() {
		if (currentAnimation != null) {
			currentAnimation.runAnimation();
		} else if (animations.size() > 0) {
			if (animations.get(AnimationType.IDLE) != null) {
				currentAnimation = animations.get(AnimationType.IDLE);
				currentAnimation.startAnimation();
			}
		}
		if (location != null) {
			Main.getScreen().displaySprite(location.getScreenLocationX(((GameState) (Main.getScreen().getCurrentState())).getMap().getScrollX()), location.getScreenLocationY(((GameState) (Main.getScreen().getCurrentState())).getMap().getScrollY()), currentAnimation.getCurrentSprite());
		}
	}
	
	public void setCollisionBox(CollisionBox collisionBox) {
		box = collisionBox;
	}

	public void addAnimation(AnimationType type, AnimatedSprite sprite) {
		animations.put(type, sprite);
	}

	public AnimatedSprite getCurrentAnimation() {
		return currentAnimation;
	}
	
	public Sprite getSprite() {
		return currentAnimation.getCurrentSprite();
	}
	
	public void setCurrentAnimation(AnimationType type) {
		if (animations.containsKey(type)){
			currentAnimation = animations.get(type);
		}	
	}

	public void update() {
	}

	public void move(int x, int y) {
		location.setLocation(location.getX() + x, location.getY() + y);
	}

	public void tryMove(int x, int y) {
		box.move(x, y);
		if (box.collidesInDirection(Direction.getDirectionFromMove(x, y))) {
			box.move(-x, -y);
		} else {
			move(x, y);
		}
	}
}

package uk.co.newagedev.LD29.entities;

import java.util.HashMap;
import java.util.Random;

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
	private int health, maxHealth, age, attackCooldown;
	private boolean onGround, alive, ages;
	private HashMap<AnimationType, AnimatedSprite> animations = new HashMap<AnimationType, AnimatedSprite>();
	private AnimatedSprite currentAnimation;
	private Direction dir;
	private int jumping;
	public Random rand;

	public void setJumping(int jumping) {
		this.jumping = jumping;
	}

	public int getJumping() {
		return jumping;
	}
	
	public int getAttackCooldown() {
		return attackCooldown;
	}
	
	public void setAttackCooldown(int attackCooldown) {
		this.attackCooldown = attackCooldown;
	}

	public boolean ages() {
		return ages;
	}

	public void setAges(boolean ages) {
		this.ages = ages;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Entity(Location location) {
		this();
		setLocation(location);
	}

	public Entity() {
		rand = new Random();
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setDirection(Direction dir) {
		this.dir = dir;
	}

	public Direction getDirection() {
		return dir;
	}
	
	public void attack(Entity entity, int amount) {
		if (attackCooldown > 0) {
			attackCooldown--;
		} else if (isAlive() && entity.isAlive()) {
			if (entity.getHealth() > 0) {
				entity.damage(amount);
				attackCooldown = 30;
			}
		}
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setLocation(Location location) {
		this.location = location;
		if (box != null)
			box.setLocation(location);
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void kill() {
		health = 0;
		alive = false;
	}

	public void damage(int health) {
		if (alive) {
			this.health -= health;
			((GameState) Main.getScreen().getCurrentState()).getMap().spawnBlood(getLocation(), rand.nextInt(4) + 4);
			if (this.health <= 0)
				alive = false;
		}
	}

	public void heal(int health) {
		if (alive) {
			this.health += health;
			if (this.health > maxHealth)
				this.health = maxHealth;
		}
	}

	public int getHealth() {
		return health;
	}

	public boolean isOnGround() {
		return onGround;
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
			Main.getScreen().displaySprite(location.getScreenLocationX(Main.getScreen().getCurrentState().getMap().getScrollX()), location.getScreenLocationY(Main.getScreen().getCurrentState().getMap().getScrollY()), currentAnimation.getCurrentSprite());
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
		if (animations.containsKey(type)) {
			currentAnimation = animations.get(type);
		}
	}

	public void update() {
		if (ages) {
			if (age > 0) {
				age--;
			} else {
				kill();
			}
		}
		if (!alive) {
			((GameState) Main.getScreen().getCurrentState()).getMap().despawnEntity(this);
		}
		box.move(0, 5);
		onGround = box.collidesInDirection(Direction.DOWN, this);
		box.move(0, -5);
		jumping--;
		if (jumping > 15) {
			move(Direction.UP, -(jumping / 7));
		} else {
			if (!onGround) {
				move(Direction.DOWN, (jumping / 5));
			} else {
				jumping = 0;
			}
		}
	}

	public void move(Direction dir, int amount) {
		int x = 0, y = 0;
		if (dir == Direction.UP)
			y = amount;
		if (dir == Direction.DOWN)
			y = -amount;
		if (dir == Direction.LEFT)
			x = -amount;
		if (dir == Direction.RIGHT)
			x = amount;
		location.add(x, y);
		box.setLocation(location);
	}
}

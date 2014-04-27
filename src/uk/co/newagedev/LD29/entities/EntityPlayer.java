package uk.co.newagedev.LD29.entities;

import java.awt.event.KeyEvent;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.graphics.AnimatedSprite;
import uk.co.newagedev.LD29.graphics.AnimationType;
import uk.co.newagedev.LD29.maps.Map;
import uk.co.newagedev.LD29.states.GameState;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class EntityPlayer extends Entity {

	private int jumping;

	public EntityPlayer(Location location) {
		super(location);
		addAnimation(AnimationType.IDLE, new AnimatedSprite("/textures/entities/player/idle.png", 2, 1, true));
		setCurrentAnimation(AnimationType.IDLE);
		getCurrentAnimation().startAnimation();
		getCurrentAnimation().runAnimation();
		setCollisionBox(new CollisionBox(getLocation(), getCurrentAnimation().getCurrentSprite().getWidth(), getCurrentAnimation().getCurrentSprite().getHeight(), true));
	}
	
	public void setJumping(int jumping) {
		this.jumping = jumping;
	}
	
	public int getJumping() {
		return jumping;
	}

	public void update() {
		getCollisionBox().setLocation(getLocation());
		if (Main.keyboard.isKeyDown(KeyEvent.VK_LEFT) && !getCollisionBox().collidesInDirection(Direction.LEFT)) {
			move(-4, 0);
		}
		if (Main.keyboard.isKeyDown(KeyEvent.VK_RIGHT) && !getCollisionBox().collidesInDirection(Direction.UP)) {
			move(4, 0);
		}
		if (Main.keyboard.isKeyReleasing(KeyEvent.VK_SPACE)  && !getCollisionBox().collidesInDirection(Direction.UP)) {
			if (jumping == 0) {
				jumping = 60;
			}
		}
		jumping--;
		if (jumping > 15) {
			if (!getCollisionBox().collidesInDirection(Direction.UP)) {
				move(0, -(jumping / 15));
			} else {
				jumping = 0;
			}
		} else {
			if (!getCollisionBox().collidesInDirection(Direction.DOWN)) {
				move(0, -(jumping / 10));
			} else {
				jumping = 0;
			}
		}
		Map map = ((GameState) Main.getScreen().getCurrentState()).getMap();
		map.setScrollX(-getLocation().getX() + (int) Math.floor(Main.WIDTH / 2));
		map.setScrollY(-getLocation().getY() + (int) Math.floor(Main.HEIGHT / 2));
	}

}

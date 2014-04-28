package uk.co.newagedev.LD29.entities;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.graphics.AnimatedSprite;
import uk.co.newagedev.LD29.graphics.AnimationType;
import uk.co.newagedev.LD29.input.KeyBinding;
import uk.co.newagedev.LD29.maps.Map;
import uk.co.newagedev.LD29.states.GameState;
import uk.co.newagedev.LD29.tiles.Tile;
import uk.co.newagedev.LD29.tiles.TileDoor;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class EntityPlayer extends Entity {

	public EntityPlayer(Location location) {
		super(location);
		addAnimation(AnimationType.IDLE, new AnimatedSprite("/textures/entities/player/idle.png", 2, 1, true));
		setCurrentAnimation(AnimationType.IDLE);
		getCurrentAnimation().startAnimation();
		getCurrentAnimation().runAnimation();
		setMaxHealth(40);
		setHealth(40);
		setCollisionBox(new CollisionBox(getLocation(), getCurrentAnimation().getCurrentSprite().getWidth(), getCurrentAnimation().getCurrentSprite().getHeight(), true, true));
	}

	public void update() {
		super.update();
		if (KeyBinding.LEFT.getIsKeyDown() && !getCollisionBox().collidesInDirection(Direction.LEFT, this) && getLocation().getX() >= 8) {
			move(Direction.LEFT, 10);
		}
		if (KeyBinding.RIGHT.getIsKeyDown() && !getCollisionBox().collidesInDirection(Direction.RIGHT, this) && getLocation().getX() <= (Main.getScreen().getCurrentState().getMap().getWidth() * 32) - 35) {
			move(Direction.RIGHT, 10);
		}
		if (KeyBinding.JUMP.getIsKeyDown() && !getCollisionBox().collidesInDirection(Direction.UP, this)) {
			if (getJumping() == 0 && isOnGround()) {
				setJumping(40);
			}
		}
		Map map = ((GameState) Main.getScreen().getCurrentState()).getMap();
		if (Main.mouse.isMouseReleasing()) {
			Tile tile = map.getClickedTile(Main.mouse.getMouseX(), Main.mouse.getMouseY());
			if (tile instanceof TileDoor) {
				TileDoor door = (TileDoor) tile;
				door.openDoor(!door.isOpen());
			}
			Entity ent = map.getClickedEntity(Main.mouse.getMouseX(), Main.mouse.getMouseY());
			if (ent != null) {
				ent.damage(2);
			}
		}
		if (getLocation().getX() >= (int) Math.floor(Main.WIDTH / 2) && getLocation().getX() + (int) Math.floor(Main.WIDTH / 2) <= (map.getWidth() * 32) + 7)
			map.setScrollX(-getLocation().getX() + (int) Math.floor(Main.WIDTH / 2));
		map.setScrollY(-getLocation().getY() + (int) Math.floor(Main.HEIGHT / 2));
	}
}

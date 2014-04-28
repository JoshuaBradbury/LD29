package uk.co.newagedev.LD29.entities;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.graphics.AnimatedSprite;
import uk.co.newagedev.LD29.graphics.AnimationType;
import uk.co.newagedev.LD29.util.Direction;
import uk.co.newagedev.LD29.util.Location;

public class EntityRat extends Entity {

	int randMove;

	public EntityRat(Location location) {
		super(location);
		addAnimation(AnimationType.IDLE, new AnimatedSprite("/textures/entities/rat/weirdratidle.png", 2, 1, true));
		setCurrentAnimation(AnimationType.IDLE);
		getCurrentAnimation().startAnimation();
		getCurrentAnimation().runAnimation();
		setCollisionBox(new CollisionBox(getLocation(), getCurrentAnimation().getCurrentSprite().getWidth(), getCurrentAnimation().getCurrentSprite().getHeight(), true, true));
		setMaxHealth(10);
		setHealth(10);
	}

	public void update() {
		super.update();
		if (randMove == 0) {
			if (rand.nextInt(5) == 0) {
				randMove = rand.nextInt(80) - 40;
			}
		} else if (randMove < 0) {
			int moveDist = 0;
			if (Math.abs(randMove) > 5) {
				moveDist = rand.nextInt(5);
			} else {
				moveDist = rand.nextInt(Math.abs(randMove));
			}
			randMove += moveDist;
			if (randMove < -1) {
				if (!getCollisionBox().collidesInDirection(Direction.LEFT, this)) {
					move(Direction.LEFT, moveDist);
				}
			} else {
				randMove = 0;
				if (!getCollisionBox().collidesInDirection(Direction.LEFT, this)) {
					move(Direction.LEFT, 1);
				}
			}
		} else {
			int moveDist = 0;
			if (Math.abs(randMove) > 5) {
				moveDist = rand.nextInt(5);
			} else {
				moveDist = rand.nextInt(Math.abs(randMove));
			}
			randMove -= moveDist;
			if (randMove > 1) {
				if (!getCollisionBox().collidesInDirection(Direction.RIGHT, this)) {
					move(Direction.RIGHT, moveDist);
				}
			} else {
				randMove = 0;
				if (!getCollisionBox().collidesInDirection(Direction.RIGHT, this)) {
					move(Direction.RIGHT, 1);
				}
			}
		}
		for (Entity entity : Main.getScreen().getCurrentState().getMap().getEntitiesInRange(getLocation(), 64)) {
			if (entity instanceof EntityPlayer) {
				attack(entity, 2);
			}
		}
	}

}

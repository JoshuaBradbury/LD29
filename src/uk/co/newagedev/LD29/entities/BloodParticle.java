package uk.co.newagedev.LD29.entities;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.graphics.AnimatedSprite;
import uk.co.newagedev.LD29.graphics.AnimationType;
import uk.co.newagedev.LD29.util.Direction;

public class BloodParticle extends Entity {

	private float xVel, yVel;
	
	public BloodParticle(float xvel, float yvel) {
		xVel = xvel;
		yVel = yvel;
		addAnimation(AnimationType.PARTICLE, new AnimatedSprite("/textures/entities/particle/blood.png", 2, 1, true));
		setCurrentAnimation(AnimationType.PARTICLE);
		getCurrentAnimation().startAnimation();
		getCurrentAnimation().runAnimation();
		setCollisionBox(new CollisionBox(getLocation(), getCurrentAnimation().getCurrentSprite().getWidth(), getCurrentAnimation().getCurrentSprite().getHeight(), true, true, false, true));
		setAge(rand.nextInt(120) + 30);
		setAges(true);
		setMaxHealth((int) Math.pow(2, 10) + 1);
		setHealth((int) Math.pow(2, 10) - 1);
	}
	
	public void update() {
		super.update();
		if (xVel > 0) {
			xVel -= 0.01;
			if (xVel < 0) xVel = 0;
		}
		if (xVel < 0) {
			xVel += 0.01;
			if (xVel > 0) xVel = 0;
		}
		if (yVel > 0) {
			yVel -= 0.01;
			if (yVel < 0) yVel = 0;
		}
		if (yVel < 0) {
			yVel += 0.01;
			if (yVel > 0) yVel = 0;
		}
		move(Direction.LEFT, (int) xVel);
		move(Direction.UP, (int) yVel);
	}
}

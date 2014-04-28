package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.graphics.Sprite;
import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileDoor extends Tile {

	private boolean open;

	public TileDoor() {
		super(null, TileType.DOOR, true);
		setCollisionBox(new CollisionBox(getLocation(), 32, 64, false, true));
	}

	public Sprite getSprite() {
		return SpriteSheet.DOOR.getSprite(open ? 1 : 0);
	}

	public void openDoor(boolean open) {
		this.open = open;
		if (open) {
			getCollisionBox().setSolid(false);
		} else {
			getCollisionBox().setSolid(true);	
		}
	}

	public boolean isOpen() {
		return open;
	}
}

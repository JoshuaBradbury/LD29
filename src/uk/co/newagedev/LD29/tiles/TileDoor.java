package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.graphics.Sprite;
import uk.co.newagedev.LD29.graphics.SpriteSheet;
import uk.co.newagedev.LD29.states.GameState;
import uk.co.newagedev.LD29.util.Location;

public class TileDoor extends Tile {

	private boolean top, open, partOfDoor;

	public TileDoor() {
		super(null, TileType.DOOR, true);
	}

	public Sprite getSprite() {
		int tex = 0;
		if (!top)
			tex = 2;
		if (open)
			tex += 1;
		return SpriteSheet.DOOR.getSprite(tex);
	}

	public void openDoor(boolean open) {
		this.open = open;
		if (partOfDoor && top) {
			if (((GameState) Main.getScreen().getCurrentState()).getMap().getTileAt(getLocation().subtract(new Location(0, 1))) instanceof TileDoor) {

			}
		}
	}

	public void update() {
		if (!partOfDoor) {
			if (((GameState) Main.getScreen().getCurrentState()).getMap().getTileAt(getLocation().subtract(new Location(0, 1))) instanceof TileDoor) {
				top = true;
				partOfDoor = true;
			} else if (((GameState) Main.getScreen().getCurrentState()).getMap().getTileAt(getLocation().add(0, 1)) instanceof TileDoor) {
				top = false;
				partOfDoor = true;
			}
		}
		if (partOfDoor) {
			if (top) {
				
			}
		}
	}
}

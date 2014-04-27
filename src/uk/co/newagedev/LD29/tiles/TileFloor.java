package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileFloor extends Tile {

	public TileFloor() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(3), TileType.FLOOR, true);
	}
	
}

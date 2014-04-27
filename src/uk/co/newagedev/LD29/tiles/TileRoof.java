package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileRoof extends Tile {

	public TileRoof() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(0), TileType.ROOF, true);
	}

}

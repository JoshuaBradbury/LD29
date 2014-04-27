package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TilePlatform extends Tile {

	public TilePlatform() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(3), TileType.PLATFORM);
	}

}

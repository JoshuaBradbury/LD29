package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileWall extends Tile {

	public TileWall() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(1), TileType.WALL, true);
	}

}

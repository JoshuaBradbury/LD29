package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileGrass extends Tile {
	
	public TileGrass() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(2), TileType.GRASS, true);
	}
}

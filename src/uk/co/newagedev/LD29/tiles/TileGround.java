package uk.co.newagedev.LD29.tiles;

import uk.co.newagedev.LD29.graphics.SpriteSheet;

public class TileGround extends Tile {
	
	public TileGround() {
		super(SpriteSheet.HOSPITAL_TILES.getSprite(6), TileType.GROUND, true);
	}
}

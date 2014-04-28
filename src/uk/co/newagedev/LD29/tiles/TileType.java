package uk.co.newagedev.LD29.tiles;

import java.awt.Color;

public enum TileType {

	GROUND(new Color(87, 0, 127), TileGround.class),
	DOOR(new Color(182, 255, 0), TileDoor.class),
	ELEVATOR(new Color(81, 252, 255), TileElevator.class),
	PLATFORM(new Color(255, 0, 220), TilePlatform.class),
	ROOF(new Color(255, 216, 0), TileRoof.class),
	FLOOR(new Color(0, 148, 255), TileFloor.class),
	GRASS(new Color(38, 127, 0), TileGrass.class),
	SPAWNER(new Color(255, 141, 61), TileSpawner.class),
	WALL(new Color(64, 64, 64), TileWall.class);
	
	private Color colour;
	private Class<? extends Tile> tileClass;
	
	TileType(Color colour, Class<? extends Tile> tile) {
		this.colour = colour;
		tileClass = tile;
	}
	
	public Color getColour() {
		return colour;
	}
	
	public Class<? extends Tile> getTileClass() {
		return tileClass;
	}
}

package uk.co.newagedev.LD29.tiles;

import java.awt.Color;
import java.util.ArrayList;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.graphics.Sprite;
import uk.co.newagedev.LD29.util.Location;

public class Tile {
	
	private Sprite sprite;
	private TileType type;
	private static ArrayList<Tile> Tiles;
	private CollisionBox box;
	private Location location;
	
	public Tile(Sprite sprite, TileType type, boolean solid) {
		this(sprite, type, solid, solid, solid, solid);
	}
	
	public Tile(Sprite sprite, TileType type, boolean up, boolean down, boolean left, boolean right) {
		this.sprite = sprite;
		this.type = type;
		box = new CollisionBox(location, 32, 32, up, down, left, right);
		if (Tiles == null) Tiles = new ArrayList<Tile>();
		Tiles.add(this);
	}
	
	public void update() {
		
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
		box.setLocation(location);
	}
	
	public CollisionBox getCollisionBox() {
		return box;
	}
	
	public void setCollisionBox(CollisionBox box) {
		this.box = box;
	}
	
	public Tile(Sprite sprite, TileType type) {
		this(sprite, type, false);
	}
	
	public static TileType getTypeByColor(Color colour) {
		for (TileType type : TileType.values()) {
			if (type.getColour().getRed() == colour.getRed() && type.getColour().getBlue() == colour.getBlue() && type.getColour().getGreen() == colour.getGreen()) {
				return type;
			}
		}
		return null;
	}
	
	public TileType getType() {
		return type;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
}

package uk.co.newagedev.LD29.maps;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.entities.Entity;
import uk.co.newagedev.LD29.entities.EntityPlayer;
import uk.co.newagedev.LD29.tiles.Tile;
import uk.co.newagedev.LD29.tiles.TileType;
import uk.co.newagedev.LD29.util.Location;

public class Map {

	public static Map HOSPITAL = new Map("/textures/maps/level1.png");
	private int scrollX, scrollY;

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();

	public Map(String tilesPath) {
		loadMap(tilesPath);
		addEntity(new EntityPlayer(new Location(100, 2500)));
	}
	
	public Tile getTileAt(Location location) {
		for (Tile tile : tiles) {
			if (tile.getLocation().equals(location)) {
				return tile;
			}
		}
		return null;
	}

	public void render() {
		for (Tile tile : tiles) {
			Location location = tile.getLocation();
			if (location != null) {
				if (location.isOnScreen(scrollX, scrollY)) {
					Main.getScreen().displaySprite(location.getScreenLocationX(scrollX), location.getScreenLocationY(scrollY), tile.getSprite());
				}
			}
		}
		for (Entity entity : entities) {
			entity.render();
		}
	}

	public void update() {
		for (Entity entity : entities) {
			entity.update();
		}
		for (Tile tile : tiles) {
			tile.update();
		}
	}

	public void setScrollX(int scrollX) {
		this.scrollX = scrollX;
	}

	public void setScrollY(int scrollY) {
		this.scrollY = scrollY;
	}

	public int getScrollX() {
		return scrollX;
	}

	public int getScrollY() {
		return scrollY;
	}

	public void loadMap(String tilesPath) {
		BufferedImage image = Main.getScreen().loadImage(tilesPath);
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int pix = image.getRGB(x, y);
				Color colour = new Color(pix);
				TileType type = Tile.getTypeByColor(colour);
				if (type != null) {
					try {
						Tile tile = type.getTileClass().newInstance();
						tile.setLocation(new Location(x * 32, y * 32));
						tiles.add(tile);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public ArrayList<Entity> getEntitiesInRange(Location location, int range) {
		ArrayList<Entity> entis = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (location.distance(entity.getLocation()) <= range) {
				entis.add(entity);
			}
		}
		return entis;
	}

	public ArrayList<CollisionBox> getCollisionBoxesInRange(Location location, int range) {
		ArrayList<CollisionBox> boxes = new ArrayList<CollisionBox>();
		for (Tile tile : tiles) {
			if (location.distance(tile.getLocation()) <= range) {
				boxes.add(tile.getCollisionBox());
			}
		}
		for (Entity entity : entities) {
			if (!(entity instanceof EntityPlayer)) {
				if (location.distance(entity.getLocation()) <= range) {
					boxes.add(entity.getCollisionBox());
				}
			}
		}
		return boxes;
	}
}

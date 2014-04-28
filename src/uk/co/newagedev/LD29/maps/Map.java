package uk.co.newagedev.LD29.maps;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import uk.co.newagedev.LD29.CollisionBox;
import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.entities.BloodParticle;
import uk.co.newagedev.LD29.entities.Entity;
import uk.co.newagedev.LD29.entities.EntityPlayer;
import uk.co.newagedev.LD29.entities.EntityRat;
import uk.co.newagedev.LD29.tiles.Tile;
import uk.co.newagedev.LD29.tiles.TileType;
import uk.co.newagedev.LD29.util.Location;
import uk.co.newagedev.LD29.states.StartMenuState;

public class Map {

	public static Map HOSPITAL = new Map("/textures/maps/level1.png", true);
	public static Map TITLE_SCREEN = new Map("/textures/maps/level1.png", false);
	private int scrollX, scrollY, width, height;
	private Random rand;
	private boolean win;
	private int winCountdown = 0;

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> delayedEntities = new ArrayList<Entity>();
	private ArrayList<Entity> entitiesToRemove = new ArrayList<Entity>();

	public Map(String tilesPath, boolean spawnPlayer) {
		loadMap(tilesPath);
		if (spawnPlayer) {
			addEntity(new EntityPlayer(new Location(100, 2500)));
		}
		rand = new Random();
	}

	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	public void despawnEntity(Entity entity) {
		entitiesToRemove.add(entity);
	}

	public void clearEntities() {
		entities.clear();
		delayedEntities.clear();
		entitiesToRemove.clear();
	}

	public void spawnBlood(Location location, int amount) {
		for (int i = 0; i < amount; i++) {
			BloodParticle particle = new BloodParticle((rand.nextInt(10) - 5), (rand.nextInt(10) - 5));
			particle.setLocation(location.getInstance());
			delayedEntities.add(particle);
		}
	}

	public void spawn(Entity entity, Location location) {
		entity.setLocation(location);
		entities.add(entity);
	}

	public Tile getClickedTile(int x, int y) {
		for (Tile tile : tiles) {
			if (new Rectangle(tile.getLocation().getScreenLocationX(scrollX), tile.getLocation().getScreenLocationY(scrollY), tile.getCollisionBox().getWidth(), tile.getCollisionBox().getHeight()).contains(x, y)) {
				return tile;
			}
		}
		return null;
	}

	public Entity getClickedEntity(int x, int y) {
		for (Entity entity : entities) {
			if (new Rectangle(entity.getLocation().getScreenLocationX(scrollX), entity.getLocation().getScreenLocationY(scrollY), entity.getCollisionBox().getWidth(), entity.getCollisionBox().getHeight()).contains(x, y)) {
				return entity;
			}
		}
		return null;
	}

	public Tile getTileAt(Location location) {
		for (Tile tile : tiles) {
			if (Math.abs(tile.getLocation().getY() - location.getY()) < 64 && Math.abs(tile.getLocation().getX() - location.getX()) < 64) {
				return tile;
			}
		}
		return null;
	}

	public void setWin(boolean win) {
		this.win = win;
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
		for (Entity entity : entities) {
			if (entity.getMaxHealth() < Math.pow(2, 10)) {
				int offset = ((entity.getMaxHealth() * 2) - entity.getCollisionBox().getWidth()) / 2;
				Main.getScreen().displayRect(entity.getLocation().getScreenLocationX(scrollX) - offset, entity.getLocation().getScreenLocationY(scrollY) - 20, entity.getHealth() * 2, 10, Color.GREEN);
				if (entity.getHealth() != entity.getMaxHealth()) {
					Main.getScreen().displayRect(entity.getLocation().getScreenLocationX(scrollX) + (entity.getHealth() * 2) - offset, entity.getLocation().getScreenLocationY(scrollY) - 20, (entity.getMaxHealth() - entity.getHealth()) * 2, 10, Color.RED);
				}
			}
		}
		if (win) {
			Main.getScreen().displayText(187, 100, 75, "Virtual DJ", Color.WHITE, "Game Over!");
		}
	}

	private boolean gameUpdate = false;

	public void update() {
		for (Entity entity : delayedEntities) {
			entities.add(entity);
		}
		delayedEntities.clear();
		for (Entity entity : entitiesToRemove) {
			entities.remove(entity);
		}
		entitiesToRemove.clear();

		if (gameUpdate) {
			for (Entity entity : entities) {
				entity.update();
			}
			gameUpdate = false;
		} else {
			gameUpdate = true;
		}
		for (Tile tile : tiles) {
			tile.update();
		}
		if (!win) {
			for (Entity entity : entities) {
				if (entity instanceof EntityRat) {
					return;
				}
			}
			win = true;
		} else {
			winCountdown++;
		}
		if (winCountdown > 300) {
			Main.getScreen().setState(new StartMenuState());
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
		if (image != null) {
			width = image.getWidth();
			height = image.getHeight();
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

	public ArrayList<CollisionBox> getCollisionBoxesInRange(Location location, int range, boolean collidesWithEntities, boolean collidesWithTiles) {
		ArrayList<CollisionBox> boxes = new ArrayList<CollisionBox>();
		if (collidesWithTiles) {
			for (Tile tile : tiles) {
				if (location.distance(tile.getLocation()) <= range) {
					boxes.add(tile.getCollisionBox());
				}
			}
		}
		if (collidesWithEntities) {
			for (Entity entity : entities) {
				if (location.distance(entity.getLocation()) <= range) {
					boxes.add(entity.getCollisionBox());
				}
			}
		}
		return boxes;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

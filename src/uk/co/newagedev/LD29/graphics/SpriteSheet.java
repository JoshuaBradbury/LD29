package uk.co.newagedev.LD29.graphics;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import uk.co.newagedev.LD29.Main;

public class SpriteSheet {

	public static final SpriteSheet HOSPITAL_TILES = new SpriteSheet("/textures/tiles/hospital_tiles.png", 16, 16);
	public static final SpriteSheet DOOR = new SpriteSheet("/textures/tiles/door.png", 2, 2);

	private HashMap<Integer, Sprite> sprites = new HashMap<Integer, Sprite>();
	private int sheetWidth, sheetHeight, tileWidth, tileHeight;
	private BufferedImage sheet;

	public SpriteSheet(String path, int sheetWidth, int sheetHeight) {
		this.sheetWidth = sheetWidth;
		this.sheetHeight = sheetHeight;
		sheet = Main.getScreen().loadImage(path);
		splitSheet();
	}
	
	public void splitSheet() {
		int scale = 2;
		tileWidth = sheet.getWidth() / sheetWidth;
		tileHeight = sheet.getHeight() / sheetHeight;
		for (int y = 0; y < sheet.getHeight(); y += tileHeight) {
			for (int x = 0; x < sheet.getWidth(); x += tileWidth) {
				BufferedImage img = new BufferedImage(tileWidth * scale, tileHeight * scale, BufferedImage.TYPE_INT_ARGB);
				for (int i = 0; i < tileWidth * scale; i++) {
					for (int j = 0; j < tileHeight * scale; j++) {
						Color colour = new Color(sheet.getRGB(x + (int) Math.floor(i / scale), y + (int) Math.floor(j / scale)));
						if (colour.getRed() == 206 && colour.getGreen() == 12 && colour.getBlue() == 255) img.setRGB(i, j, new Color(255, 255, 255, 0).getRGB());
						else img.setRGB(i, j, sheet.getRGB(x + (int) Math.floor(i / scale), y + (int) Math.floor(j / scale)));
					}
				}
				sprites.put(((y / tileHeight) * sheetWidth) + (x / tileWidth), new Sprite(img));
			}
		}
	}

	public Sprite getSprite(int i) {
		return sprites.get(i);
	}
	
	public Sprite getSprite(int x, int y) {
		return sprites.get(((y / tileHeight) * sheetWidth) + (x / tileWidth));
	}
	
	public HashMap<Integer, Sprite> getSprites() {
		return sprites;
	}
	
	public int getWidth() {
		return sheetWidth;
	}
	
	public int getHeight() {
		return sheetHeight;
	}
}

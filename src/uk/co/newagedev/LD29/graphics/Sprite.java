package uk.co.newagedev.LD29.graphics;

import java.awt.image.BufferedImage;

import uk.co.newagedev.LD29.Main;

public class Sprite {
	
	public static final Sprite MISSING_TEXTURE = new Sprite(Main.getScreen().loadImage("/textures/error.png"));
	public static final Sprite BLANK = new Sprite(Main.getScreen().loadImage("/textures/blank.png"));

	private BufferedImage image;
	private int width, height;
	
	public Sprite(BufferedImage image) {
		this.image = image;
		width = image.getWidth();
		height = image.getHeight();
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}

package uk.co.newagedev.LD29.graphics;

import java.awt.image.BufferedImage;

import uk.co.newagedev.LD29.Main;

public class Background {

	private BufferedImage bg;
	
	public Background(String path) {
		bg = Main.getScreen().loadImage(path);
	}
	
	public BufferedImage getBG() {
		return bg;
	}
	
}

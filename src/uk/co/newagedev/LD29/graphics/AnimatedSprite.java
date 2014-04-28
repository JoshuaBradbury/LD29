package uk.co.newagedev.LD29.graphics;

import java.util.ArrayList;

public class AnimatedSprite {

	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	private int frame;
	private Sprite currentSprite;
	private boolean loops, playing;

	public AnimatedSprite(String path, int sheetWidth, int sheetHeight, boolean loops) {
		SpriteSheet sheet = new SpriteSheet(path, sheetWidth, sheetHeight);
		for (int i = 0; i < sheetWidth * sheetHeight; i++) {
			sprites.add(sheet.getSprite(i));
		}
		this.loops = loops;
	}

	public AnimatedSprite(String path, int sheetWidth, int sheetHeight) {
		this(path, sheetWidth, sheetHeight, false);
	}

	public void startAnimation() {
		playing = true;
	}

	public void stopAnimation() {
		playing = false;
	}

	public Sprite getCurrentSprite() {
		return currentSprite;
	}

	public boolean isPlaying() {
		return playing;
	}
	
	int count = 0;
	public void runAnimation() {
		if (count % 120 == 0) {
			if (playing) {
				if (frame >= sprites.size()) {
					frame = 0;
					if (!loops) {
						playing = false;
					}
				}
				currentSprite = sprites.get(frame);
				frame++;
			}
		}
		count++;
	}
}

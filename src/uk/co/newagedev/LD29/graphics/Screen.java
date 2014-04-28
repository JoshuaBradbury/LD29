package uk.co.newagedev.LD29.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import uk.co.newagedev.LD29.Main;
import uk.co.newagedev.LD29.states.State;

public class Screen extends Canvas {

	private static final long serialVersionUID = -8487743645370495190L;
	private Graphics g;
	private BufferStrategy bs;
	private State currentState;

	public State getCurrentState() {
		return currentState;
	}

	public void setState(State state) {
		currentState = state;
	}

	public void fillScreen(Color colour) {
		initRendering();
		g.setColor(colour);
		g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
	}

	public int getTextWidth(Font font, String option) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		return (int) font.getStringBounds(option, frc).getWidth();
	}

	public int getTextHeight(Font font, String option) {
		AffineTransform affinetransform = new AffineTransform();
		FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
		return (int) font.getStringBounds(option, frc).getHeight();
	}

	public void displayText(int x, int y, Font font, Color colour, String text) {
		initRendering();
		g.setFont(font);
		g.setColor(colour);
		g.drawString(text, x, y);
	}

	public BufferedImage loadImage(String path) {
		try {
			URL url = Screen.class.getResource(path);
			BufferedImage img = ImageIO.read(url);
			return img;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void displayText(int x, int y, int size, String fontName, Color colour, String text) {
		displayText(x, y, new Font(fontName, Font.BOLD, size), colour, text);
	}

	public void displayRect(int x, int y, int width, int height, Color colour) {
		initRendering();
		g.setColor(colour);
		g.fillRect(x, y, width, height);
	}

	public void displaySprite(int x, int y, Sprite sprite) {
		if (g == null) {
			initRendering();
		}
		if (x > -32 && x < Main.WIDTH + 32) {
			if (y > -32 && y < Main.HEIGHT + 32) {
				if (sprite != null) {
					g.drawImage(sprite.getImage(), x, y, this);
				} else {
					g.drawImage(Sprite.MISSING_TEXTURE.getImage(), x, y, this);
				}
			}
		}
	}

	public void initRendering() {
		bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
	}

	public void render() {
		if (g == null) {
			initRendering();
		}

		currentState.render();

		if (g != null) {
			g.dispose();
			bs.show();
		}
	}
}

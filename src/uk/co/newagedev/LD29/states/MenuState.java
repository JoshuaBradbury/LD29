package uk.co.newagedev.LD29.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import uk.co.newagedev.LD29.Main;

public class MenuState extends State {

	private ArrayList<String> options = new ArrayList<String>();
	private int pos;
	private Font font;
	private String optionPressed = "";

	public MenuState(Font font) {
		super(Color.BLACK);
		this.font = font;
	}

	public String getOptionPressed() {
		return optionPressed;
	}

	public void addOption(String option) {
		options.add(option);
	}

	public void render() {
		super.render();
		if (options.size() > 0) {
			int inc = (Main.HEIGHT - 200 - Main.getScreen().getTextHeight(font, options.get(0))) / options.size();
			int h = 100;
			for (int i = 0; i < options.size(); i++) {
				int w = Main.getScreen().getTextWidth(font, options.get(i));
				if (pos == i)
					Main.getScreen().displayRect(200, h - Main.getScreen().getTextHeight(font, options.get(i)), Main.WIDTH - 400, Main.getScreen().getTextHeight(font, options.get(i)) + 8, new Color(255, 255, 255, 100));
				Main.getScreen().displayText((Main.WIDTH / 2) - (w / 2), h, font, Color.WHITE, options.get(i));
				h += inc;
				h += Main.getScreen().getTextHeight(font, options.get(0));
			}
		}
	}

	public void update() {
		optionPressed = "";
		if (Main.keyboard.isKeyReleasing(KeyEvent.VK_DOWN)) {
			pos++;
			if (pos >= options.size()) {
				pos = 0;
			}
		}
		if (Main.keyboard.isKeyReleasing(KeyEvent.VK_UP)) {
			pos--;
			if (pos < 0) {
				pos = options.size() - 1;
			}
		}
		if (Main.mouse.didMouseMove()) {
			if (options.size() > 0) {
				int inc = (Main.HEIGHT - 200 - Main.getScreen().getTextHeight(font, options.get(0))) / options.size();
				int h = 100;
				for (int i = 0; i < options.size(); i++) {
					int w = Main.getScreen().getTextWidth(font, options.get(i));
					if (Main.mouse.getMouseX() > 200 && Main.mouse.getMouseX() < Main.WIDTH - 200) {
						if (Main.mouse.getMouseY() > h - Main.getScreen().getTextHeight(font, options.get(i)) && Main.mouse.getMouseY() < (Main.getScreen().getTextHeight(font, options.get(i)) + 8) + (h - Main.getScreen().getTextHeight(font, options.get(i)))) {
							pos = i;
						}
					}
					Main.getScreen().displayText((Main.WIDTH / 2) - (w / 2), h, font, Color.WHITE, options.get(i));
					h += inc;
					h += Main.getScreen().getTextHeight(font, options.get(0));
				}
			}
		}
		if (Main.mouse.isMouseReleasing()) {
			if (options.size() > 0) {
				int inc = (Main.HEIGHT - 200 - Main.getScreen().getTextHeight(font, options.get(0))) / options.size();
				int h = 100;
				for (int i = 0; i < options.size(); i++) {
					int w = Main.getScreen().getTextWidth(font, options.get(i));
					if (pos == i) {
						if (Main.mouse.getMouseX() > 200 && Main.mouse.getMouseX() < Main.WIDTH - 200) {
							if (Main.mouse.getMouseY() > h - Main.getScreen().getTextHeight(font, options.get(i)) && Main.mouse.getMouseY() < (Main.getScreen().getTextHeight(font, options.get(i)) + 8) + (h - Main.getScreen().getTextHeight(font, options.get(i)))) {
								optionPressed = options.get(i);
							}
						}
					}
					Main.getScreen().displayText((Main.WIDTH / 2) - (w / 2), h, font, Color.WHITE, options.get(i));
					h += inc;
					h += Main.getScreen().getTextHeight(font, options.get(0));
				}
			}
		}
	}
}

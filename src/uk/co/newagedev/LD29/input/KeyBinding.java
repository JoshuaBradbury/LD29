package uk.co.newagedev.LD29.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import uk.co.newagedev.LD29.Main;

public class KeyBinding {

	public static ArrayList<KeyBinding> keyBindings = new ArrayList<KeyBinding>();
	
	public static final KeyBinding JUMP = new KeyBinding(KeyEvent.VK_SPACE, KeyEvent.VK_UP, KeyEvent.VK_W);
	public static final KeyBinding LEFT = new KeyBinding(KeyEvent.VK_LEFT, KeyEvent.VK_A);
	public static final KeyBinding RIGHT = new KeyBinding(KeyEvent.VK_RIGHT, KeyEvent.VK_D);
	public static final KeyBinding CROUCH = new KeyBinding(KeyEvent.VK_SHIFT);
	public static final KeyBinding INTERACT = new KeyBinding(KeyEvent.VK_ENTER);
	
	private ArrayList<Integer> keyCodes = new ArrayList<Integer>();
	private boolean down, releasing, press;

	public boolean getIsKeyDown() {
		return down;
	}
	
	public boolean getIsKeyReleasing() {
		return releasing;
	}

	public boolean getIsKeyPressed() {
		return press;
	}

	KeyBinding(int... keyCodes) {
		for (int code : keyCodes) {
			registerBinding(this, code);
		}
		keyBindings.add(this);
	}

	public void update() {
		
		boolean dn = false;
		for (int keyCode : keyCodes) {
			if (Main.keyboard.isKeyDown(keyCode))
				dn = true;
		}
		down = dn;

		boolean re = false;
		for (int keyCode : keyCodes) {
			if (Main.keyboard.isKeyDown(keyCode)) {
				re = false;
				break;
			}
			if (Main.keyboard.isKeyReleasing(keyCode))
				re = true;
		}
		releasing = re;

	}

	public ArrayList<Integer> getKeyCodes() {
		return keyCodes;
	}

	public void addKeyCode(int keyCode) {
		keyCodes.add(keyCode);
	}

	public void registerBinding(KeyBinding bind, int keyCode) {
		if (!bind.getKeyCodes().contains(keyCode)) {
			bind.addKeyCode(keyCode);
		}
	}

}

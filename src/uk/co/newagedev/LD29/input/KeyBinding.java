package uk.co.newagedev.LD29.input;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class KeyBinding {

	public static final KeyBinding JUMP = new KeyBinding(KeyEvent.VK_SPACE, KeyEvent.VK_UP);
	
	private ArrayList<Integer> keyCodes = new ArrayList<Integer>();
	
	public KeyBinding (int... keyCodes) {
		for (int code : keyCodes) {
			registerBinding(this, code);
		}
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

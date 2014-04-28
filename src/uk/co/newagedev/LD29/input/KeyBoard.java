package uk.co.newagedev.LD29.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keysReleasing = new boolean[8192];
	private boolean[] keys = new boolean[8192];
	private boolean[] keysPressed = new boolean[8192];
	
	public void update() {
		for (int i = 0; i < keysReleasing.length; i++) {
			if (keysReleasing[i]) {
				keysReleasing[i] = false;
				keys[i] = false;
			}
			keysPressed[i] = false;
		}
	}

	public void keyPressed(KeyEvent ke) {
		keys[ke.getKeyCode()] = true;
		keysPressed[ke.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent ke) {
		keysReleasing[ke.getKeyCode()] = true;
	}
	
	public boolean isKeyDown(int keycode) {
		return keys[keycode];
	}
	
	public boolean isKeyReleasing(int keycode) {
		return keysReleasing[keycode];
	}
	
	public boolean isKeyPressing(int keycode) {
		return keysPressed[keycode];
	}
	
	public void keyTyped(KeyEvent ke) {}
}

package uk.co.newagedev.LD29.input;

import java.awt.event.MouseEvent;

import javax.swing.event.MouseInputListener;

public class Mouse implements MouseInputListener {

	private int mouseButton = -1, mouseX, mouseY;
	private boolean mouseDown, mouseReleasing, mouseMoved;
	
	public void update() {
		if (mouseButton > -1) mouseButton = -1;
		if (mouseDown && mouseReleasing) mouseDown = false;
		if (mouseReleasing) mouseReleasing = false;
		if (mouseMoved) mouseMoved = false;
	}
	
	public boolean isMouseDown() {
		return mouseDown;
	}
	
	public boolean isMouseReleasing() {
		return mouseReleasing;
	}
	
	public int getMouseX() {
		return mouseX;
	}
	
	public int getMouseY() {
		return mouseY;
	}
	
	public int getMouseButton() {
		return mouseButton;
	}
	
	public boolean didMouseMove() {
		return mouseMoved;
	}

	public void mouseClicked(MouseEvent me) {
		mouseButton = me.getButton();
		mouseDown = true;
		mouseX = me.getX();
		mouseY = me.getY();
	}

	public void mouseReleased(MouseEvent me) {
		mouseReleasing = true;
		mouseX = me.getX();
		mouseY = me.getY();
	}

	public void mouseMoved(MouseEvent me) {
		mouseX = me.getX();
		mouseY = me.getY();
		mouseMoved = true;
	}

	public void mouseDragged(MouseEvent me) {}
	public void mouseEntered(MouseEvent me) {}
	public void mouseExited(MouseEvent me) {}
	public void mousePressed(MouseEvent me) {}
}

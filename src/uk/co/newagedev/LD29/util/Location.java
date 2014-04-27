package uk.co.newagedev.LD29.util;

import uk.co.newagedev.LD29.Main;

public class Location {

	private int x, y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public boolean equals(Location location) {
		return x == location.getX() && y == location.getY();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public float distance(Location location) {
		int xDiff = Math.max(location.getX(), x) - Math.min(location.getX(), x);
		int yDiff = Math.max(location.getY(), y) - Math.min(location.getY(), y);
		return (float) Math.sqrt(Math.pow(xDiff, 2) + Math.pow(yDiff, 2));
	}
	
	public Location add(Location location) {
		setLocation(x + location.getX(), y + location.getY());
		return this;
	}
	
	public Location add(int x, int y) {
		setLocation(this.x + x, this.y + y);
		return this;
	}
	
	
	public Location subtract(Location location) {
		setLocation(x - location.getX(), y - location.getY());
		return this;
	}
	
	public Location subtract(int x, int y) {
		setLocation(this.x - x, this.y - y);
		return this;
	}
	
	
	public void move(int x, int y) {
		setLocation(this.x + x, this.y + y);
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean isOnScreen(int xOffset, int yOffset) {
		return (x + xOffset > 0 || x + xOffset < Main.WIDTH) && (y + yOffset > 0 || y + yOffset < Main.HEIGHT);
	}

	public int getScreenLocationX(int xOffset) {
		return x + xOffset;
	}
	
	public int getScreenLocationY(int yOffset) {
		return y + yOffset;
	}
}

package uk.co.newagedev.LD29.util;

public enum Direction {
	
	UP,
	RIGHT,
	DOWN,
	LEFT,
	INVALID_DIRECTION;
	
	public static Direction getDirectionFromMove(int x, int y) {
		if (x >= 0) {
			return RIGHT;
		} else if (y >= 0) {
			return DOWN;
		} else if (x <= 0) {
			return LEFT;
		} else if (y <= 0) {
			return UP;
		}
		return INVALID_DIRECTION;
	}
	
}

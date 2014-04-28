package uk.co.newagedev.LD29.util;

public enum Direction {
	
	UP,
	RIGHT,
	DOWN,
	LEFT,
	INVALID_DIRECTION;
	
	public static Direction getOpposite(Direction dir) {
		if (dir == UP) {
			return DOWN;
		} else if (dir == DOWN) {
			return UP;
		} else if (dir == RIGHT) {
			return LEFT;
		} else if (dir == LEFT) {
			return RIGHT;
		}
		return INVALID_DIRECTION;
	}
	
}

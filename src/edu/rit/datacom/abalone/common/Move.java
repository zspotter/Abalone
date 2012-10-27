package edu.rit.datacom.abalone.common;

/**
 * Represents an immutable player move.
 * 
 * @author Zachary Potter
 *
 */
public class Move {

	private final int color;
	private final int direction;
	private final int[][] tiles;

	public Move(int color, int direction, int[][] tiles) {
		this.color = color;
		this.direction = direction;
		this.tiles = tiles;
	}

	public int getColor() {
		return color;
	}

	public int getDirection() {
		return direction;
	}

	public int[][] getMarbles() {
		return tiles.clone();
	}

}

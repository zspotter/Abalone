package com.teamname.abalone;


/**
 * 
 * 
 * 
 * The board is represented on two axes that are 120 degrees from each other.
 * The coordinates are of the form (x, y) where the Y-axis is horizontal to
 * the board and the X-axis is at an angle -30 degrees from vertical. The
 * positive direction is towards the north and east from the board. The north
 * side of the board is always home to the white marbles.
 * 
 * For example, the center and left-most spot on this board (marked A) is (0, 4) while the
 * center and right-most spot (marked B) is (8, 4).
 * 
 *             8 O O O O O
 *            7 O O O O O O
 *           6 + + O O O + +
 *          5 + + + + + + + +
 *         4 A + + + + + + + B
 *          3 + + + + + + + + 8
 *           2 + + @ @ @ + + 7
 *            1 @ @ @ @ @ @ 6
 *             0 @ @ @ @ @ 5
 *                0 1 2 3 4
 * 
 * Each tile is adjacent to 6 others in the directions:
 * 
 *          NW     NE
 *             \ /
 *         W  - + -  E
 *             / \
 *          SW     SE
 * 
 * In memory, the board contents are represented as a 2D array of ints:
 * 	board[8] = {2, 2, 2, 2, 2}
 * 	board[7] = {2, 2, 2, 2, 2, 2}
 * 	board[6] = {0, 0, 2, 2, 2, 0, 0}
 * 	board[7] = {0, 0, 0, 0, 0, 0, 0, 0}
 * 	...
 * 	board[Y] = {x0, x1, x2, ...}
 * 	...
 * 	board[0] = {1, 1, 1, 1, 1}
 * 
 * 
 * 
 * @author Zachary Potter
 *
 */
public class Board {

	/** Constants that define direction. */
	public static final int SW = 0;
	public static final int W = 1;
	public static final int NW = 2;
	public static final int NE = 3;
	public static final int E = 4;
	public static final int SE = 5;

	/** Constants that define states of any spot on the board. */
	public static final int EMPTY = 0;
	public static final int BLACK = 1;
	public static final int WHITE = 2;

	/** Constant maximum length of the board. */
	public static final int MAX = 9;
	/** Constant minimum length of the board. */
	public static final int MIN = 5;


	private final int[][] board;

	public Board() {
		// Create the board, initializing the spots of differing length on the y-axis.
		board = new int[MAX][];
		int length;
		for (int y = 0; y < MIN - 1; y++) {
			length = MIN + y;
			board[y] = new int[length];
			board[MAX - 1 - y] = new int[length];
		}
		board[MIN - 1] = new int[MAX];

		setupBoard();
	}

	/**
	 * Sets the state of the board to the initial player starting state.
	 */
	public void setupBoard() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				set(x, y, EMPTY);
			}
		}

		for (int x = 0; x < MIN; x++) set(x, 0, BLACK);
		for (int x = 0; x < MIN + 1; x++) set(x, 1, BLACK);
		set(2, 2, BLACK); set(3, 2, BLACK); set(4, 2, BLACK);

		for (int x = 0; x < MIN; x++) set(x, 8, WHITE);
		for (int x = 0; x < MIN + 1; x++) set(x, 7, WHITE);
		set(2, 6, WHITE); set(3, 6, WHITE); set(4, 6, WHITE);
	}

	public int get(int x, int y) {
		return board[y][x];
	}

	public void set(int x, int y, int state) {
		if (state != BLACK && state != WHITE && state != EMPTY)
			throw new IllegalArgumentException("Unknown state: "+state);
		board[y][x] = state;
	}

	/**
	 * @param y The position along the Y axis to get the length of.
	 * @return A number between Board.MIN and Board.MAX.
	 */
	public int lengthOf(int y) {
		return board[y].length;
	}

	/**
	 * Prints a human readable version of this board to standard output.
	 */
	public void printBoard() {
		// Print line by line through the y-axis
		for (int y = MAX - 1; y >= 0; y--) {
			// Print prefacing spaces
			int numS = Math.abs(MIN - 1 - y);
			for (int s = 0; s < numS; s++) {
				System.out.print(" ");
			}
			// Print the y axis number
			System.out.print(y);
			// Print the board values
			for(int x = 0; x < lengthOf(y); x++) {
				int marbleInt = get(x, y);
				char marble;
				if (marbleInt == EMPTY) {
					marble = '+';
				} else if (marbleInt == BLACK) {
					marble = '@';
				} else if (marbleInt == WHITE) {
					marble = 'O';
				} else {
					throw new IllegalStateException();
				}
				System.out.print(" " + marble);
			}
			// Print the x axis number (if there is one)
			if (y < MIN - 1) {
				System.out.print(" " + (MAX - numS));
			}
			System.out.println();
		}
		// Print bottom x axis numbers
		for (int s = 0; s <= MIN; s++) {
			System.out.print(" ");
		}
		for (int x = 0; x < MIN; x++) {
			System.out.print(" " + x);
		}
		System.out.println();
	}

}

package com.teamname.abalone;


/**
 * The board is represented on two axes that are 120 degrees from each other.
 * The coordinates are of the form (x, y) where the Y-axis is horizontal to
 * the board and the X-axis is at an angle -30 degrees from vertical. The
 * positive direction is towards the north and east from the board. The north
 * side of the board is always home to the white marbles.
 * 
 * For example, the center and left-most spot on this board (marked A) is
 * (0, 4) while the center and right-most spot (marked B) is (8, 5).
 * 
 *             8 O O O O O
 *            7 O O O O O O
 *           6 + + O O O + +
 *          5 + + + + + + + B
 *         4 A + + + + + + + +
 *          3 + + + + + + + + 8
 *           2 + + @ @ @ + + 7
 *            1 @ @ @ @ @ @ 6
 *             0 @ @ @ @ @ 5
 *                0 1 2 3 4
 * 
 * In memory, the board contents are represented as a 2D array of ints:
 * 
 * 	board[8] = {2, 2, 2, 2, 2}
 * 	board[7] = {2, 2, 2, 2, 2, 2}
 * 	board[6] = {0, 0, 2, 2, 2, 0, 0}
 * 	board[5] = {0, 0, 0, 0, 0, 0, 0, B}
 * 	board[4] = {A, 0, 0, 0, 0, 0, 0, 0, 0}
 *  board[3] = {0, 0, 0, 0, 0, 0, 0, 0}
 *  board[2] = {0, 0, 1, 1, 1, 0, 0}
 *  board[1] = {1, 1, 1, 1, 1, 1}
 * 	board[0] = {1, 1, 1, 1, 1}
 * 
 * Each non-edge tile is adjacent to 6 others in the directions:
 * 
 *          NW     NE
 *             \ /
 *         W  - + -  E
 *             / \
 *          SW     SE
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
	public static final int SE = 3;
	public static final int E = 4;
	public static final int NE = 5;

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

	public Board(Board src) {
		board = new int[MAX][];
		for (int i = 0; i < MAX; i++) {
			board[i] = src.board[i].clone();
		}
	}

	/**
	 * Sets the state of the board to the initial player starting state.
	 */
	private void setupBoard() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < lengthOf(y); x++) {
				board[y][x] = EMPTY;
			}
		}

		for (int x = 0; x < lengthOf(0); x++) set(x, 0, BLACK);
		for (int x = 0; x < lengthOf(1); x++) set(x, 1, BLACK);
		set(2, 2, BLACK); set(3, 2, BLACK); set(4, 2, BLACK);

		for (int x = 0; x < lengthOf(8); x++) set(x + 4, 8, WHITE);
		for (int x = 0; x < lengthOf(7); x++) set(x + 3, 7, WHITE);
		set(4, 6, WHITE); set(5, 6, WHITE); set(6, 6, WHITE);
	}

	public int get(int x, int y) {
		if (y < MIN) {
			return board[y][x];
		} else {
			return board[y][x - y + MIN - 1];
		}
	}

	public void set(int x, int y, int state) {
		if (state != BLACK && state != WHITE && state != EMPTY)
			throw new IllegalArgumentException("Unknown state: "+state);
		if (y < MIN) {
			board[y][x] = state;
		} else {
			board[y][x - y + MIN - 1] = state;
		}
	}

	/**
	 * @param y The position along the Y axis to get the length of.
	 * @return A number between Board.MIN and Board.MAX.
	 */
	public int lengthOf(int y) {
		return board[y].length;
	}

	public int startOf(int y) {
		if (y < MIN) {
			return 0;
		} else {
			return y - MIN + 1;
		}
	}

	public int endOf(int y) {
		if (y < MIN) {
			return board[y].length - 1;
		} else {
			return MAX - 1;
		}
	}

	public int getRelative(int x, int y, int dir) {
		int[] rel = getRelativeCoords(x, y, dir);
		return this.get(rel[0], rel[1]);
	}

	public void setRelative(int x, int y, int dir, int state) {
		int[] rel = getRelativeCoords(x, y, dir);
		this.set(rel[0], rel[1], state);
	}

	/**
	 * @param x source
	 * @param y source
	 * @param dir Board.dir
	 * @return A pair of ints, representing the relative coordinate.
	 */
	public static int[] getRelativeCoords(int x, int y, int dir) {
		switch (dir) {
		case SW:
			return new int[]{ x - 1, y - 1};
		case W:
			return new int[]{ x - 1, y};
		case NW:
			return new int[]{ x, y + 1};
		case NE:
			return new int[]{ x + 1, y + 1};
		case E:
			return new int[]{ x + 1, y};
		case SE:
			return new int[]{ x, y - 1};
		default:
			throw new IllegalArgumentException("Unknown direction: "+dir);
		}
	}

	public static int getOppositeDirectionOf(int dir) {
		return NE - dir;
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
			for(int x = startOf(y); x <= endOf(y); x++) {
				int marbleInt = get(x, y);
				String marble;
				if (marbleInt == EMPTY) {
					marble = "·";
				} else if (marbleInt == BLACK) {
					marble = "⬢";
				} else if (marbleInt == WHITE) {
					marble = "⬡";
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

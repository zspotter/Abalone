package edu.rit.datacom.abalone.common;

public class GameMaster {

	// Maximum number of the player's marbles that can be moved per turn.
	public static final int MAX_MARBLES = 3;

	private int playerColor = Board.BLACK;

	private Board board;

	public GameMaster() {
		board = new Board();
	}

	public boolean makeMove(Move move) {
		// Make sure the correct player is making a move.
		if (move.getColor() != playerColor) return false;
		// Make sure a valid number of marbles are being moved.
		if (move.getMarbles().length > MAX_MARBLES
				|| move.getMarbles().length <= 0) return false;
		// Make sure all the marbles are the right color.
		for (int[] marble : move.getMarbles()) {
			if (board.get(marble[0], marble[1]) != move.getColor()) {
				return false;
			}
		}

		int dir = move.getDirection();
		int[][] marbles = move.getMarbles();
		// Figure out if this is an inline move or not.
		boolean inline = false;
		inline = (marbles.length == 1 || areInline(marbles, dir));

		// If not inline, is it broadside?
		if (!inline) {
			int dirA, dirB;
			if (dir == Board.NW || dir == Board.SE) {
				dirA = Board.NE;
				dirB = Board.W;
			} else if (dir == Board.NE || dir == Board.SW) {
				dirA = Board.NW;
				dirB = Board.W;
			} else {
				dirA = Board.NE;
				dirB = Board.NW;
			}

			// If not inline or broadside, bad move.
			if (!areInline(marbles, dirA)
					&& !areInline(marbles, dirB)) return false;
		}

		// Attempt to update a copy of the board.
		int opponentColor = (playerColor == Board.BLACK)? Board.WHITE : Board.BLACK;
		Board copy = new Board(board);
		if (inline) {
			// Inline push.
			// Find the marble at the head and tail of the move train.
			int[] head = null;
			int[] tail = null;
			for (int[] marble : marbles) {
				int[] rel = Board.getRelativeCoords(marble[0], marble[1], dir);
				if (head == null && (!copy.onBoard(rel[0], rel[1])
						|| copy.get(rel[0], rel[1]) != playerColor)) {
					head = marble;
				}
				if (tail == null) {
					rel = Board.getRelativeCoords(marble[0], marble[1],
							Board.getOppositeDirectionOf(dir));
					boolean isTail = true;
					for (int[] m : marbles) {
						if (m[0] == rel[0] && m[1] == rel[1]) {
							isTail = false;
							break;
						}
					}
					if (isTail) tail = marble;
				}
			}
			// None of the marbles in the list were actually the head? Bad move.
			if (head == null) return false;
			// Push opponent marbles if any and if legal.
			int opp = marbles.length - 1;
			int[] oppHead = Board.getRelativeCoords(head[0], head[1], dir);
			while (opp > 0) {
				if (!copy.onBoard(oppHead[0], oppHead[1])) {
					// Pushing one opponent marble off the board. Special case.
					break;
				}
				if (copy.get(oppHead[0], oppHead[1]) == Board.EMPTY) {
					break;
				}
				if (copy.get(oppHead[0], oppHead[1]) != opponentColor) {
					// Marble to be pushed isn't opponent color. Bad move.
					return false;
				}
				oppHead = Board.getRelativeCoords(oppHead[0], oppHead[1], dir);
				opp--;
			}
			if (copy.onBoard(oppHead[0], oppHead[1]) && copy.get(oppHead[0], oppHead[1]) != Board.EMPTY) {
				// Too many opponent marbles to push. Bad move.
				return false;
			}
			if (copy.onBoard(oppHead[0], oppHead[1]) && opp != marbles.length - 1) {
				copy.set(oppHead[0], oppHead[1], opponentColor);
			}
			// Push own marbles.
			copy.set(tail[0], tail[1], Board.EMPTY);
			int[] newHead = Board.getRelativeCoords(head[0], head[1], dir);
			if (copy.onBoard(newHead[0], newHead[1])) {
				copy.setRelative(head[0], head[1], dir, playerColor);
			}
		} else {
			// Broadside slide.
			for (int[] marble : marbles) {
				if (copy.getRelative(marble[0], marble[1], dir) != Board.EMPTY) {
					// Not an empty dest spot. Bad move!
					return false;
				}
				copy.set(marble[0], marble[1], Board.EMPTY);
				int[] relCoords = Board.getRelativeCoords(marble[0], marble[1], dir);
				copy.set(relCoords[0], relCoords[1], move.getColor());
			}
		}

		// If the move was successfully made, update the current board.
		board = copy;

		// Update who should go next.
		playerColor = opponentColor;
		return true;
	}

	private boolean areInline(int[][] marbles, int dir) {
		for (int[] coord : marbles) {
			int[] forward = Board.getRelativeCoords(coord[0], coord[1], dir);
			int[] backward = Board.getRelativeCoords(coord[0], coord[1],
					Board.getOppositeDirectionOf(dir));

			// Make sure either the frontward or backward marble is also being pushed.
			boolean adjacent = false;
			for (int[] marble : marbles) {
				if ((marble[0] == forward[0] && marble[1] == forward[1])
						|| (marble[0] == backward[0] && marble[1] == backward[1])) {
					adjacent = true;
					break;
				}
			}
			// If not adjacent to a forward or backward pushed marble, not inline.
			if (!adjacent) return false;
		}
		return true;
	}

	public Board getBoardCopy() {
		return new Board(board);
	}

}

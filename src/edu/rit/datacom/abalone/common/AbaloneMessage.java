package edu.rit.datacom.abalone.common;


/**
 * The abstract super of all AbaloneMessages to be sent over the server. This
 * contains a handful of different messages to send.
 */
public abstract class AbaloneMessage {

	/**
	 * Sent by the client to the server requesting to join a game. If the requested
	 * game doesn't exist, it will be created. A ResponseJoined will be sent back.
	 */
	public static class RequestJoin extends AbaloneMessage {

		private String gameId;

		/**
		 * @param gameId The game session name to join.
		 */
		public RequestJoin(String gameId) {
			this.gameId = gameId;
		}

		/**
		 * @return The requested game id.
		 */
		public String getGameId() {
			return gameId;
		}

	}

	/**
	 * An object that notifies the server that the client is leaving. Not required
	 * to send before quitting, but it is the friendly thing to do!
	 */
	public static class RequestLeave extends AbaloneMessage {

	}

	/**
	 * An object requesting a player move.
	 */
	public static class RequestMove extends AbaloneMessage {

		private Move move;

		public RequestMove(Move move) {
			this.move = move;
		}

		public Object getMove() {
			return move;
		}

	}

	/**
	 * Sent by the server to notify the client that the board has been updated.
	 */
	public static class ResponseBoardUpdate extends AbaloneMessage {

		private Board board;
		private int turnColor;

		public ResponseBoardUpdate(Board board, int turnColor) {
			this.board = board;
			this.turnColor = turnColor;
		}

		public Board getBoard() {
			return board;
		}

		public int getColor() {
			return turnColor;
		}

	}

	/**
	 * Sent by the server to notify the client that they have either joined the
	 * requested game or have failed to join the game.
	 */
	public static class ResponseJoined extends AbaloneMessage {

		private int color;

		public ResponseJoined(int color) {
			this.color = color;
		}

		public int getColor() {
			return color;
		}

		public boolean hasJoined() {
			return color >= 0 && color != Board.EMPTY;
		}

	}

	/**
	 * Sent by the server to notify the client that they have been kicked from
	 * the game, either because the game is over or another player has quit.
	 */
	public static class ResponseLeftGame extends AbaloneMessage {

	}

	/**
	 * Sent by the server to notify the client that their last move was
	 * rejected or illegal.
	 */
	public static class ResponseMoveRejected extends AbaloneMessage {

	}

}

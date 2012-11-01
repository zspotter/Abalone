package edu.rit.datacom.abalone.common;


/**
 * ModelListener is an interface that defines what messages can be sent to the
 * player client.
 *
 */
public interface ModelListener {

	/**
	 * Called after a player has joined the game.
	 * @param color The player color
	 */
	public void joinedGame(int color);

	/**
	 * Called after any move is made or a the game has started.
	 * @param board The new state of the board.
	 * @param color The color of the next move.
	 */
	public void updateBoard(Board board, int color);

	/**
	 * Called in response to a failed move attempt. The current player is
	 * expected to send another move.
	 */
	public void rejectMove();

	/**
	 * Called to notify the player that they have been disconnected because the
	 * either the game ended or the other player left.
	 */
	public void leaveGame();


}

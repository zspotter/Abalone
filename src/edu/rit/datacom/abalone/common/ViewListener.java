package edu.rit.datacom.abalone.common;


/**
 * ViewListener is an interface that for defining what types of actions a
 * client player can make. Implementors of this must respond appropriately to
 * requests.
 */
public interface ViewListener {

	/**
	 * Called when a player attempts to join a game.
	 * @param gameid The id of the game the player has requested.
	 */
	public void joinGame(int gameid);

	/**
	 * Called when the player attempts a move.
	 * @param move The move object for the player.
	 */
	public void requestMove(Move move);

	/**
	 * Called when a player quits the game.
	 */
	public void leaveGame();

}

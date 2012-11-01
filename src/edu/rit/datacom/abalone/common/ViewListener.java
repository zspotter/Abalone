package edu.rit.datacom.abalone.common;


/**
 * ViewListener is an interface that for defining what types of actions a
 * client player can make. Implementors of this must respond appropriately to
 * requests.
 */
public interface ViewListener {

	/**
	 * Called when a player attempts to join a game.
	 * @param gameName The name of the game the player has requested. A new
	 * 	game will be created if the given one doesn't exist.
	 */
	public void joinGame(String gameName);

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

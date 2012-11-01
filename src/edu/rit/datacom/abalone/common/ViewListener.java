package edu.rit.datacom.abalone.common;

import edu.rit.datacom.abalone.common.AbaloneMessage.RequestJoin;
import edu.rit.datacom.abalone.common.AbaloneMessage.RequestMove;


/**
 * ViewListener is an interface that for defining what types of actions a
 * client player can make. Implementors of this must respond appropriately to
 * requests.
 */
public interface ViewListener {

	/**
	 * Called when a player attempts to join a game.
	 * @param msg
	 */
	public void joinGame(RequestJoin msg);

	/**
	 * Called when the player attempts a move.
	 * @param msg
	 */
	public void requestMove(RequestMove msg);

	/**
	 * Called when a player quits the game.
	 */
	public void leaveGame();

}

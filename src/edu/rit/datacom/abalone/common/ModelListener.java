package edu.rit.datacom.abalone.common;

import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;



/**
 * ModelListener is an interface that defines what messages can be sent to the
 * player client.
 *
 */
public interface ModelListener {

	/**
	 * Called after a player has joined the game.
	 * @param msg
	 */
	public void gameJoined(ResponseJoined msg);

	/**
	 * Called after any move is made or a the game has started.
	 * @param msg
	 */
	public void boardUpdated(ResponseBoardUpdate msg);

	/**
	 * Called in response to a failed move attempt. The current player is
	 * expected to send another move.
	 */
	public void moveRejected();

	/**
	 * Called to notify the player that they have been disconnected because the
	 * either the game ended or the other player left.
	 */
	public void leftGame();


}

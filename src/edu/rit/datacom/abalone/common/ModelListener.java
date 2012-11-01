package edu.rit.datacom.abalone.common;

import edu.rit.datacom.abalone.common.message.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.message.ResponseJoined;


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
	public void joinedGame(ResponseJoined msg);

	/**
	 * Called after any move is made or a the game has started.
	 * @param msg
	 */
	public void updateBoard(ResponseBoardUpdate msg);

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

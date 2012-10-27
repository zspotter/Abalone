package edu.rit.datacom.abalone.common;

/**
 * An interface representing the state of the board. Shared by server and client.
 * @author Zachary Potter
 *
 */
public interface GameState {

	/**
	 * @return The state of the board.
	 */
	public Board getBoard();

	/**
	 * @return The number of white marbles on the board.
	 */
	public int getWhiteCount();

	/**
	 * @return The number of black marbles on the board.
	 */
	public int getBlackCount();

}

package edu.rit.datacom.abalone.common;

/**
 * The "model" and "model proxy" for the server and client to implement.
 * @author Zachary Potter
 *
 */
public interface GameMaster {

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

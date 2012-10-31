package edu.rit.datacom.abalone.common;

/**
 * 
 * Abalone Messages:
 * 
 * Client:
 * createGame(game id): Request to create a new game with a custom game id
 * joinGame(game id): Request to join the game indicated by the game id
 * sendMove(move) Send a move request
 * leaveGame() Sent when quiting a game
 * 
 * Server:
 * joinedGame(color): Sent as a response to createGame or joinGame. Notifies the player with their color
 * updateBoard(board): Sent after either player moved or upon joining/creating a game
 * rejectMove(): Sent in response to a bad move request
 * leftGame(color): Sent when a player quits or is being kicked by the server
 * 
 * @author Zachary Potter
 * 
 */
public class Abalone {

	public static void main(String[] args) {
		GameMaster game = new GameMaster();

		game.getBoardCopy().printBoard();

		/* pushing tests */
		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {0,0}, {1,1}, {2,2} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.W,
				new int[][]{ {4,6}, {5,6}, {6,6} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {1,1}, {2,2}, {3,3} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {3,6} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {2,2}, {3,3}, {4,4} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {2,5} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {3,3}, {4,4}, {5,5} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {1,4} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {4,4}, {5,5}, {6,6} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {8,7} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {5,5}, {6,6}, {7,7} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.E,
				new int[][]{ {5,7}, {6,7} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {8,8} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SE,
				new int[][]{ {0,3} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();



		/* simple movement tests
		game.makeMove(new Move(Board.BLACK, Board.NE,
				new int[][]{ {2,2}, {3,2}, {4,2} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {7,8}, {6,7}, {5,6} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.BLACK, Board.NW,
				new int[][]{ {3,3}, {4,3} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();

		game.makeMove(new Move(Board.WHITE, Board.SW,
				new int[][]{ {5,6}, {4,5} }));
		System.out.println("\n==========\n");
		game.getBoardCopy().printBoard();
		 */
	}

}

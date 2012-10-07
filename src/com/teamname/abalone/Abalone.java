package com.teamname.abalone;

public class Abalone {

	public static void main(String[] args) {
		GameMaster game = new GameMaster();

		game.getBoardCopy().printBoard();

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
	}

}

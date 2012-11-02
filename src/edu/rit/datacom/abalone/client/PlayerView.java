package edu.rit.datacom.abalone.client;

import java.util.Scanner;

import edu.rit.datacom.abalone.common.AbaloneMessage.RequestMove;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.Move;
import edu.rit.datacom.abalone.common.ViewListener;

public class PlayerView implements ModelListener{

	private Scanner _userIn;

	private ViewListener _viewListener;

	private Board _board;
	private int _playerColor;
	private int _turnColor;

	private String _colorName;
	private String _opponentColorName;

	public PlayerView() {
		_userIn = new Scanner(System.in);
		System.out.println("Joining game...");
	}

	public void setViewListener(ViewListener viewListener) {
		_viewListener = viewListener;
	}

	@Override
	public void gameJoined(ResponseJoined msg) {
		if (!msg.hasJoined()) {
			System.out.println("The requested game was full.");
			quit();
		} else {
			_playerColor = msg.getColor();
			if (msg.getColor() == Board.BLACK) {
				_colorName = "Black";
				_opponentColorName = "White";
			} else {
				_colorName = "White";
				_opponentColorName = "Black";
			}

			System.out.println("Joined game as " + _colorName + "!");
			System.out.println("Waiting for " + _opponentColorName + "...\n");
		}
	}

	@Override
	public void boardUpdated(ResponseBoardUpdate msg) {
		_board = msg.getBoard();
		_turnColor = msg.getColor();
		displayBoard();
		System.out.println();
		if (_turnColor == _playerColor) {
			promptMove();
		}
	}

	@Override
	public void moveRejected() {
		System.out.println("\nIllegal move! Try again.\n");

		if (_turnColor == _playerColor) {
			promptMove();
		}
	}

	@Override
	public void leftGame() {
		System.out.println("\nYou have been disconnected by the server.");
		quit();
	}

	private void displayBoard() {
		// Print who's turn it is.
		if (_turnColor == _playerColor) {
			System.out.println("It is your ("+_colorName+"'s) move.");
		} else {
			System.out.println("It is "+_opponentColorName+"'s move. Please wait...");
		}
		System.out.println("---------");
		// Print score.
		System.out.println("Black marbles remaining: " +
				(Math.max(Board.START_COUNT - _board.countBlack(), Board.GOAL))+"/"+Board.GOAL);
		System.out.println("White marbles remaining: " +
				(Math.max(Board.START_COUNT - _board.countWhite(), Board.GOAL))+"/"+Board.GOAL);
		// Print board.
		System.out.println();
		_board.printBoard();
	}

	private void promptMove() {
		// Prompt for list of marble coordinates.
		int[][] coords = new int[3][]; // Max number of marbles = 3
		int num = 0; // Count number of marbles so far.
		String line;
		int x = -1;
		int y = -1;
		while(num < coords.length) {
			System.out.println("\nPush a marble (enter 'd' when done):");
			System.out.print(" X: ");
			line = _userIn.next();

			// Break if line starts with a 'd'
			if (line.charAt(0) == 'd') break;

			try {
				x = Integer.parseInt(line);
				System.out.print(" Y: ");
				line = _userIn.next();
				y = Integer.parseInt(line);
			} catch (NumberFormatException e) {
				// Prompt again.
				System.out.println("Input not an int.");
				continue;
			}

			// Add marble to list.
			coords[num] = new int[] {x, y};
			num++;
		}
		// Prompt for direction.
		System.out.println("Enter a direction to push:");
		System.out.println(" 0: SW");
		System.out.println(" 1: W");
		System.out.println(" 2: NW");
		System.out.println(" 3: SE");
		System.out.println(" 4: E");
		System.out.println(" 5: NE");

		while(true) {
			System.out.print("Direction: ");
			try {
				line = _userIn.next();
				x = Integer.parseInt(line);
				break;
			} catch (NumberFormatException e) {
				// Prompt again.
				System.out.println("Input not an int.");
			}
		}
		// Create Move and send to ModelProxy.
		Move move = new Move(_playerColor, x, coords);
		_viewListener.requestMove(new RequestMove(move));
	}

	private void quit() {
		System.out.println("Bye!");
		System.exit(0); // Probably want to change this... TODO
	}


}

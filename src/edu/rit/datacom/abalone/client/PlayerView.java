package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;

public class PlayerView implements ModelListener{

	private ViewListener _viewListener;

	private Board _board;
	private int _playerColor;
	private int _turnColor;

	private String _colorName;
	private String _opponentColorName;

	public PlayerView() {
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
			if (msg.getColor() == Board.BLACK) {
				_colorName = "Black";
				_opponentColorName = "White";
			} else {
				_colorName = "White";
				_opponentColorName = "Black";
			}

			System.out.println("Joined game as " + _colorName + "!");
			System.out.println("Waiting for " + _opponentColorName + "...");
		}
	}

	@Override
	public void boardUpdated(ResponseBoardUpdate msg) {
		_board = msg.getBoard();
		displayBoard();
		if (msg.getColor() == _playerColor) {
			promptMove();
		}
	}

	@Override
	public void moveRejected() {
		System.out.println("Illegal move! Try again.");
		promptMove();
	}

	@Override
	public void leftGame() {
		System.out.println("You have been disconnected by the server.");
		quit();
	}

	private void displayBoard() {
		// Print who's turn it is.
		if (_turnColor == _playerColor) {
			System.out.println("It is your ("+_colorName+"'s) move.");
		} else {
			System.out.println("It is "+_opponentColorName+"'s name.");
		}
		// Print score.
		System.out.println("Black marbles remaining: " +
				(Board.START_COUNT - _board.countBlack())+"/"+Board.GOAL);
		System.out.println("White marbles remaining: " +
				(Board.START_COUNT - _board.countWhite())+"/"+Board.GOAL);
		// Print board.
		_board.printBoard();

	}

	private void promptMove() {
		// Prompt for list of marble coordinates.
		System.out.println("haaaaaaaaaaaaaa");
		// Prompt for direction.
		// Create Move and send to ModelProxy.
	}

	private void quit() {
		System.out.println("Bye!");
		System.exit(0); // Probably want to change this... TODO
	}


}

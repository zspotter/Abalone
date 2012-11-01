package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;

public class ModelClone implements ModelListener {

	private ModelListener _modelListener;
	private Board _board;
	private int _color = -1;
	private int _turnColor = -1;

	public void setModelListener(ModelListener modelListener){
		_modelListener = modelListener;
	}

	public Board getBoard() {
		return _board;
	}

	public int getTurnColor() {
		return _turnColor;
	}

	public int getPlayerColor() {
		return _color;
	}

	@Override
	public void joinedGame(ResponseJoined msg) {
		_color = msg.getColor();

		_modelListener.joinedGame(msg);
	}

	@Override
	public void updateBoard(ResponseBoardUpdate msg) {
		_board = msg.getBoard();
		_turnColor = msg.getColor();

		_modelListener.updateBoard(msg);
	}

	@Override
	public void rejectMove() {
		_modelListener.rejectMove();
	}

	@Override
	public void leaveGame() {
		_turnColor = -1;

		_modelListener.leaveGame();
	}

}

package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;

public class ModelClone implements ModelListener {

	private ModelListener _modelListener;
	private Board _board;
	private int _color;
	private int _turnColor;

	public void setModelListener(ModelListener modelListener){
		_modelListener = modelListener;
	}

	public Board getBoard() {
		return _board;
	}

	@Override
	public void joinedGame(int color) {
		_color = color;
		_modelListener.joinedGame(color);
	}

	@Override
	public void updateBoard(Board board, int color) {
		_board = board;
		_turnColor = color;
		_modelListener.updateBoard(board, color);
	}

	@Override
	public void rejectMove() {
		_modelListener.rejectMove();
	}

	@Override
	public void leaveGame() {
		_modelListener.leaveGame();
	}

}

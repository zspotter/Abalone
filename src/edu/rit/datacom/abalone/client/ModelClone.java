package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.message.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.message.ResponseJoined;

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
		_modelListener.leaveGame();
	}

}

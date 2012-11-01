package edu.rit.datacom.abalone.client;

import java.io.IOException;

import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;

public class ModelClone implements ModelListener {

	private Board _board = new Board();
	private ModelListener _modelListener;

	public void setModelListener(ModelListener modelListener){
		_modelListener = modelListener;
	}

	public Board getBoard() {
		return _board;
	}

	@Override
	public void joinedGame(int session) throws IOException {
		_modelListener.joinedGame(session);

	}

	@Override
	public void updateBoard() throws IOException {
		_modelListener.updateBoard();
	}

	@Override
	public void rejectMove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub

	}

}

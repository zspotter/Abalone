package com.teamname.abalone;

import java.io.IOException;

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
	public void leftGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeMove(int x, int y, int color) throws IOException {
		_modelListener.makeMove(x, y, color);
		_board.set(x, y, color); // This may or may not be correct
	}

}

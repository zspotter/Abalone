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
	public void joinedGame(int color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		
	}

}

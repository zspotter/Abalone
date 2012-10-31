package edu.rit.datacom.abalone.server;

import java.io.IOException;
import java.util.LinkedList;

import edu.rit.datacom.abalone.client.ViewListener;
import edu.rit.datacom.abalone.common.Board;

public class AbaloneModel implements ViewListener {
	
    private Board _board = new Board();
    private LinkedList<ModelListener> _listenerList = new LinkedList<ModelListener>();

	@Override
	public void joinGame(ViewProxy proxy, int gameid) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leaveGame(int gameid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resetBoard(int gameid) {
		// TODO Auto-generated method stub
		
	}

	public void addModelListener(ModelListener modelListener) {
        try
        {
        // Pump up the new client with the current state of the Go board.
        for (int x = 0; x < Board.MAX; ++x)
            {
            for (int y = 0; y < Board.MAX; ++y)
                {
                int color = _board.get(x, y);
                if (color != 0)
                    {
                    modelListener.makeMove (x, y, color);
                    }
                }
            }

        _listenerList.add (modelListener);
        }

    catch (IOException exc)
        {}
		
	}

	@Override
	public void sendMove(int x, int y, int color) throws IOException {
		// TODO Auto-generated method stub
		
	}

}

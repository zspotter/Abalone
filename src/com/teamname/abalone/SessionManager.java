package com.teamname.abalone;

import java.io.IOException;
import java.util.HashMap;

public class SessionManager implements ViewListener {
	
    private HashMap<Integer,AbaloneModel> _sessions = new HashMap<Integer,AbaloneModel>();
	
	public SessionManager(){}

	@Override
	public void joinGame(ViewProxy proxy, int gameid) throws IOException {
		AbaloneModel model = _sessions.get (gameid);
        if (model == null) // if there is no game yet
            {
            model = new AbaloneModel(); // make a new game session
            _sessions.put (gameid, model);
            }
        model.addModelListener (proxy);
        proxy.setViewListener (model);
		
	}

	public void leaveGame(int gameid) {}

	public void resetBoard(int gameid) {}

	public void sendMove(int x, int y, int color) throws IOException {}

}

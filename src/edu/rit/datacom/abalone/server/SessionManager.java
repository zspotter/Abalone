package edu.rit.datacom.abalone.server;

import java.io.IOException;
import java.util.HashMap;

public class SessionManager {

	private static HashMap<String,AbaloneModel> _sessions = new HashMap<String,AbaloneModel>();

	public static void joinGame(ViewProxy proxy, String gameid) throws IOException {
		AbaloneModel model = _sessions.get (gameid);
		if (model == null) // if there is no game yet
		{
			model = new AbaloneModel(); // make a new game session
			_sessions.put (gameid, model);
		}
		if (!model.isFull()) {
			model.addModelListener (proxy);
			proxy.setViewListener (model);
		} else {
			// Game was full.
			// TODO handle this case
		}

	}

}

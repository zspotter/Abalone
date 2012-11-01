package edu.rit.datacom.abalone.server;

import java.io.IOException;
import java.util.HashMap;

import edu.rit.datacom.abalone.common.AbaloneMessage.RequestJoin;
import edu.rit.datacom.abalone.common.AbaloneMessage.RequestMove;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.ViewListener;

public class SessionManager implements ViewListener {

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
			proxy.gameJoined(new ResponseJoined(-1));
		}

	}

	public static void endGame(AbaloneModel game) {
		_sessions.remove(game);
	}

	public void joinGame(RequestJoin msg) {}

	public void requestMove(RequestMove msg) {}

	public void leaveGame() {}

}

package edu.rit.datacom.abalone.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import edu.rit.datacom.abalone.common.AbaloneMessage;
import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;

public class ViewProxy implements ModelListener {

	private static final char MSG_JOIN = 'J';
	private static final char MSG_UPDATE = 'U';
	private static final char MSG_REJECT = 'R';
	private static final char MSG_LEAVE = 'L';


	private Socket _socket;
	private DataOutputStream _out;
	private DataInputStream _in;
	private ViewListener _viewListener;

	private int _color;

	public ViewProxy(Socket socket) throws IOException {
		_socket = socket;
		_out = new DataOutputStream (_socket.getOutputStream());
		_in = new DataInputStream (_socket.getInputStream());

		new ReaderThread().start();
	}

	public void setViewListener(ViewListener viewListener) {
		_viewListener = viewListener;
	}

	@Override
	public void joinedGame(ResponseJoined msg) {
		// TODO

	}

	@Override
	public void updateBoard(ResponseBoardUpdate msg) {
		// TODO
	}

	@Override
	public void rejectMove() {
		// TODO
	}

	@Override
	public void leaveGame() {
		// TODO
	}

	/**
	 * A constant reader for messages from the client.
	 */
	private class ReaderThread
	extends Thread
	{
		@Override
		public void run()
		{
			try
			{
				while(true)
				{

					// TODO implement with ObjectInputStream

					get next message

					// If this client hasn't joined the game yet, only accept
					// join requests.
					if (_viewListener == null) {
						if ( message is a join game message ) {
							SessionManager.joinGame(ViewProxy.this, session name);
						}
						// Skip other message checks.
						continue;
					}

					// TODO listen for other ViewListener messages
				}
			}
			catch (IOException exc)
			{
				try {
					_socket.close();
				}
				catch (IOException exc2)
				{
				}
			}
		}
	}
}

package edu.rit.datacom.abalone.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import edu.rit.datacom.abalone.common.AbaloneMessage;
import edu.rit.datacom.abalone.common.AbaloneMessage.RequestJoin;
import edu.rit.datacom.abalone.common.AbaloneMessage.RequestLeave;
import edu.rit.datacom.abalone.common.AbaloneMessage.RequestMove;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseLeftGame;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseMoveRejected;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;

public class ViewProxy implements ModelListener {

	private Socket _socket;
	private ObjectOutputStream _out;
	private ObjectInputStream _in;
	private ViewListener _viewListener;

	private int _color;

	public ViewProxy(Socket socket) throws IOException {
		_socket = socket;
		_out = new ObjectOutputStream (_socket.getOutputStream());
		_in = new ObjectInputStream (_socket.getInputStream());

		new ReaderThread().start();
	}

	public void setViewListener(ViewListener viewListener) {
		_viewListener = viewListener;
	}

	@Override
	public void gameJoined(ResponseJoined msg) {
		try {
			_out.writeObject(msg);
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nError sending joined message.");
		}

	}

	@Override
	public void boardUpdated(ResponseBoardUpdate msg) {
		try {
			_out.writeObject(msg);
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nError sending updated board message.");
		}
	}

	@Override
	public void moveRejected() {
		try {
			_out.writeObject(new ResponseMoveRejected());
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nError sending rejected move message.");
		}
	}

	@Override
	public void leftGame() {
		try {
			_out.writeObject(new ResponseLeftGame());
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nError sending leave game message.");
		}
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
			while(true)
			{
				try {
					// Get next message from client.
					Object msg = _in.readObject();

					// If this client hasn't joined the game yet, only accept
					// join requests.
					if (_viewListener == null) {
						if (msg instanceof RequestJoin) {
							SessionManager.joinGame(ViewProxy.this, ((RequestJoin) msg).getGameId());
						}
						// Skip other message checks.
						continue;
					}

					// Listen for only AbaloneMessage's
					if (!(msg instanceof AbaloneMessage)) continue;

					if (msg instanceof RequestMove) {
						_viewListener.requestMove((RequestMove) msg);
					} else if (msg instanceof RequestLeave) {
						_viewListener.leaveGame();
					}

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

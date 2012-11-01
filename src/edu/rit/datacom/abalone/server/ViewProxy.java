package edu.rit.datacom.abalone.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import edu.rit.datacom.abalone.common.Board;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;

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
	public void joinedGame(int color) {
		try {
			_out.writeByte (MSG_JOIN);
			_out.writeInt(color);
			_out.flush();
			_color = color;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void updateBoard(Board board, int color) {
		try {
			_out.writeByte (MSG_UPDATE);
			_out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void rejectMove() {
		try {
			_out.writeByte (MSG_REJECT);
			_out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void leaveGame() {
		try {
			_out.writeByte (MSG_LEAVE);
			_out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

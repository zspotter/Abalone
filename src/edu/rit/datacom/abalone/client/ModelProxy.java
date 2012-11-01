package edu.rit.datacom.abalone.client;

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
import edu.rit.datacom.abalone.common.ViewListener;

public class ModelProxy implements ViewListener {

	private Socket _socket;
	private ObjectOutputStream _out;
	private ObjectInputStream _in;
	private ModelClone _modelClone;

	public ModelProxy(Socket socket) throws IOException {
		_socket = socket;
		_out = new ObjectOutputStream (_socket.getOutputStream());
		_in = new ObjectInputStream (_socket.getInputStream());

		// Start listening for server messages.
		new ReaderThread().start();
	}

	public void setModelListener(ModelClone model) {
		_modelClone = model;
	}

	@Override
	public void joinGame(RequestJoin msg) {
		try {
			_out.writeObject(msg);
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("/nError sending join request.");
		}
	}

	@Override
	public void requestMove(RequestMove msg) {
		try {
			_out.writeObject(msg);
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("/nError sending move request.");
		}
	}

	@Override
	public void leaveGame() {
		try {
			_out.writeObject(new RequestLeave());
			_out.flush();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("/nError sending leave request.");
		}
	}

	private class ReaderThread
	extends Thread
	{
		@Override
		public void run()
		{
			while(true)
			{
				try {

					// Get input from server.
					Object msg = _in.readObject();

					// Ignore messages that aren't an AbaloneMessage
					if (!(msg instanceof AbaloneMessage)) continue;

					if (msg instanceof ResponseJoined) {
						_modelClone.gameJoined((ResponseJoined) msg);
					} else if (msg instanceof ResponseBoardUpdate) {
						_modelClone.boardUpdated((ResponseBoardUpdate) msg);
					} else if (msg instanceof ResponseLeftGame) {
						_modelClone.leftGame();
					} else if (msg instanceof ResponseMoveRejected) {
						_modelClone.moveRejected();
					}

				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

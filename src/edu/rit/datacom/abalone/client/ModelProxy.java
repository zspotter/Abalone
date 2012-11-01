package edu.rit.datacom.abalone.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import edu.rit.datacom.abalone.common.Move;
import edu.rit.datacom.abalone.common.ViewListener;

public class ModelProxy implements ViewListener {


	private Socket _socket;
	private DataOutputStream _out;
	private DataInputStream _in;
	private ModelClone _modelClone;

	public ModelProxy(Socket socket) throws IOException {
		_socket = socket;
		_out = new DataOutputStream (_socket.getOutputStream());
		_in = new DataInputStream (_socket.getInputStream());

	}

	public void setModelListener(ModelClone model) {
		_modelClone = model;
	}

	@Override
	public void joinGame(String gameid) {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestMove(Move move) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub

	}

	private class ReaderThread
	extends Thread
	{
		@Override
		public void run()
		{
			while(true)
			{
				// TODO get input from server
			}
		}
	}
}

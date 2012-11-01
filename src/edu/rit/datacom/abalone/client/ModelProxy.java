package edu.rit.datacom.abalone.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import edu.rit.datacom.abalone.common.ViewListener;
import edu.rit.datacom.abalone.server.ViewProxy;

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
	public void joinGame(ViewProxy proxy, int session) throws IOException  {
		_out.writeByte ('J');
		_out.writeInt(session);
		_out.flush();

	}

	@Override
	public void sendMove(int x, int y, int color) throws IOException {
		_out.writeByte('M');
		_out.writeInt(x);
		_out.writeInt(y);
		_out.writeInt(color);
		_out.flush();

	}

	@Override
	public void leaveGame(int gameid)  throws IOException {
		_out.writeByte('L');
		_out.writeInt(gameid);
		_out.flush();

	}

	@Override
	public void resetBoard(int gameid) throws IOException {
		_out.writeByte('R');
		_out.writeInt(gameid);
		_out.flush();
	}

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
					int x, y, color, session;
					byte b = _in.readByte();
					switch (b)
					{
					case 'J':
						session = _in.readByte();
						_modelClone.joinedGame(session);
						break;
					case 'U':
						_modelClone.updateBoard();
						break;
					case 'R':
						_modelClone.rejectMove();
						break;

					case 'L':
						_modelClone.leaveGame();
						break;
					default:
						// Ignore bad messages.
						break;
					}
				}
			}
			catch (IOException exc){
				try
				{
					_socket.close();
				}
				catch (IOException exc2){}
			}
		}
	}

}

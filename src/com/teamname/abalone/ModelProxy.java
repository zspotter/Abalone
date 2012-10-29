package com.teamname.abalone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

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
    public void run()
        {
        try
            {
            while(true)
                {
                int r, c, color;
                byte b = _in.readByte();
                switch (b)
                    {

                    default:
                        System.err.println ("Bad message");
                        break;
                    }
                }
            }
        catch (IOException exc){}
        finally
            {
            try
                {
                _socket.close();
                }
            catch (IOException exc){}
            }
        }
    }

}

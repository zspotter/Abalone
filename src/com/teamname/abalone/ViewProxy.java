package com.teamname.abalone;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ViewProxy implements ModelListener {
	
    private Socket _socket;
    private DataOutputStream _out;
    private DataInputStream _in;
    private ViewListener _viewListener;

	public ViewProxy(Socket socket) throws IOException {
		_socket = socket;
        _out = new DataOutputStream (_socket.getOutputStream());
        _in = new DataInputStream (_socket.getInputStream());
	}

	public void setViewListener(ViewListener viewListener) {
		if (_viewListener == null) // if we don't already have one started
        {
			_viewListener = viewListener;
			new ReaderThread().start();
        }
		else
        {
			_viewListener = viewListener;
        }
		
	}

	@Override
	public void joinedGame(int session) throws IOException {
		_out.writeByte ('J');
        _out.writeInt(session);
        _out.flush();
	}

	@Override
	public void updateBoard() throws IOException {
		_out.writeByte ('U');
        _out.flush();
		
	}

	@Override
	public void rejectMove() throws IOException {
		_out.writeByte ('R');
        _out.flush();
		
	}

	@Override
	public void leftGame() throws IOException {
		_out.writeByte ('L');
        _out.flush();
		
	}

	@Override
	public void makeMove(int x, int y, int color) throws IOException {
		_out.writeByte ('M');
        _out.writeInt(x);
        _out.writeInt(y);
        _out.writeInt(color);
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
                   int x, y, color, session;
                   byte b = _in.readByte();
                   switch (b)
                       {
                       case 'J':
                    	   session = _in.readByte();
                    	   _viewListener.joinGame(ViewProxy.this, session);
                    	   break;
                    	   
                       case 'M':
                    	   x = _in.readByte();
                    	   y = _in.readByte();
                    	   color = _in.readByte();
                    	   _viewListener.sendMove(x, y, color);
                    	   break;
                    	   
                       case 'L':
                    	   session = _in.readByte();
                    	   _viewListener.leaveGame(session);
                    	   break;
                    	   
                       case 'R':
                    	   session = _in.readByte();
                    	   _viewListener.resetBoard(session);
                    	   break;
                      
                       default:
                           System.err.println ("Bad message");
                           break;
                       }
                   }
               }
           catch (IOException exc)
               {
               }
           finally
               {
               try
                   {
                   _socket.close();
                   }
               catch (IOException exc)
                   {
                   }
               }
           }
       }

}

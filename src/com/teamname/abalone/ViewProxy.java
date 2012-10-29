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
	public void joinedGame(int color) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateBoard() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rejectMove() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void leftGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void makeMove(int x, int y, int color) {
		// TODO Auto-generated method stub
		
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
                    	   break;
                    	   
                       case 'M':
                    	   x = _in.readByte();
                    	   y = _in.readByte();
                    	   color = _in.readByte();
                    	   break;
                    	   
                       case 'L':
                    	   session = _in.readByte();
                    	   break;
                    	   
                       case 'R':
                    	   session = _in.readByte();
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

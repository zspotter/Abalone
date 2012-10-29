package com.teamname.abalone;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AbaloneServer {
	
	public AbaloneServer(String host, int port) throws IOException
	{
        ServerSocket serversocket = new ServerSocket();
        serversocket.bind (new InetSocketAddress (host, port));

        SessionManager manager = new SessionManager();

        while(true)
            {
            Socket socket = serversocket.accept();
            ViewProxy proxy = new ViewProxy (socket);
            proxy.setViewListener (manager);
            }
    }

}

package com.teamname.abalone;

import java.net.InetSocketAddress;
import java.net.Socket;

public class AbaloneClient {
	
	public AbaloneClient(String host, int port, int session)
	{
		Socket socket = new Socket();
        socket.connect (new InetSocketAddress (host, port));

        ModelClone model = new ModelClone();
        //GoUI view = new GoUI (session, model.getBoard());
        ModelProxy proxy = new ModelProxy (socket);
        model.setModelListener (view);
        view.setViewListener (proxy);
        proxy.setModelListener (model);
        proxy.joinGame (null, session);
	}

}

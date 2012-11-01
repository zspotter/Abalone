package edu.rit.datacom.abalone.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import edu.rit.datacom.abalone.common.AbaloneMessage.RequestJoin;

public class AbaloneClient {

	public static void main(String[] args) {
		if (args.length != 3) {
			System.err.println("Usage: java AbaloneClient <host> <port> <game-id>");
			System.exit(1);
		}

		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port number not an integer.");
			System.err.println("Usage: java AbaloneClient <host> <port> <game-id>");
			System.exit(1);
		}

		String host = args[0];
		String session = args[2];

		AbaloneClient client = new AbaloneClient(host, port, session);
	}

	public AbaloneClient(String host, int port, String session)
	{
		Socket socket = new Socket();
		ModelProxy proxy = null;
		try {
			socket.connect (new InetSocketAddress (host, port));
			proxy = new ModelProxy (socket);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("\nError connecting to "+host+":"+port);
			System.exit(1);
		}

		ModelClone model = new ModelClone();
		PlayerView view = new PlayerView ();

		model.setModelListener (view);
		view.setViewListener (proxy);
		proxy.setModelListener (model);
		proxy.joinGame (new RequestJoin(session));
	}

}

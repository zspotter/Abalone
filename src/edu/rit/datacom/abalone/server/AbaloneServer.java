package edu.rit.datacom.abalone.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class AbaloneServer {

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java AbaloneServer <host> <port>");
			System.exit(1);
		}

		int port = -1;
		try {
			port = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			System.err.println("Port number not an integer.");
			System.err.println("Usage: java AbaloneServer <host> <port>");
			System.exit(1);
		}

		String host = args[0];

		AbaloneServer server = new AbaloneServer(host, port);
	}

	public AbaloneServer(String host, int port) {
		ServerSocket serversocket = null;
		SessionManager manager = new SessionManager();
		try {
			serversocket = new ServerSocket();
			serversocket.bind (new InetSocketAddress (host, port));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("\nError binding socket on "+host+":"+port);
			System.exit(1);
		}

		while(true) {
			try {
				Socket socket = serversocket.accept();
				ViewProxy proxy = new ViewProxy (socket);
			} catch (IOException e) {
				// Ignore connections that fail for whatever reason.
			}
		}
	}

}

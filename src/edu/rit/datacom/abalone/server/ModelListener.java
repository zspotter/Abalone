package edu.rit.datacom.abalone.server;

import java.awt.Color;
import java.io.IOException;

/*
* Client:
* createGame(game id): Request to create a new game with a custom game id
* joinGame(game id): Request to join the game indicated by the game id
* sendMove(move) Send a move request
* leaveGame() Sent when quiting a game
* 
* Server:
* joinedGame(color): Sent as a response to createGame or joinGame. Notifies the player with their color
* updateBoard(board): Sent after either player moved or upon joining/creating a game
* rejectMove(): Sent in response to a bad move request
* leftGame(color): Sent when a player quits or is being kicked by the server
*/


//reports that things have happened
public interface ModelListener {
	
	//joinedGame(color): Sent as a response to createGame or joinGame. Notifies the player with their color
	public void joinedGame(int color) throws IOException;
	
	//updateBoard(board): Sent after either player moved or upon joining/creating a game
	public void updateBoard() throws IOException;
	
	//rejectMove(): Sent in response to a bad move request
	public void rejectMove() throws IOException;
	
	//leftGame(color): Sent when a player quits or is being kicked by the server
	public void leftGame() throws IOException;
	
	
	public void makeMove(int x, int y, int color) throws IOException;


}

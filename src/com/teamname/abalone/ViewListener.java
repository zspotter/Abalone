package com.teamname.abalone;

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

//ViewListerner does actions
public interface ViewListener {
	
	// joinGame(game id): Request to join the game indicated by the game id
	public void joinGame(ViewProxy proxy, int gameid) throws IOException; 
	
	// sendMove(move) Send a move request
	public void sendMove(int x, int y, int color) throws IOException;
	
	//leaveGame() Sent when quiting a game
	public void leaveGame(int gameid) throws IOException;
	
	// 
	public void resetBoard(int gameid) throws IOException;
	
	

}

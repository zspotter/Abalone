package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.AbaloneMessage.ResponseJoined;
import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;

public class PlayerView implements ModelListener{

	private ViewListener _viewListener;

	public PlayerView() {
		System.out.println("Joining game...");
	}

	public void setViewListener(ViewListener viewListener) {
		_viewListener = viewListener;
	}

	@Override
	public void joinedGame(ResponseJoined msg) {
		if (!msg.hasJoined()) {
			System.out.println("The requested game was full.");
			quit();
			return;
		}
		System.out.println("Joined game as " + msg.getColor() + "!");
		System.out.println("Waiting for other player...");
	}

	@Override
	public void updateBoard(ResponseBoardUpdate msg) {
		// TODO

	}

	@Override
	public void rejectMove() {
		// TODO Auto-generated method stub
		System.out.println("Illegal move! Try again.");
		promptMove();
	}

	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub
		System.out.println("You have been disconnected by the server.");
		quit();
	}

	private void promptMove() {

	}

	private void quit() {
		System.out.println("Bye!");
		System.exit(0); // Probably want to change this... TODO
	}


}

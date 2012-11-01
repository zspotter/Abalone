package edu.rit.datacom.abalone.client;

import edu.rit.datacom.abalone.common.ModelListener;
import edu.rit.datacom.abalone.common.ViewListener;
import edu.rit.datacom.abalone.common.message.ResponseBoardUpdate;
import edu.rit.datacom.abalone.common.message.ResponseJoined;

public class PlayerView implements ModelListener{

	public PlayerView() {
		System.out.println("Joining game...");
	}

	public void setViewListener(ViewListener viewListener) {
		//TODO
	}

	@Override
	public void joinedGame(ResponseJoined msg) {
		if (msg.hasJoined()) {
			System.out.println("The requested game was full.");
			quit();
			return;
		}
		System.out.println("Joined game as " + msg.getColor() + "!");
	}

	@Override
	public void updateBoard(ResponseBoardUpdate msg) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectMove() {
		// TODO Auto-generated method stub
		System.out.println("Illegal move! Try again.");
	}

	@Override
	public void leaveGame() {
		// TODO Auto-generated method stub
		System.out.println("You have been disconnected by the server.");
		quit();
	}

	private void quit() {
		System.out.println("Bye!");
		System.exit(0); // Probably want to change this... TODO
	}


}

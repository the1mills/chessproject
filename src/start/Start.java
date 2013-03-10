package start;

import models.ChessBoard;
import models.ChessPiece;
import controllers.ChessPlayer;
import controllers.Referee;

public class Start {

	public Start() {
	
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
			
			new Referee();
			
			Referee.startGame();
		
			
	}

}

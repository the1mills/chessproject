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
		

			ChessPlayer whitePlayer = new ChessPlayer(false);
			ChessPlayer blackPlayer = new ChessPlayer(true);
			
			ChessBoard c = new ChessBoard(whitePlayer, blackPlayer);
			
			String x = c.getLetterNumberOfBoard(4, 4);
			
			System.out.println(x);
			
			Referee r = new Referee(whitePlayer, blackPlayer);
			
			Referee.startGame();
		
			
	}

}

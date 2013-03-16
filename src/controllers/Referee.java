package controllers;

import views.ChessViewFrame;
import models.BoardCell;
import models.ChessBoard;
import models.ChessPiece;

public class Referee {

	private static Double gameCharacteristics;
	private static boolean gameOver = false;
	private volatile static ChessPlayer whitePlayer;
	private volatile static ChessPlayer blackPlayer;
	public static ChessViewFrame cvf;
	public static boolean paused = false;

	public Referee() {

		cvf = new ChessViewFrame();
		cvf.setVisible(true);
		whitePlayer = new ChessPlayer(false, cvf);
		blackPlayer = new ChessPlayer(true, cvf);
		
		whitePlayer.setUpOpponent();
		blackPlayer.setUpOpponent();

		ChessBoard c = new ChessBoard(whitePlayer, blackPlayer);

		Referee.whitePlayer = whitePlayer;
		Referee.blackPlayer = blackPlayer;

	}

	public static ChessPlayer getOppposingPlayer(ChessPlayer cp) {

		if (cp.isBlack()) {
			return whitePlayer;
		} else {
			return blackPlayer;
		}
	}

	public synchronized static void doWait(Object o)
			throws InterruptedException {
		o.wait();
	}

	public synchronized static void doNotify(Object o)
			throws InterruptedException {
		o.notify();
	}

	public static void startGame() {

		int i = 0;
		while (!isGameOver() && i < 500) {
			System.out.println("Move #: " + i);
			
			while(paused){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			whitePlayer.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			while(paused){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if(isGameOver()){
				continue;
			}
			
			blackPlayer.move();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}

		for (ChessPiece p : whitePlayer.getChessPieces()) {
			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {

				System.out.println("Piece: " + p.getPieceType()
						+ ", current square: "
						+ p.getCurrentCell().getSquareName()
						+ ", Destination Square: " + c.getSquareName());
			}

		}

		for (ChessPiece p : blackPlayer.getChessPieces()) {
			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {

				System.out.println("Piece: " + p.getPieceType()
						+ ", current square: "
						+ p.getCurrentCell().getSquareName()
						+ ", Destination Square: " + c.getSquareName());
			}

		}

	}

	public static Double getGameCharacteristics() {
		return gameCharacteristics;
	}

	public static void setGameCharacteristics(Double gameCharacteristics) {
		Referee.gameCharacteristics = gameCharacteristics;
	}

	public static ChessPlayer getWhitePlayer() {
		return whitePlayer;
	}

	public static void setWhitePlayer(ChessPlayer whitePlayer) {
		Referee.whitePlayer = whitePlayer;
	}

	public static ChessPlayer getBlackPlayer() {
		return blackPlayer;
	}

	public static void setBlackPlayer(ChessPlayer blackPlayer) {
		Referee.blackPlayer = blackPlayer;
	}

	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean gameOver) {
		Referee.gameOver = gameOver;
	}

}

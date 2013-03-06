package controllers;

import models.BoardCell;
import models.ChessPiece;

public class Referee {

	private static Double gameCharacteristics;
	private static boolean gameOver = false;
	private volatile static ChessPlayer whitePlayer;
	private volatile static ChessPlayer blackPlayer;

	public Referee(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {

		Referee.whitePlayer = whitePlayer;
		Referee.blackPlayer = blackPlayer;

	}
	
	public static ChessPlayer getOppposingPlayer(ChessPlayer cp){
		
		if(cp.isBlack()){
			return whitePlayer;
		}
		else{
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
			whitePlayer.move();
			blackPlayer.move();
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

package controllers;

import java.util.Vector;

import models.ChessPiece;

public class ChessPlayer extends Thread {

	private boolean isBlack;
	private Vector<ChessPiece> chessPieces;

	public ChessPlayer(boolean color) {
		
		isBlack = color;
		chessPieces = new Vector<ChessPiece>();
		
	}
	
	public synchronized void move(){
		
	}
	
	
	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public Vector<ChessPiece> getChessPieces() {
		return chessPieces;
	}

	public void setChessPieces(Vector<ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
	}


}

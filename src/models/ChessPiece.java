package models;

import java.util.Vector;

public abstract class ChessPiece {
	
	protected boolean isOnBlack;
	protected boolean isBlack;
	protected double valueOfPiece;
	protected String pieceType;
	protected BoardCell currentCell;
	protected boolean hasMadeFirstMove = false; 

	public ChessPiece(String pieceType, double value, boolean color, int row, int column) {
		
		this.isBlack = color;
		this.currentCell = new BoardCell(row,column);
		this.pieceType = pieceType;
		this.valueOfPiece = value;
	}
	
	public abstract void setValueOfPiece();
	
	public abstract Vector<BoardCell> findPossibleSquaresToMoveTo();
	
	public String getPieceType(){
		return pieceType;
	}

	
	public boolean isOnBlack() {
		return isOnBlack;
	}

	public void setOnBlack(boolean isOnBlack) {
		this.isOnBlack = isOnBlack;
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public double getValueOfPiece() {
		return valueOfPiece;
	}

	public void setValueOfPiece(double valueOfPiece) {
		this.valueOfPiece = valueOfPiece;
	}

	public BoardCell getCurrentCell() {
		return currentCell;
	}

	public void setCurrentCell(BoardCell currentCell) {
		this.currentCell = currentCell;
	}

	public void setPieceType(String pieceType) {
		this.pieceType = pieceType;
	}

	
	
}

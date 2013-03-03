package models;

public abstract class ChessPiece {
	
	protected boolean isOnBlack;
	protected boolean isBlack;
	protected double valueOfPiece;

	public ChessPiece(boolean color) {
		
		this.isBlack = color;
	}
	
	public abstract void setValueOfPiece();

}

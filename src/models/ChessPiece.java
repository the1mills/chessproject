package models;

import java.util.Observable;
import java.util.Vector;

import javax.swing.JLabel;

import controllers.Referee;

public abstract class ChessPiece extends Observable {

	protected boolean isOnBlack;
	protected boolean isBlack;
	protected double valueOfPiece;
	protected String pieceType;
	protected BoardCell currentCell;
	protected boolean hasMadeFirstMove = false; 
	private JLabel jlImage;

	public ChessPiece(String pieceType, BoardCell c, double value, boolean color, int row, int column) {
		
		this.isBlack = color;
		this.currentCell = c;
		this.pieceType = pieceType;
		this.valueOfPiece = value;
		
		this.addObserver(Referee.cvf);
		
		jlImage = getImageLabel();
		
		this.setChanged();
		this.notifyObservers(this);
	}
	
	public abstract BoardCell doTheSquareLoopThing(int i, int j);
	
	public abstract void setValueOfPiece();
	
	public abstract Vector<BoardCell> findPossibleSquaresToMoveTo();
	
	public abstract boolean canAndDoesProtectThisSquare(BoardCell bc);
	
	public String getPieceType(){
		return pieceType;
	}

	public JLabel getImageLabel(){
		
		String imgToGet = this.pieceType;
		
		if(this.isBlack){
		
			imgToGet += "Black";
		}
		else{
			imgToGet += "White";
		}
		
		return new JLabel(Referee.cvf.getPieceImgHash().get(imgToGet));
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
	
	public boolean isHasMadeFirstMove() {
		return hasMadeFirstMove;
	}

	public void setHasMadeFirstMove(boolean hasMadeFirstMove) {
		this.hasMadeFirstMove = hasMadeFirstMove;
	}

	public JLabel getJlImage() {
		return jlImage;
	}

	public void setJlImage(JLabel jlImage) {
		this.jlImage = jlImage;
	}


	
	
}

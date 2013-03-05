package models;

import java.util.Vector;

public class King extends ChessPiece {

	public King(String pieceType,boolean color, int row, int column) {
		
		super(pieceType,color,row,column);
	}

	@Override
	public void setValueOfPiece() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<BoardCell> findPossibleSquaresToMoveTo() {
		Vector<BoardCell> potentialCells = new Vector<BoardCell>();
		
		return potentialCells;
	}

}

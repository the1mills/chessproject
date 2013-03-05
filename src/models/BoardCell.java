package models;

public class BoardCell {
	
	Integer row;
	Integer column;
	String square;
	ChessPiece currentPiece;

	public BoardCell(Integer i, Integer j) {
		
		this.row = i;
		this.column = j;
	}

	public String getSquareName(){
		return ChessBoard.getLetterNumberOfBoard(row, column);
	}
}

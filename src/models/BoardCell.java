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
		return square = ChessBoard.getLetterNumberOfBoard(row, column);
	}
	
	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}

	public Integer getColumn() {
		return column;
	}

	public void setColumn(Integer column) {
		this.column = column;
	}

	public ChessPiece getCurrentPiece() {
		return currentPiece;
	}

	public void setCurrentPiece(ChessPiece currentPiece) {
		this.currentPiece = currentPiece;
	}
}

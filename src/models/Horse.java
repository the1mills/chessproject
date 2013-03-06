package models;

import java.util.Vector;

public class Horse extends ChessPiece {

	public Horse(String pieceType, double value,boolean color, int row, int column) {
		super(pieceType,value,color,row,column);
	}

	@Override
	public void setValueOfPiece() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vector<BoardCell> findPossibleSquaresToMoveTo() {
		Vector<BoardCell> potentialCells = new Vector<BoardCell>();

		BoardCell currentCell = this.getCurrentCell();
		int x = currentCell.row;
		int y = currentCell.column;

		if (isBlack) {

			// Loop over 8 directions
			for (int i = 0; i < 8; i++) {
				// Loop over 7 potential squares in each of 8 directions

				int xTemp;
				int yTemp;
				BoardCell temp = null;

				if (i == 0) {
					xTemp = x + 1;
					yTemp = y + 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 1) {
					xTemp = x + 2;
					yTemp = y + 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 2) {
					xTemp = x + 1;
					yTemp = y - 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 3) {
					xTemp = x + 2;
					yTemp = y - 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 4) {
					xTemp = x - 1;
					yTemp = y - 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 5) {
					xTemp = x - 1;
					yTemp = y + 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 6) {
					xTemp = x - 2;
					yTemp = y + 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 7) {
					xTemp = x - 2;
					yTemp = y - 1;
					temp = new BoardCell(xTemp, yTemp);
				} else {
					// a problem arises...
				}

				BoardCell temp1 = ChessBoard.getCellAt(temp.row, temp.column);

				if (temp1 != null) {
					ChessPiece p = temp1.currentPiece;

					if (p == null) {
						potentialCells.add(temp1);
					} else if (p.isBlack == false) {
						potentialCells.add(temp1);
						continue;
					} else if (p.isBlack == true) {
						//do nothing here
						continue;
					}
				} else {
					continue;
				}
			}

		} else {

			for (int i = 0; i < 8; i++) {
				int j = 1;

				int xTemp;
				int yTemp;
				BoardCell temp = null;

				if (i == 0) {
					xTemp = x + 1;
					yTemp = y + 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 1) {
					xTemp = x + 2;
					yTemp = y + 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 2) {
					xTemp = x + 1;
					yTemp = y - 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 3) {
					xTemp = x + 2;
					yTemp = y - 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 4) {
					xTemp = x - 1;
					yTemp = y - 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 5) {
					xTemp = x - 1;
					yTemp = y + 2;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 6) {
					xTemp = x - 2;
					yTemp = y + 1;
					temp = new BoardCell(xTemp, yTemp);
				} else if (i == 7) {
					xTemp = x - 2;
					yTemp = y - 1;
					temp = new BoardCell(xTemp, yTemp);
				} else {
					// a problem arises...
				}


				BoardCell temp1 = ChessBoard.getCellAt(temp.row, temp.column);

				if (temp1 != null) {
					ChessPiece p = temp1.currentPiece;

					if (p == null) {
						potentialCells.add(temp1);
					} else if (p.isBlack == true) {
						potentialCells.add(temp1);
						continue;
					} else if (p.isBlack == false) {
						//do nothing here
						continue;
					}
				} else {
					continue;
				}
			}
		}

		return potentialCells;
		
	}

}

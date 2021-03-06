package models;

import java.util.Vector;

public class Pawn extends ChessPiece {

	public Pawn(String pieceType, BoardCell c, double value, boolean color, int row, int column) {
		super(pieceType, c, value, color, row, column);
	}

	@Override
	public void setValueOfPiece() {
		// TODO Auto-generated method stub

	}

	@Override
	public Vector<BoardCell> findPossibleSquaresToMoveTo() {

		Vector<BoardCell> potentialCells = new Vector<BoardCell>();

		if (isBlack) {

			BoardCell upAndLeft = ChessBoard.getCellAt(
					this.getCurrentCell().row - 1,
					this.getCurrentCell().column + 1);

			if (upAndLeft != null) {
				ChessPiece p = upAndLeft.currentPiece;

				if (p != null && p.isBlack == false) {
					potentialCells.add(upAndLeft);
				}
			}

			BoardCell upAndRight = ChessBoard.getCellAt(
					this.getCurrentCell().row - 1,
					this.getCurrentCell().column - 1);

			if (upAndRight != null) {
				ChessPiece p = upAndRight.currentPiece;

				if (p != null && p.isBlack == false) {
					potentialCells.add(upAndRight);
				}
			}

			BoardCell oneSpaceUp = ChessBoard.getCellAt(
					this.getCurrentCell().row - 1,
					this.getCurrentCell().column);

			if (oneSpaceUp != null) {
				ChessPiece p = oneSpaceUp.currentPiece;

				if (p == null) {
					potentialCells.add(oneSpaceUp);
				}
			}
		}

		else {
			
			BoardCell upAndLeft = ChessBoard.getCellAt(
					this.getCurrentCell().row + 1,
					this.getCurrentCell().column - 1);

			if (upAndLeft != null) {
				ChessPiece p = upAndLeft.currentPiece;

				if (p != null && p.isBlack == true) {
					potentialCells.add(upAndLeft);
				}
			}

			BoardCell upAndRight = ChessBoard.getCellAt(
					this.getCurrentCell().row + 1,
					this.getCurrentCell().column + 1);

			if (upAndRight != null) {
				ChessPiece p = upAndRight.currentPiece;

				if (p != null && p.isBlack == true) {
					potentialCells.add(upAndRight);
				}
			}

			BoardCell oneSpaceUp = ChessBoard.getCellAt(
					this.getCurrentCell().row + 1,
					this.getCurrentCell().column);

			if (oneSpaceUp != null) {
				ChessPiece p = oneSpaceUp.currentPiece;

				if (p == null) {
					potentialCells.add(oneSpaceUp);
				}
			}

		}

		return potentialCells;

	}

	@Override
	public boolean canAndDoesProtectThisSquare(BoardCell bc) {
		if (isBlack) {

			BoardCell upAndLeft = ChessBoard.getCellAt(
					this.getCurrentCell().row - 1,
					this.getCurrentCell().column + 1);

			if (upAndLeft != null) {
				
				if(upAndLeft == bc){
					return true;
				}
			}

			BoardCell upAndRight = ChessBoard.getCellAt(
					this.getCurrentCell().row - 1,
					this.getCurrentCell().column - 1);

			if (upAndRight != null) {
				if(upAndRight == bc){
					return true;
				}
			}
					
		}

		else {
			
			BoardCell upAndLeft = ChessBoard.getCellAt(
					this.getCurrentCell().row + 1,
					this.getCurrentCell().column - 1);

			if (upAndLeft != null) {
				
				if(upAndLeft == bc){
					return true;
				}
			}

			BoardCell upAndRight = ChessBoard.getCellAt(
					this.getCurrentCell().row + 1,
					this.getCurrentCell().column + 1);

			if (upAndRight != null) {
				if(upAndRight == bc){
					return true;
				}
			}

		}
		return false;
	}

	@Override
	public BoardCell doTheSquareLoopThing(int i, int j) {
		// this method not needed for pawn
		return null;
	}

}

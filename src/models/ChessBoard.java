package models;

public class ChessBoard {

	public ChessPiece[][] board;

	public ChessBoard() {

		board = new ChessPiece[8][8];

	}
	
	public ChessPiece getPieceAt(int i, int j) {

		return board[i][j];
	}
	
	public void populateBoardStartGame(){
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				board[i][j] = addPiece(i,j);
				
			}
		}
		
	}

	private ChessPiece addPiece(int i, int j) {
		
		switch (i) {

		case 0:
			switch (j) {
			case 0:
				return new Rook(false);
			case 1:
				return new Knight(false);
			case 2:
				return new Bishop(false);
			case 3:
				return new Queen(false);
			case 4:
				return new King(false);
			case 5:
				return new Bishop(false);
			case 6:
				return new Knight(false);
			case 7:
				return new Rook(false);

			}
		case 1:
			switch (j) {
			case 0:
				return new Pawn(false);
			case 1:
				return new Pawn(false);
			case 2:
				return new Pawn(false);
			case 3:
				return new Pawn(false);
			case 4:
				return new Pawn(false);
			case 5:
				return new Pawn(false);
			case 6:
				return new Pawn(false);
			case 7:
				return new Pawn(false);

			}
		
		case 6:
			switch (j) {
			case 0:
				return new Pawn(true);
			case 1:
				return new Pawn(true);
			case 2:
				return new Pawn(true);
			case 3:
				return new Pawn(true);
			case 4:
				return new Pawn(true);
			case 5:
				return new Pawn(true);
			case 6:
				return new Pawn(true);
			case 7:
				return new Pawn(true);

			}

		case 7:
			switch (j) {
			case 0:
				return new Rook(true);
			case 1:
				return new Knight(true);
			case 2:
				return new Bishop(true);
			case 3:
				return new Queen(true);
			case 4:
				return new King(true);
			case 5:
				return new Bishop(true);
			case 6:
				return new Knight(true);
			case 7:
				return new Rook(true);
			}
		}
		return null;
		
	}


	public String getLetterNumberOfBoard(Integer i, Integer j) {

		String square = "";

		switch (i) {

		case 0:
			switch (j) {
			case 0:
				return square = "A1";
			case 1:
				return square = "A2";
			case 2:
				return square = "A3";
			case 3:
				return square = "A4";
			case 4:
				return square = "A5";
			case 5:
				return square = "A6";
			case 6:
				return square = "A7";
			case 7:
				return square = "A8";

			}
		case 1:
			switch (j) {
			case 0:
				return square = "B1";
			case 1:
				return square = "B2";
			case 2:
				return square = "B3";
			case 3:
				return square = "B4";
			case 4:
				return square = "B5";
			case 5:
				return square = "B6";
			case 6:
				return square = "B7";
			case 7:
				return square = "B8";

			}
		case 2:
			switch (j) {
			case 0:
				return square = "C1";
			case 1:
				return square = "C2";
			case 2:
				return square = "C3";
			case 3:
				return square = "C4";
			case 4:
				return square = "C5";
			case 5:
				return square = "C6";
			case 6:
				return square = "C7";
			case 7:
				return square = "C8";

			}

		case 3:
			switch (j) {
			case 0:
				return square = "D1";
			case 1:
				return square = "D2";
			case 2:
				return square = "D3";
			case 3:
				return square = "D4";
			case 4:
				return square = "D5";
			case 5:
				return square = "D6";
			case 6:
				return square = "D7";
			case 7:
				return square = "D8";

			}

		case 4:
			switch (j) {
			case 0:
				return square = "E1";
			case 1:
				return square = "E2";
			case 2:
				return square = "E3";
			case 3:
				return square = "E4";
			case 4:
				return square = "E5";
			case 5:
				return square = "E6";
			case 6:
				return square = "E7";
			case 7:
				return square = "E8";

			}

		case 5:
			switch (j) {
			case 0:
				return square = "F1";
			case 1:
				return square = "F2";
			case 2:
				return square = "F3";
			case 3:
				return square = "F4";
			case 4:
				return square = "F5";
			case 5:
				return square = "F6";
			case 6:
				return square = "F7";
			case 7:
				return square = "F8";

			}
		case 6:
			switch (j) {
			case 0:
				return square = "G1";
			case 1:
				return square = "G2";
			case 2:
				return square = "G3";
			case 3:
				return square = "G4";
			case 4:
				return square = "G5";
			case 5:
				return square = "G6";
			case 6:
				return square = "G7";
			case 7:
				return square = "G8";

			}

		case 7:
			switch (j) {
			case 0:
				return square = "H1";
			case 1:
				return square = "H2";
			case 2:
				return square = "H3";
			case 3:
				return square = "H4";
			case 4:
				return square = "H5";
			case 5:
				return square = "H6";
			case 6:
				return square = "H7";
			case 7:
				return square = "H8";

			}
		}
		return "error1";

	}
	
	public static void main(String[] args){
		
		ChessBoard c = new ChessBoard();
		
		String x = c.getLetterNumberOfBoard(4, 4);
		System.out.println(x);
		
		c.populateBoardStartGame();
		
		ChessPiece p = c.getPieceAt(4, 4);
		
		System.out.println(p.getClass().getName());
	}

}

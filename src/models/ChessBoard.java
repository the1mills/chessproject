package models;

import controllers.ChessPlayer;

public class ChessBoard {

	private static BoardCell[][] boardCells;
	private ChessPlayer blackPlayer;
	private ChessPlayer whitePlayer;

	public ChessBoard(ChessPlayer whitePlayer, ChessPlayer blackPlayer) {

		this.blackPlayer = blackPlayer;
		this.whitePlayer = whitePlayer;
		boardCells = new BoardCell[8][8];

		populateBoardStartGame();
		
//		for(ChessPiece p: whitePlayer.getChessPieces()){
//			System.out.println("Piece is black: " + p.isBlack + " Piece Square: " + p.getCurrentCell().getSquareName() +
//					"  Piece type: " + p.getPieceType());
//		}
//		
//		for(ChessPiece p: blackPlayer.getChessPieces()){
//			System.out.println("Piece is black: " + p.isBlack + " Piece Square: " +p.getCurrentCell().getSquareName() +
//					"  Piece type: " + p.getPieceType());
//		}
	}
	
	public static ChessPiece getPieceAt(BoardCell c){
		
		BoardCell bc = boardCells[c.row][c.column];
		
		return bc.currentPiece;
	}
	
	public static BoardCell getCellAt(int i, int j) {

		if(i < 0 || i > 7 || j < 0 || j > 7){
			return null;
		}
		
		return boardCells[i][j];
	}
	
	public static BoardCell[][] getBoardCell() {
		return boardCells;
	}

	public static void setBoard(BoardCell[][] board) {
		ChessBoard.boardCells = board;
	}

	public ChessPlayer getBlackPlayer() {
		return blackPlayer;
	}

	public void setBlackPlayer(ChessPlayer blackPlayer) {
		this.blackPlayer = blackPlayer;
	}

	public ChessPlayer getWhitePlayer() {
		return whitePlayer;
	}

	public void setWhitePlayer(ChessPlayer whitePlayer) {
		this.whitePlayer = whitePlayer;
	}
	
	public void populateBoardStartGame(){
		
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				
				
				boardCells[i][j] = new BoardCell(i,j);
				ChessPiece p = addPiece(i,j);
				
//				if(p !=null && p.getPieceType().equals("Pawn")){
//					continue;
//				}
				
				
				boardCells[i][j].currentPiece = p;
				
				if(i<2){
					
					whitePlayer.getChessPieces().add(p);
				}
				else if(i>5){
					
					blackPlayer.getChessPieces().add(p);
				}
				
				
				
			}
		}
		
	}

	private ChessPiece addPiece(int i, int j) {
		
		switch (i) {

		case 0:
			switch (j) {
			case 0:
				return new Rook("Rook",5,false,i,j);
			case 1:
				return new Horse("Horse",4,false,i,j);
			case 2:
				return new Bishop("Bishop",3,false,i,j);
			case 3:
				return new Queen("Queen",7,false,i,j);
			case 4:
				return new King("King",9999,false,i,j);
			case 5:
				return new Bishop("Bishop",3,false,i,j);
			case 6:
				return new Horse("Horse",4,false,i,j);
			case 7:
				return new Rook("Rook",5,false,i,j);

			}
		case 1:
			switch (j) {
			case 0:
				return new Pawn("Pawn",2,false,i,j);
			case 1:
				return new Pawn("Pawn",2,false,i,j);
			case 2:
				return new Pawn("Pawn",2,false,i,j);
			case 3:
				return new Pawn("Pawn",2,false,i,j);
			case 4:
				return new Pawn("Pawn",2,false,i,j);
			case 5:
				return new Pawn("Pawn",2,false,i,j);
			case 6:
				return new Pawn("Pawn",2,false,i,j);
			case 7:
				return new Pawn("Pawn",2,false,i,j);

			}
		
		case 6:
			switch (j) {
			case 0:
				return new Pawn("Pawn",2,true,i,j);
			case 1:
				return new Pawn("Pawn",2,true,i,j);
			case 2:
				return new Pawn("Pawn",2,true,i,j);
			case 3:
				return new Pawn("Pawn",2,true,i,j);
			case 4:
				return new Pawn("Pawn",2,true,i,j);
			case 5:
				return new Pawn("Pawn",2,true,i,j);
			case 6:
				return new Pawn("Pawn",2,true,i,j);
			case 7:
				return new Pawn("Pawn",2,true,i,j);

			}

		case 7:
			switch (j) {
			case 0:
				return new Rook("Rook",5,true,i,j);
			case 1:
				return new Horse("Horse",4,true,i,j);
			case 2:
				return new Bishop("Bishop",3,true,i,j);
			case 3:
				return new Queen("Queen",7,true,i,j);
			case 4:
				return new King("King",9999,true,i,j);
			case 5:
				return new Bishop("Bishop",3,true,i,j);
			case 6:
				return new Horse("Horse",4,true,i,j);
			case 7:
				return new Rook("Rook",5,true,i,j);
			}
		}
		return null;
		
	}


	public static String getLetterNumberOfBoard(Integer i, Integer j) {

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
	

}

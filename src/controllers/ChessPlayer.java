package controllers;

import java.util.Vector;

import models.Bishop;
import models.BoardCell;
import models.ChessBoard;
import models.ChessPiece;
import models.Horse;
import models.King;
import models.Move;
import models.Pawn;
import models.Queen;
import models.Rook;

public class ChessPlayer extends Thread {

	private boolean isBlack;
	private Vector<ChessPiece> chessPieces;
	private Vector<Move> historicalMoves;

	public ChessPlayer(boolean color) {

		isBlack = color;
		chessPieces = new Vector<ChessPiece>();
		historicalMoves = new Vector<Move>();
	}

	public void move() {

		Vector<Move> possibleMoves = new Vector<Move>();

		for (ChessPiece p : this.getChessPieces()) {
			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {
				
				if(c.getCurrentPiece() !=null){
					System.out.println("Not null!!!! : " + c.getCurrentPiece().getPieceType());
				}

				Move m = new Move(isBlack, p, c.getCurrentPiece(),
						p.getCurrentCell(), c);
				possibleMoves.add(m);

//				System.out.println("Piece: " + p.getPieceType()
//						+ ", current square: "
//						+ p.getCurrentCell().getSquareName()
//						+ ", Destination Square: " + c.getSquareName());

			}
		}


		for (Move m : possibleMoves) {

			double calcTemp = 0;

			if (m.getCaptured() instanceof Pawn) {

				calcTemp += 2.0;
				
			} else if (m.getCaptured() instanceof Bishop) {
				
				calcTemp += 3.0;

			} else if (m.getCaptured() instanceof Rook) {
				
				calcTemp += 5.0;

			} else if (m.getCaptured() instanceof Horse) {

				calcTemp += 4.0;
				
			} else if (m.getCaptured() instanceof Queen) {
				
				calcTemp += 7.0;

			} else if (m.getCaptured() instanceof King) {
				
				calcTemp += 9999.0;
				Referee.setGameOver(true);

			}
			
			m.setPerceivedValue(calcTemp);

		}
		
		double calc = 0;
		Vector<Move> finalChoices = new Vector<Move>();
		Move finalMove = null;
		
		for (Move m : possibleMoves) {
			
			if(m.getPerceivedValue() >= calc){
				finalChoices.add(m);
				calc = m.getPerceivedValue();
			}
		}
		
		if(finalChoices.isEmpty()){
			//we have a stalemate
		 System.out.println("We have a stalemate!");
		}
		
		double rand = -111;
		int x = -1;
		
		if(finalChoices.size()>1){
			
			 rand = (Math.random()*(finalChoices.size()-1));
			 x = (int) Math.round(rand);
			finalMove = finalChoices.get(x);
			
		}
		else if(finalChoices.size() == 1){
			finalMove = finalChoices.get(0);
		}
		
		if(finalMove == null){
			System.out.println("Final move is null");
		}
		
		executeMove(finalMove);
		
		
		//System.out.println("Captured piece: " + Object.getCaptured());
	}

	private void executeMove(Move finalMove) {
		
		System.out.println("Piece: " + finalMove.getMoved().getPieceType()
				+ ", current square: "
				+ finalMove.getMoved().getCurrentCell().getSquareName()
				+ ", Destination Square: " + finalMove.getToCell().getSquareName() +
				" Captured piece: " + finalMove.getCaptured());
		
	
	if(finalMove.getCaptured() != null){
	BoardCell bc = finalMove.getCaptured().getCurrentCell();
	ChessBoard.getCellAt(bc.getRow(), bc.getColumn()).setCurrentPiece(null);
	}
	
	ChessPlayer cp = Referee.getOppposingPlayer(this);
	cp.getChessPieces().remove(finalMove.getCaptured());
	BoardCell bc1 = finalMove.getToCell();
	BoardCell bc2 = finalMove.getMoved().getCurrentCell();
	ChessBoard.getCellAt(bc2.getRow(), bc2.getColumn()).setCurrentPiece(null);
	ChessBoard.getCellAt(bc1.getRow(), bc1.getColumn()).setCurrentPiece(finalMove.getMoved());
	}

	public boolean isBlack() {
		return isBlack;
	}

	public void setBlack(boolean isBlack) {
		this.isBlack = isBlack;
	}

	public Vector<ChessPiece> getChessPieces() {
		return chessPieces;
	}

	public void setChessPieces(Vector<ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
	}

}

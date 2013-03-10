package controllers;

import java.util.Observable;
import java.util.Vector;

import views.ChessViewFrame;

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

public class ChessPlayer extends Observable {

	private boolean isBlack;
	private Vector<ChessPiece> chessPieces;
	private Vector<Move> historicalMoves;

	public ChessPlayer(boolean color, ChessViewFrame cvf) {

		isBlack = color;
		chessPieces = new Vector<ChessPiece>();
		historicalMoves = new Vector<Move>();
		
		this.addObserver(cvf);

	}

	public void move() {

		Vector<Move> possibleMoves = new Vector<Move>();

		for (ChessPiece p : this.getChessPieces()) {
			
			if(p instanceof Queen || p instanceof King){
				continue;
			}
			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {
				
				if(c.getCurrentPiece() !=null){
					System.out.println("Potential captured is Not null!!!! : " + c.getCurrentPiece().getPieceType());
				}

				Move m = new Move(isBlack, p, c.getCurrentPiece(),
						p.getCurrentCell(), c);
				possibleMoves.add(m);

			}
		}


		for (Move m : possibleMoves) {

			double calcTemp = 0;

			if (m.getCapturedPiece() instanceof Pawn) {

				calcTemp += 2.0;
				
			} else if (m.getCapturedPiece() instanceof Bishop) {
				
				calcTemp += 2.0;
//				calcTemp += 3.0;

			} else if (m.getCapturedPiece() instanceof Rook) {
				
				calcTemp += 2.0;
//				calcTemp += 5.0;

			} else if (m.getCapturedPiece() instanceof Horse) {

				calcTemp += 2.0;
//				calcTemp += 4.0;
				
			} else if (m.getCapturedPiece() instanceof Queen) {
				
				calcTemp += 2.0;
//				calcTemp += 7.0;

			} else if (m.getCapturedPiece() instanceof King) {
				
				calcTemp += 2.0;
//				calcTemp += 9999.0;
				Referee.setGameOver(true);

			}
			
			m.setPerceivedValue(calcTemp);

		}
		
		double calc = -111;
		Vector<Move> finalChoices = new Vector<Move>();
		Move finalMoveChoice = null;
		
		for (Move m : possibleMoves) {
			
			if(m.getPerceivedValue() >= calc){
				finalChoices.add(m);
			//	calc = m.getPerceivedValue();
			}
		}
		
		if(finalChoices.isEmpty()){
			//we have a stalemate
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 System.out.println("We have a stalemate!");
		 return;
		}
		
		double rand = -111;
		int x = -1;
		
		if(finalChoices.size()>1){
			
			 rand = (Math.random()*(finalChoices.size()-1));
			 x = (int) Math.round(rand);
			finalMoveChoice = finalChoices.get(x);
			
		}
		else if(finalChoices.size() == 1){
			finalMoveChoice = finalChoices.get(0);
		}
		
		if(finalMoveChoice == null){
			System.out.println("Final move is null");
		}
		
		executeMove(finalMoveChoice);
		
		
		//System.out.println("Captured piece: " + Object.getCaptured());
	}

	private void executeMove(Move finalMove) {
		
		System.out.println("Piece: " + finalMove.getMovedPiece().getPieceType()
				+ ", current square: "
				+ finalMove.getMovedPiece().getCurrentCell().getSquareName()
				+ ", Destination Square: " + finalMove.getToCell().getSquareName() +
				" Captured piece: " + finalMove.getCapturedPiece());
		
	
	if(finalMove.getCapturedPiece() != null){
	BoardCell bc = finalMove.getCapturedPiece().getCurrentCell();
	//ChessBoard.getCellAt(bc.getRow(), bc.getColumn()).setCurrentPiece(null);
	ChessBoard.getCellAt1(bc).setCurrentPiece(null);
	ChessPlayer cp = Referee.getOppposingPlayer(this);
	cp.getChessPieces().remove(finalMove.getCapturedPiece());
	}
	
	BoardCell bc1 = finalMove.getToCell();
	BoardCell bc2 = finalMove.getMovedPiece().getCurrentCell();
//	ChessBoard.getCellAt(bc2.getRow(), bc2.getColumn()).setCurrentPiece(null);
//	ChessBoard.getCellAt(bc1.getRow(), bc1.getColumn()).setCurrentPiece(finalMove.getMovedPiece());
	ChessBoard.getCellAt1(bc2).setCurrentPiece(null);
	ChessBoard.getCellAt1(bc1).setCurrentPiece(finalMove.getMovedPiece());
	
	finalMove.getMovedPiece().setCurrentCell(bc1);
	
	this.setChanged();
	this.notifyObservers(finalMove);
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

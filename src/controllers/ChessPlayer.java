package controllers;

import java.awt.Color;
import java.util.Observable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

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
	private boolean inCheck = false;
	private ChessPlayer opposingCP = null;

	public ChessPlayer(boolean color, ChessViewFrame cvf) {

		isBlack = color;
		chessPieces = new Vector<ChessPiece>();
		historicalMoves = new Vector<Move>();
		this.addObserver(cvf);
	}
	

	public void setUpOpponent() {
		opposingCP = Referee.getOppposingPlayer(this);
	}


	public void move() {
		
		Vector<Move> possibleMoves = new Vector<Move>();

		for (ChessPiece p : this.getChessPieces()) {

			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {

				Move m = new Move(isBlack, p, c.getCurrentPiece(),
						p.getCurrentCell(), c);
				possibleMoves.add(m);

			}
		}
		
		
		if(amIinCheck()){
			
			King myKing = getMyKing();
			pauseAndfindAttackingPiecesAndHighlightThem(myKing);
		}
		
		

		for (Move m : possibleMoves) {
			
			getPieces(m.getMovedPiece());
			
			double calcTemp = 0;
//			double movingPieceValue = m.getMovedPiece().getValueOfPiece();
			double movingPieceValue = 3.999;
			
			

			if (m.getMovedPiece() instanceof King) {

				if (opposingCP.canThisSquareBeAttackedBoolean(m.getToCell())) {

					m.setLegal(false);
					m.setPerceivedValue(-999999999999999999999.99);
				
					continue;
				}
			}
			
			//if this to move piece is currently under attack, add the value of the piece
			//...
			
			// subtract value to move if the move puts the piece in jeopardy

			
			double multiplierAttacked = opposingCP.canThisSquareBeAttacked(m
					.getToCell());

			calcTemp -= (multiplierAttacked * movingPieceValue);
			
			if(m.getMovedPiece() instanceof King){
				calcTemp -= (multiplierAttacked * movingPieceValue)*5;
			}
			
			

			// add value if boardcell to move to will be protected

			double multiplierProtected = canThisSquareBeProtected(
					m.getMovedPiece(), m.getToCell());

			calcTemp += multiplierProtected * movingPieceValue;

			// add value to move if the move captures an opponents piece

			if (m.getCapturedPiece() instanceof Pawn) {

				calcTemp += 2.0;

			} else if (m.getCapturedPiece() instanceof Bishop) {

				// calcTemp += 2.0;
				calcTemp += 3.0;

			} else if (m.getCapturedPiece() instanceof Rook) {

				// calcTemp += 2.0;
				calcTemp += 5.0;

			} else if (m.getCapturedPiece() instanceof Horse) {

				// calcTemp += 2.0;
				calcTemp += 4.0;

			} else if (m.getCapturedPiece() instanceof Queen) {

				// calcTemp += 2.0;
				calcTemp += 7.0;

			} else if (m.getCapturedPiece() instanceof King) {

				calcTemp += 99999999.0;
				// calcTemp += 9999.0;
			}

			m.setPerceivedValue(calcTemp);

		}

		double highestCalcValue = -999999999999.999;
		Vector<Move> finalChoices = new Vector<Move>();
		Move finalMoveChoice = null;

		int i = 0;
		String capturedPieceType = "";
		for (Move m : possibleMoves) {
			i++;
			
			if(m.getCapturedPiece() != null){
				capturedPieceType = m.getCapturedPiece().getPieceType();
			}
			System.out.println("Potential move # " + i + " ---> Piece moved: " +
					m.getMovedPiece().getPieceType()
					+ ", current square: "
					+ m.getMovedPiece().getCurrentCell().getSquareName()
					+ ", destination Square: "
					+ m.getToCell().getSquareName() + " captured piece: "
					+ capturedPieceType
					+ ", perceived value: " + m.getPerceivedValue());
			

			if (m.getPerceivedValue() >= highestCalcValue) {
				highestCalcValue = m.getPerceivedValue();
			}
		}

		int j = 0;
		for (Move m : possibleMoves) {
			if (m.getPerceivedValue() >= highestCalcValue && m.isLegal()) {
				
				j++;
				finalChoices.add(m);
				if(m.getCapturedPiece() != null){
					capturedPieceType = m.getCapturedPiece().getPieceType();
				}
				System.out.println("Final move(s) choice # " + j + " ---> Piece moved: " +
						m.getMovedPiece().getPieceType()
						+ ", current square: "
						+ m.getMovedPiece().getCurrentCell().getSquareName()
						+ ", destination Square: "
						+ m.getToCell().getSquareName() + " captured piece: "
						+ capturedPieceType 
						+ ", perceived value: " + m.getPerceivedValue());
			}
		}

		double rand = -1;
		int index = -1;

		if (finalChoices.size() > 1) {

			rand = (Math.random() * (finalChoices.size() - 1));
			index = (int) Math.round(rand);
			finalMoveChoice = finalChoices.get(index);

		} else if (finalChoices.size() == 1) {
			finalMoveChoice = finalChoices.get(0);
		} else {
			System.out.println("We have a stalemate!");
			return;
		}

		if (finalMoveChoice == null) {
			System.out.println("Final move is null");
		}

		executeMove(finalMoveChoice);

	}

	private King getMyKing() {
		King myKing = null;
		
		for(ChessPiece p: this.getChessPieces()){
			
			if(p instanceof King){
				myKing = (King) p;
				break;
			}
		}
		return myKing;
	}


	private void pauseAndfindAttackingPiecesAndHighlightThem(ChessPiece p) {
		
		Vector<ChessPiece> cpv = new Vector<ChessPiece>();
		
		Referee.paused = true;
		
		BoardCell bc = p.getCurrentCell();
		
		for(ChessPiece piece: opposingCP.getChessPieces()){
			
			for(BoardCell boardCell : piece.findPossibleSquaresToMoveTo()){
				
					if(boardCell == bc){
						cpv.add(piece);
						break;
					}
			}
		}
		
		for(ChessPiece pp: cpv){
			
			pp.getImageLabel().setBorder(BorderFactory.createEtchedBorder(Color.green, Color.green));
		
			this.setChanged();
			this.notifyObservers(pp);
		}
		
		Referee.paused = true;
		
		while(Referee.paused){
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	private Vector<ChessPiece> getPieces(ChessPiece chessPiece){
		
		Vector<ChessPiece> vcp = new Vector<ChessPiece>();
		Class<?> c = chessPiece.getClass();
		
		for(ChessPiece p: this.getChessPieces()){
			
			  try{
				 chessPiece.getClass().cast(p);
				  vcp.add(p);
			  }
			  catch(Exception e){
				  System.out.println("");
			  }	
		}
		
		return vcp;
		
	}

	private boolean amIinCheck() {
		
		King myKing = getMyKing();
		
		if(opposingCP.canThisSquareBeAttackedBoolean(myKing.getCurrentCell())){
			System.out.println(this.isBlack + " --- >" + "King can be killed");
			return true;	
		}
		else{
			return false;
		}
	}

	private void executeMove(Move finalMove) {
		
		String capturedPieceType = "";
		if(finalMove.getCapturedPiece() != null){
			capturedPieceType = finalMove.getCapturedPiece().getPieceType();
		}

		System.out.println("FINAL MOVE info: " + "--- Piece moved: " + finalMove.getMovedPiece().getPieceType()
				+ ", current square: "
				+ finalMove.getMovedPiece().getCurrentCell().getSquareName()
				+ ", Destination Square: "
				+ finalMove.getToCell().getSquareName() + " Captured piece: "
				+ capturedPieceType);

		if (finalMove.getCapturedPiece() != null) {

			if (finalMove.getCapturedPiece() instanceof King) {
				String kingColor = "";
				if (finalMove.getCapturedPiece().isBlack()) {
					kingColor = "Black";
				} else {
					kingColor = "White";
				}
				Referee.setGameOver(true);
				JOptionPane.showMessageDialog(null, kingColor
						+ " king is captured");
			}

			BoardCell bc = finalMove.getCapturedPiece().getCurrentCell();
			// ChessBoard.getCellAt(bc.getRow(),
			// bc.getColumn()).setCurrentPiece(null);
			ChessBoard.getCellAt1(bc).setCurrentPiece(null);
			ChessPlayer cp = Referee.getOppposingPlayer(this);
			cp.getChessPieces().remove(finalMove.getCapturedPiece());
		}

		BoardCell bc1 = finalMove.getToCell();
		BoardCell bc2 = finalMove.getMovedPiece().getCurrentCell();
		// ChessBoard.getCellAt(bc2.getRow(),
		// bc2.getColumn()).setCurrentPiece(null);
		// ChessBoard.getCellAt(bc1.getRow(),
		// bc1.getColumn()).setCurrentPiece(finalMove.getMovedPiece());
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

	public double canThisSquareBeProtected(ChessPiece pc, BoardCell bc) {

		double multiplier = 0;
		double count = 0;
		
		for (ChessPiece p : this.getChessPieces()) {

			if (p.equals(pc)) {
				continue;
			}
			
			if(p.canAndDoesProtectThisSquare(bc)){
				
				count++;
			}

//			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {
//
//				if (c.equals(bc)) {
//					count++;
//				}
//			}
		}
		
		return multiplier = count / (count + 1);
	}

	public boolean canThisSquareBeAttackedBoolean(BoardCell bc) {

		for (ChessPiece p : this.getChessPieces()) {

			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {

				if (c.equals(bc)) {
					return true;
				}

			}

		}

		return false;
	}

	public double canThisSquareBeAttacked(BoardCell bc) {

		double multiplier = 0;
		double count = 0;

		for (ChessPiece p : this.getChessPieces()) {

			for (BoardCell c : p.findPossibleSquaresToMoveTo()) {

				if (c.equals(bc)) {
					count++;
				}

			}

		}

		return multiplier = count / (count + 1);
	}

}

package models;

public class Move {

	boolean actorColor;
	ChessPiece moved;
	ChessPiece captured;
	BoardCell fromCell;
	BoardCell toCell;
	double totalPerceivedValue;
	double captureBenefits;
	double captureCosts;
	double toSquareProtection;
	boolean isLegal = true;

	public Move(boolean actorColor, ChessPiece moved, ChessPiece captured,
			BoardCell fromCell, BoardCell toCell) {
		super();
		this.actorColor = actorColor;
		this.moved = moved;
		this.captured = captured;
		this.fromCell = fromCell;
		this.toCell = toCell;
	}

	public boolean isActorColor() {
		return actorColor;
	}

	public void setActorColor(boolean actorColor) {
		this.actorColor = actorColor;
	}

	public ChessPiece getMovedPiece() {
		return moved;
	}

	public void setMoved(ChessPiece moved) {
		this.moved = moved;
	}

	public ChessPiece getCapturedPiece() {
		return captured;
	}

	public void setCaptured(ChessPiece captured) {
		this.captured = captured;
	}

	public BoardCell getFromCell() {
		return fromCell;
	}

	public void setFromCell(BoardCell fromCell) {
		this.fromCell = fromCell;
	}

	public BoardCell getToCell() {
		return toCell;
	}

	public void setToCell(BoardCell toCell) {
		this.toCell = toCell;
	}

	public double getPerceivedValue() {
		return totalPerceivedValue;
	}

	public void setPerceivedValue(double perceivedValue) {
		this.totalPerceivedValue = perceivedValue;
	}

	public double getTotalPerceivedValue() {
		return totalPerceivedValue;
	}

	public void setTotalPerceivedValue(double totalPerceivedValue) {
		this.totalPerceivedValue = totalPerceivedValue;
	}

	public double getCaptureBenefits() {
		return captureBenefits;
	}

	public void setCaptureBenefits(double captureBenefits) {
		this.captureBenefits = captureBenefits;
	}

	public double getCaptureCosts() {
		return captureCosts;
	}

	public void setCaptureCosts(double captureCosts) {
		this.captureCosts = captureCosts;
	}

	public double getToSquareProtection() {
		return toSquareProtection;
	}

	public void setToSquareProtection(double toSquareProtection) {
		this.toSquareProtection = toSquareProtection;
	}

	public boolean isLegal() {
		return isLegal;
	}

	public void setLegal(boolean isLegal) {
		this.isLegal = isLegal;
	}

	public ChessPiece getMoved() {
		return moved;
	}

	public ChessPiece getCaptured() {
		return captured;
	}
}

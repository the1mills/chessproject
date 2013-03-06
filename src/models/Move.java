package models;

public class Move {

	boolean actorColor;
	ChessPiece moved;
	ChessPiece captured;
	BoardCell fromCell;
	BoardCell toCell;
	double perceivedValue;

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

	public ChessPiece getMoved() {
		return moved;
	}

	public void setMoved(ChessPiece moved) {
		this.moved = moved;
	}

	public ChessPiece getCaptured() {
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
		return perceivedValue;
	}

	public void setPerceivedValue(double perceivedValue) {
		this.perceivedValue = perceivedValue;
	}

}

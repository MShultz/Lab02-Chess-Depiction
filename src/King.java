
public class King extends Piece {
	private boolean isCheck;

	public King(PieceType type, boolean isWhite, Position p) {
		super(type, isWhite, p);
		isCheck = false;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public Position getQueenSide() {
		Position location;
		if (this.isWhite()) {
			location = new Position(1, 3);
		} else {
			location = new Position(8, 3);
		}
		return location;
	}

	public Position getKingSide() {
		Position location;
		if (this.isWhite()) {
			location = new Position(1, 7);
		} else {
			location = new Position(8, 7);
		}
		return location;
	}

}

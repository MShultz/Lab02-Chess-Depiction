
public abstract class Piece {
	private boolean hasMoved;
	private PieceType type;
	private boolean isWhite;
	private int xLocation;
	private int yLocation;
	
	public Piece(PieceType type, boolean isWhite, int xLocation, int yLocation){
		this.type = type;
		this.isWhite = isWhite;
		this.xLocation = xLocation;
		this.yLocation = yLocation;
		hasMoved = false;
	}
	public boolean hasMoved() {
		return hasMoved;
	}
	public PieceType getType() {
		return type;
	}
	public boolean isWhite() {
		return isWhite;
	}
	public int getXLocation() {
		return xLocation;
	}
	public int getYLocation() {
		return yLocation;
	}
	@Override
	public String toString() {
		return "" + (isWhite? type.getWhiteType(): type.getBlackType());
	}
	
}

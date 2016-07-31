
public class King extends Piece {
	private boolean isCheck;
	public King(PieceType type, boolean isWhite, int xLocation, int yLocation) {
		super(type, isWhite, xLocation, yLocation);
		isCheck = false;
	}
	
	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
	public boolean isCheck() {
		return isCheck;
	}
	

}

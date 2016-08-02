
public class Rook extends Piece{

	public Rook(PieceType type, boolean isWhite, Position p) {
		super(type, isWhite, p);
	}
	public Position getQueenSide(){
		Position location;
		if(isWhite()){
			location = new Position(1, 4);
		}else{
			location = new Position(8, 4);
		}
		return location;
	}
	
	public Position getKingSide(){
		Position location;
		if(isWhite()){
			location = new Position(1, 6);
		}else{
			location = new Position(8, 6);
		}
		return location;
	}
}

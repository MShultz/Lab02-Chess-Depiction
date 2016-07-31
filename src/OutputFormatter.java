
public class OutputFormatter {
	public String formatPlacement(String placement) {
		String formatted =  getColor(placement.charAt(1)) + " "
				+ getPiece(placement.charAt(0)) + " was placed at " + placement.substring(2, 4) + ".";
		return formatted;
	}

	public String formatMovement(String movement, boolean isWhite) {
		String piece = getPiece(movement.charAt(0));
		String formatted = "Movement: [" + movement + "] " +(isWhite ? "White" : "Black") + " moves " + piece + " at ";
		if (piece.equals("Pawn")) {
			formatted += getMovement(movement, true) + ".";
			formatted += getCapturingPiece(movement, true);
		} else {
			formatted += getMovement(movement, false) + ".";
			formatted += getCapturingPiece(movement, false);
		}
		formatted +=  getEnding(movement.charAt(movement.length() - 1));
		return formatted;
	}
	public String formatCastle(String castle, boolean isWhite){
		return ("Movement: [" + castle + "] " + getColor(isWhite?'l':'d') + " castles " + (castle.trim().equals("O-O-O")? "Queen":"King") + " side.");
	}

	private String getPiece(char piece) {
		String pieceString;
		switch (piece) {
		case 'K':
			pieceString = "King";
			break;
		case 'Q':
			pieceString = "Queen";
			break;
		case 'R':
			pieceString = "Rook";
			break;
		case 'B':
			pieceString = "Bishop";
			break;
		case 'N':
			pieceString = "Knight";
			break;
		case 'P':
		default:
			pieceString = "Pawn";
			break;
		}
		return pieceString;
	}

	private String getColor(char color) {
		return (color == 'l' ? "White" : "Black");
	}

	private String getEnding(char end) {
		String ending = "";
		if (end == '+' || end == '#') {
			ending = (end == '+' ? " Check!" : " Checkmate!");
		}
		return ending;
	}

	private String getCapturingPiece(String movement, boolean isPawn) {
		int captureAt = (isPawn ? 2 : 3);
		String capturedString = "";
		if (movement.charAt(captureAt) == 'x') {
			capturedString = " They capture a piece. ";
		}
		return capturedString;
	}

	private String getMovement(String movement, boolean isPawn) {
		int startingIndex = (isPawn ? 0 : 1);
		int endingIndex = (isPawn ? 3 : 4);
		return (movement.substring(startingIndex, startingIndex + 2) + " to "
				+ movement.substring(endingIndex, endingIndex + 2));
	}
	public String getIncorrect(String currentLine){
		String incorrect = "Warning: Skipping line [" + currentLine + "] Invalid ";
		if(currentLine.contains("O")){
			incorrect += " castling";
		}else if(currentLine.contains("x") || currentLine.contains("-")){
			incorrect += " movement";
		}else{
			incorrect += " placement";
		}
		incorrect += " directive.";
		return incorrect;
	}
}


public class Board {
	private final static int BOARD_SIZE = 8;
	private Piece[][] board;
	LogWriter writer;
	DirectiveHandler handler;

	public Board(LogWriter writer) {
		this.writer = writer;
		board = new Piece[BOARD_SIZE][BOARD_SIZE];
		handler = new DirectiveHandler();
	}

	public void addNewPiece(String placement){
		int rank = handler.getInitialRank(placement, false);
		int file = handler.getInitialFile(placement, false);
		char piece = handler.getPieceChar(placement);
		boolean isWhite = handler.isWhite(placement);
		board[rank-1][file-1] = handler.getPiece(piece, rank, file, isWhite);
	}
	public void writeBoard() {
		for (int i = BOARD_SIZE - 1; i >= 0; --i) {
			String boardString = "";
			for (int j = 0; j < BOARD_SIZE; ++j) {
				if (j == 0) {
					boardString += (i + 1) + "|" + getPieceStringForBoard(i, j);
				} else {
					boardString += "|" + getPieceStringForBoard(i, j);
				}
				if (j == 7) {
					boardString += "|";
					writer.writeToFile(boardString);
				}
			}
		}
		writer.writeToFile("  A B C D E F G H ");
	}

	private String getPieceStringForBoard(int y, int x) {
		Piece p = board[y][x];
		return (p == null ? " " : (p.isWhite() ? "" + p.getType().getWhiteType() : "" + p.getType().getBlackType()));
	}
	private boolean isCapture(String directive){
		return directive.contains("x");
	}
	private boolean containsPiece(int y, int x){
		return board[y][x] != null;
	}
	
}


public class Position {
	int rank;
	int file;
	
	public Position(int rank, int file){
		this.rank = rank-1;
		this.file = file-1;
	}
	public int getRank() {
		return rank;
	}

	public int getFile() {
		return file;
	}

}

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Translator {
	LogWriter writer;
	OutputFormatter format;
	DirectiveFinder finder;
	boolean movementBegun = false;
	BufferedReader file = null;
	Board board;
	

	public Translator(String fileName) {
		writer = new LogWriter();
		initializeReader(fileName);
		writer.writeToFile("Process: Sucessfully opened file [" + fileName + "]");
		writer.writeToFile("Process: Files Initialized.");
		format = new OutputFormatter();
		finder = new DirectiveFinder();
		board = new Board(writer);
	}

	public void translate() {
		try {
			while (file.ready()) {
				String currentLine = getCurrentLine().trim();
				if (finder.containsComment(currentLine)) {
					currentLine = finder.removeComment(currentLine).trim();
				}
				if (currentLine.trim().length() > 0) {
					if (finder.isPlacement(currentLine)) {
						processPlacement(currentLine);
					} else if (finder.isMovement(currentLine)) {
						processMovement(currentLine);
					} else if (finder.containsCastle(currentLine)) {
						processCastling(currentLine);
						
					} else {
						writer.writeToFile(format.getIncorrect(currentLine));
					}
				}
			}
			board.writeBoard();
			shutdown();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void initializeReader(String fileName) {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(fileName);
			file = new BufferedReader(new InputStreamReader(inputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	private String getCurrentLine() {
		String currentLine = null;
		try {
			currentLine = file.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return currentLine;
	}

	

	private void processPlacement(String currentLine) {
		if (movementBegun) {
			writer.writeToFile("Warning: Skipping [" + currentLine + "]. Movement has begun.");
		} else {
			String placement = finder.getPlacementDirective(currentLine);
			board.addNewPiece(placement);
			String placement1 = "Placement: Adding [" + placement + "] " + format.formatPlacement(placement);
			writer.writeToFile(placement1);
		}
	}

	private void processMovement(String currentLine) {
		if (!movementBegun) {
			movementBegun = true;
		}
		ArrayList<String> movements = finder.getMovementDirectives(currentLine);
		writer.writeToFile(format.formatMovement(movements.get(0), true));
		writer.writeToFile(format.formatMovement(movements.get(1), false));
	}
	private void processCastling(String currentLine){
		ArrayList<String> lineAction = finder.getLineAction(currentLine);
		if(finder.containsSingleMovement(currentLine)){	
			if(lineAction.size() == 2){
				if(finder.isCastle(lineAction.get(0))){
					writer.writeToFile(format.formatCastle(lineAction.get(0), true));
				}else{
					writer.writeToFile(format.formatMovement(lineAction.get(0), true));
				}
				if(finder.isCastle(lineAction.get(1))){
					writer.writeToFile(format.formatCastle(lineAction.get(1), false));
				}else{
					writer.writeToFile(format.formatMovement(lineAction.get(1), false));
				}	
			}
		}else{
			writer.writeToFile(format.formatCastle(lineAction.get(0), true));
			writer.writeToFile(format.formatCastle(lineAction.get(1), false));
		}
	}

	public void shutdown() {
		try {
			writer.writeToFile("Process: Closing Files.");
			file.close();
			writer.closeLogFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	
}

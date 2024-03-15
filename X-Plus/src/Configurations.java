// Configurations.java --> This class implements the necessary methods for Play.java to run the game
public class Configurations {
	// Declaring variables (game board, length of x or + shape required to win, max levels, and board size
	private char[][] board;
	private int winLength, maxLvls, size;
	
	public Configurations(int board_size, int lengthToWin, int max_levels) {
		// Initializing the variables
		this.size = board_size;
		this.winLength = lengthToWin;
		this.maxLvls = max_levels;
		this.board = new char[board_size][board_size];
		// Setting all positions on the board to ' ' to indicate position is empty
		for(int i = 0; i < this.size; i++) {
			for(int j = 0; j < this.size; j++) {
				this.board[i][j] = ' ';
			}
		}
	}
	
	public HashDictionary createDictionary() {	// method to return an empty HashDictionary of size 8461
		return new HashDictionary(8461);
	}
	
	private String boardCharToStr() { // Stores current board configuration in a string, and returns them
		String config = "";
		for(int i = 0; i < this.size; i++) {	// loop through board and add the character at each position to the string
			for(int j = 0; j < this.size; j++) {
				config += this.board[i][j];
			}
		}
		
		return config;
	}
	
	public int repeatedConfiguration(HashDictionary hashTable) { 
		String config = boardCharToStr(); // Stores board contents in a string, then checks if this string is already a key in hashTable
		return hashTable.get(config);
	}
	
	public void addConfiguration(HashDictionary hashDictionary, int score) { 
		String config = boardCharToStr(); // Represents content of board as a string
		Data newRecord = new Data(config, score); // Create a record with this string key and given score
		hashDictionary.put(newRecord);	// Store record in hashDictionary
	}
	
	public void savePlay(int row, int col, char symbol) { // Stores symbol at specified position in board
		this.board[row][col] = symbol;
	}
	
	public boolean squareIsEmpty(int row, int col) {	// Checks if a square at specified position on the board is empty
		if(this.board[row][col] == ' ') {
			return true;
		} else {
			return false;
		}
	}
	
	private int countMatching(int row, int col, int dx, int dy, char symbol) {
	    int count = 0; // Initialize a counter to keep track of matching squares
	    while (row >= 0 && row < this.size && col >= 0 && col < this.size && this.board[row][col] == symbol) {
	        count++; // Increase count
	        row += dx; // Move to the next row based on the specified direction
	        col += dy; // Move to the next column based on the specified direction
	    }
	    return count; 
	}
	
	private boolean xShape(int row, int col, char symbol) { // Check for valid X shape
		int[] dx = {1, -1, 1, -1}; // 2 arrays used for diagonal directions
		int[] dy = {-1, -1, 1, 1}; // When combined --> up & right, up & left, down & right, down & left
		int xLength = 1;  // Initialize the length to 1 (center square)
		
		for(int countDirection = 0; countDirection < 4; countDirection++) {	// Loop through directions, and count matching squares in each diagonal direction
			int directionLength = countMatching(row, col, dx[countDirection], dy[countDirection], symbol) - 1; // Subtract 1 from matching count since center is accounted for again
			if(directionLength < 1) {
				return false; // If the direction has less than 1 matching tile, it's not a valid X-shape.
			} else {
				xLength += directionLength;	// Add matching count to X shape length
			}
		}
		
		return xLength  >= this.winLength;	// Return true if X shape length is sufficient, else return false
	}
	
	private boolean plusShape(int row, int col, char symbol) { // Same as xShape but for + plus shape, count matching in horizontal and vertical
		int[] dx = {1, -1, 0, 0}; 
		int[] dy = {0, 0, -1, 1}; // Direction when arrays are combined --> right, left, up, down
		int plusLength = 1; 
		
		for(int countDirection = 0; countDirection < 4; countDirection++) {
			int directionLength = countMatching(row, col, dx[countDirection], dy[countDirection], symbol) - 1;
			if(directionLength < 1) {
				return false;
			} else {
				plusLength += directionLength;
			}
		}
		
		return plusLength >= this.winLength;
	}
	
	public boolean wins(char symbol) {	// Checks if either player has won the game
		 for(int i = 0; i < this.size; i++) {	// loop through each square on the board
			 for(int j = 0; j < this.size; j++) {
				 char current = this.board[i][j];
				// If the square has the specified symbol and there's a valid x or + shape around it of sufficient length, return true
				 if(current == symbol) {  
					 if(xShape(i, j, current)) {
						 return true;
					 }
					 
					 if(plusShape(i, j, current)) {
						 return true;
					 }
				 }
			 }
		 }
		 // Return false if no valid x or + shape
		 return false;
	}
	
	public boolean isDraw() {	// Checks if the game is a draw
		String config = boardCharToStr();	// Get string of current board configuration (string of contents)
		if(config.contains(" ")) {	// If there is an empty square, signified by whitespace in the string, not a draw
			return false;
		} else if(wins('X') || wins('O')) {	// If either player has won, not a draw
			return false;
		} else {	// Otherwise, return a draw
			return true;
		}
	}
	
	public int evalBoard() { // Evaluate the current situation of the game
		if(wins('O')) {	// If computer has won, return 3
			return 3;
		} else if(wins('X')){	// If human won, return 0
			return 0;
		} else if(isDraw()) {	// If the game is a draw, return 2
			return 2;
		} else {	// If undecided, return 0
			return 1;
		}
	}
}

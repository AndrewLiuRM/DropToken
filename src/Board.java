/*
 * Author: Andrew Liu
 * This is a class for a Board object that represent the board where the index of the board
 * is the index of the array and the value of the position is 0 if that position is empty,
 * 1 if that position is taken by user 1, and 2 if that position is taken by user 2.
 * Representation invariant: all elements in board should be 0, 1, or 2. the currentUser
 * should be either 1 or 2.
 */
public class Board {
	private int[][] board;
	private int currentUser;
	
	/*
	 * the constructor of board, initializes currentUser to user 1.
	 */
	public Board() {
		board = new int[4][4];
		currentUser = 1;
	}
	
	/*
	 * Get the status of the position on the board
	 * @param	x the x position
	 * 			y the y position
	 * @requires 0 <= x <= 3 && 0 <= y <= 3
	 * @returns	1 if it is occupied by user 1
	 * 			2 if it is occupied by user 2
	 * 			0 if it is empty
	 */
	public int getPositionStatus(int x, int y) {
		return board[x][y];
	}
	
	/*
	 * Get the status of the game
	 * @returns	1 if user 1 wins
	 * 			2 if user 2 wins
	 * 			0 if the board is still not full but no one wins
	 * 			-1 if the board is full but still no one wins
	 */
	public int getGameStatus() {
		if (getStatusForOneUser(1)) {
			return 1;
		}
		if (getStatusForOneUser(2)) {
			return 2;
		}
		// check if the board is full
		for (int i = 0; i < 4; i ++) {
			for (int j = 0; j < 4; j ++) {
				if (board[i][j] == 0) {
					return 0;
				}
			}
		}
		// it is full
		return -1;
	}
	
	/*
	 * Returns 1 if the user wins
	 * @param	user the user being checked
	 * @require	user is valid
	 * @return	true if user wins
	 * 			false otherwise
	 */
	private boolean getStatusForOneUser(int user) {
		// check rows
		for (int i = 0; i < 4; i ++) {
			boolean indicator = true;
			for (int j = 0; j < 4; j ++) {
				if (board[i][j] != user) {
					indicator = false;
					break;
				}
			}
			if (indicator) {
				return true;
			}
		}
		// check columns
		for (int i = 0; i < 4; i ++) {
			boolean indicator = true;
			for (int j = 0; j < 4; j ++) {
				if (board[j][i] != user) {
					indicator = false;
					break;
				}
			}
			if (indicator) {
				return true;
			}
		}
		// check diagonal
		// top left to bottom right
		boolean indicator = true;
		for (int i = 0; i < 4; i ++) {
			if (board[i][i] != user) {
				indicator = false;
				break;
			}
		}
		if (indicator) {
			return true;
		}
		// top right to bottom left
		indicator = true;
		for (int i = 0; i < 4; i ++) {
			if (board[i][3 - i] != user) {
				indicator = false;
				break;
			}
		}
		if (indicator) {
			return true;
		}
		
		// no win
		return false;
	}
	
	/*
	 * put user in column
	 * @param	column the column to put in
	 * @returns	-1 if putting failed
	 * 			1 if putting succeeded
	 */
	public int put(int column) {
		int position = getPuttingPosition(column);
		if (position == -1) {
			return -1;
		}
		board[position][column] = currentUser;
		// alter current user
		currentUser = currentUser == 1 ? 2 : 1;
		return 1;
	}
	
	/*
	 * get the next putting position
	 * @param	column the column to put in
	 * @require	column is valid
	 * @returns	the position of the next put if valid
	 * 			-1 otherwise
	 */
	private int getPuttingPosition(int column) {
		for (int i = 3; i >= 0; i--) {
			if (board[i][column] == 0) {
				return i;
			}
		}
		return -1;
	}
	
	/*
	 * clear the board
	 */
	public void clear() {
		board = new int[4][4];
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			builder.append('|');
			for (int j = 0; j < 4; j++) {
				builder.append(" ");
				builder.append(board[i][j]);
			}
			builder.append("\r\n");
		}
		builder.append("+--------\r\n");
		builder.append("  1 2 3 4");
		return builder.toString();
	}
	
}

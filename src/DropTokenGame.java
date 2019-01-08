import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Author: Andrew Liu
 * This is a class for playing the Drop Token Game
 */
public class DropTokenGame {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		Board board = new Board();
		System.out.print("> ");
		String command = s.nextLine().toUpperCase();
		List<Integer> list = new ArrayList<Integer>();
		boolean isOver = false;
		// manage commands
		while (!command.equals("EXIT")) {
			if (command.startsWith("PUT")) {
				// the game is over
				if (isOver) {
					System.out.println("ERROR, GAME IS OVER!");
					System.out.print("> ");
					command = s.nextLine().toUpperCase();
					continue;
				}
				try {
					isOver = put(command, board, list);
				} catch (NumberFormatException e) {
					System.out.println("Unrecognized Command, failed to recognize column");
					System.out.print("> ");
					command = s.nextLine().toUpperCase();
					continue;
				}
			} else if(command.equals("GET")) {
				get(list);
			} else if(command.equals("BOARD")) {
				System.out.println(board.toString());
			} else {
				System.out.println("Unrecognized Command");
			}
			System.out.print("> ");
			command = s.nextLine().toUpperCase();
		}
		s.close();
	}
	
	/*
	 * Handles the put command
	 * @param	command the command starting with "PUT"
	 * 			board the board we are currently using
	 * 			list the list of successful puts
	 * @require	list and board not null
	 * @returns	true if the game is over
	 * 			false otherwise
	 */
	private static boolean put(String command, Board board, List<Integer> list) throws NumberFormatException {
		String[] splited = command.split(" ");
		if (splited.length != 2) {
			// unspecified command
			System.out.println("Unrecognized Command");
			return false;
		}
		int column = Integer.parseInt(splited[1]);
		if (column < 0 || column > 4) {
			// unspecified command
			System.out.println("Unrecognized Command");
			return false;
		}
		// put
		int status = board.put(column - 1);
		// check status
		if (status == -1) {
			System.out.println("ERROR");
			return false;
		}
		// put successful, add to the list
		list.add(column);
		// check game status
		status = board.getGameStatus();
		if (status == 0) {
			System.out.println("OK");
			return false;
		} else if (status == -1) {
			System.out.println("DRAW");
		} else {
			System.out.println("WIN");
		}
		return true;
	}
	
	/*
	 * Handles the get command
	 * @param list the list of successful put commands
	 */
	private static void get(List<Integer> list) {
		for (int i = 0; i < list.size(); i ++) {
			System.out.println(list.get(i));
		}
	}
}

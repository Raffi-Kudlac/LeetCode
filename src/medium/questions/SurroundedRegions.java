package medium.questions;

import helper.SolutionOutline;

public class SurroundedRegions extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("SurroundedRegions");
		this.setDifficulty("Medium");
		
		char[][] board = buildBoard();
		this.setInput(TwoDCharArrayToStirng(board));
		String answer = TwoDCharArrayToStirng(this.solution(board));
		this.setOutput(answer);
		this.printResult();
	} 
	
	public char[][] solution(char[][] board) {
		
		// checks top and bottom horizontal rows
		for (int y = 0; y < board[0].length; y ++) {
			isSafe(board, 0, y);
			isSafe(board, board.length -1, y);
		}
		
		// checks left and right vertical columns
		for (int x = 1; x < board.length - 1; x++) {
			isSafe(board, x, 0);
			isSafe(board, x, board[x].length -1);
		}
		
		for (int x = 0; x < board.length; x++) {
			for (int y = 0; y < board[x].length; y++) {
				
				if (board[x][y] == 'O') {
					board[x][y] = 'X';
				} else if (board[x][y] == 'S') {
					board[x][y] = 'O';
				}
			}
		}
		
		return board;
	}
	
	public char[][] buildBoard() {
		char[][] board = {
			{'X', 'X', 'X', 'X'},
			{'X', 'O', 'O', 'X'},
			{'X', 'X', 'O', 'X'},
			{'X', 'O', 'X', 'X'}
		};
		return board;
	}
	
	public void isSafe(char[][] board, int x, int y) {
		
		try {
			if (board[x][y] == 'X' || board[x][y] == 'S') {
				return;
			}
		} catch(Exception e) {
			return;
		}
		
		board[x][y] = 'S';
		
		isSafe(board, x - 1, y);
		isSafe(board, x + 1, y);
		isSafe(board, x, y - 1);
		isSafe(board, x, y + 1);
	}

}

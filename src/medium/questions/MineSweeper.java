package medium.questions;

import helper.SolutionOutline;

public class MineSweeper extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("MineSweeper");
		this.setDifficulty("Medium");
		
		char[][] test = getTestBoard();		
		this.setInput(TwoDCharArrayToString(test));
		this.solution(test, new int[] {1, 2});
		this.setOutput(TwoDCharArrayToString(test));
		this.printResult();
	}
	
	public char[][] getTestBoard() {
		char [][] board = {
			{'E', 'E', 'E', 'E', 'E'},
			{'E', 'E', 'M', 'E', 'E'},
			{'E', 'E', 'E', 'E', 'E'},
			{'E', 'E', 'E', 'E', 'E'}
		};
		
		return board;
	}
	
	public void solution(char[][] board, int[] click) {
		int x = click[0];
		int y = click[1];
		char boardChar;
		
		try {
			boardChar = board[x][y];
		} catch(Exception e) {
			return;
		}
		
		if (boardChar == 'M') {
			board[x][y] = 'X';
			return;
		} else if (boardChar == 'B' || (boardChar >= '1' && boardChar <= '8')) {
			return;
		}
		
		// else it is 'E'
		int counter = 0;
		for (int xMod = -1; xMod < 2; xMod++) {
			for (int yMod = -1; yMod < 2; yMod++) {
				
				try {
					if (yMod != 0 || xMod != 0) {
						if (board[x + xMod][y + yMod] == 'M') {
							counter++;
						}
					} 
				} catch(Exception e) {
					
				}
				
			}
		}
		
		if (counter != 0) {
			board[x][y] = Integer.toString(counter).charAt(0);
		} else {
			board[x][y] = 'B';
			
			for (int xMod = -1; xMod < 2; xMod++) {
				for (int yMod = -1; yMod < 2; yMod++) {
					
					if (yMod != 0 || xMod != 0) {
						solution(board, new int[]{x + xMod, y + yMod});						
					}
				}
			}
			
		}
	}		
}

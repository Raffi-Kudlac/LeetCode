package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.SolutionOutline;

public class RepeatedSubArray extends SolutionOutline{
	
	public void runTest() {
		this.setClassName("RepeatedSubArray");
		this.setDifficulty("Medium");
		
		int[] A = {1,2,3,2,1};
		int[] B = {3,2,1,2,3,2,4,7};
		this.setInput(Arrays.toString(A) + "------" + Arrays.toString(B));
		int answer = this.solution(A, B);
		this.setOutput(Integer.toString(answer));
		this.printResult();
	}
	
	public int solution(int[] A, int[] B) {
			
		int[][] answer = new int[A.length + 1][B.length+1];
		
		for (int x = 0; x < B.length; x++) {
			answer[0][x] = 0;
		}
		
		for (int y = 0; y < A.length; y++) {
			answer[y][0] = 0;
		}
		
		int oneBack, oneUp, diagonal;
		
		for (int y = 1; y <= A.length; y++) {
			for (int x = 1; x <= B.length; x++) {
				 oneBack = answer[y][x - 1];
				 oneUp = answer[y - 1][x];
				 diagonal = answer[y -1][x -1];
				 
				 if (A[y-1] == B[x-1]) {
					 answer[y][x] = Math.max(diagonal + 1, oneUp);
					 answer[y][x] = Math.max(answer[y][x], oneBack);
				 } else {
					 answer[y][x] = Math.max(oneBack, oneUp);
				 }
			}
		}
		printMatrix(answer);
		getFullAnswer(answer, A, B, answer[A.length][B.length]);
		return answer[A.length][B.length];
	}
	
	public void getFullAnswer(int[][] matrix, int[] A, int[] B, int answer) {
		List<Integer> answerList = new ArrayList<Integer>();
		int find = 1;
		
		for (int y = 0; y <= A.length; y++) {
			for(int x = 0; x <= B.length; x++) {
				if (matrix[y][x] == find) {
					answerList.add(A[y-1]);
					find++;
				}
				
				if (find > answer) {
					break;
				}
			}
		}
		
		System.out.print("Full answer: ");
		System.out.println(answerList.toString());
		
	}
	
	public void printMatrix(int[][] answer) {
		
		for (int[] row : answer) {
			System.out.println(Arrays.toString(row));
		}
	}
}

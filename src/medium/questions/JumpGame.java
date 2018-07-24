package medium.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class JumpGame extends SolutionOutline {

	
	public void runTest() {
		this.setClassName("JumpGame");
		this.setDifficulty("Medium");
		
		int[] test = {1, 3, 6, 1, 0, 9};
		//int[] test = {3,2,1,0,4};
		this.setInput(Arrays.toString(test));
		
		int answer = this.getNumberOfSteps(test);
		this.setOutput(Integer.toString(answer));
		
//		boolean answer = this.solution(test);
//		this.setOutput(Boolean.toString(answer));
		this.printResult();
	} 
	
	public boolean solution(int[] path) {
		
		int lastGoodIndex = path.length - 1;
		
		for (int index = path.length - 2; index >= 0; index--) {
			if (path[index] + index >= lastGoodIndex) {
				lastGoodIndex = index;
			} 
		}
		
		return lastGoodIndex == 0;		
	}
	
	public int getNumberOfSteps(int[] path) {
		
		int[] jumps = new int[path.length];
		
		Arrays.fill(jumps, -1);		
		jumps[0] = 0;
		
		int pathElement;
		for (int x = 0; x < path.length; x++) {
			pathElement = path[x];
			
			for (int p = x + 1; p <= x + pathElement; p++) {
				
				if (p == jumps.length) {
					break;
				}
				
				if (jumps[p] == -1) {
					jumps[p] = jumps[x] + 1;
				}
			}
		}
		
		return jumps[jumps.length - 1];
	}
	
}

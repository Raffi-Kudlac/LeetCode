package hard.questions;

import helper.SolutionOutline;

public class KEggs extends SolutionOutline {
	
	/**
	 * Idea
	 * 		see https://www.youtube.com/watch?v=3hcaVyX00_4
	 */
	
	public void runTest() {
		this.setClassName("KEggs");
		this.setDifficulty("Hard");
		
		int floors = 600;
		int eggs = 5;
		
		this.setInput("floors: " + floors +", eggs: " + eggs);
		int answer = this.solution(floors, eggs);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/super-egg-drop/description/
	public int solution(int floors, int eggs) {
		
		int[][] dp = new int[eggs + 1][floors + 1];		
		int max = 0;
		for (int x = 0; x <= floors; x++) {
			dp[1][x] = x;
		}
		
		for (int x = 1; x <= eggs; x++) {
			dp[x][1] = 1;
		}
		
		for (int currentEgg = 2; currentEgg <= eggs; currentEgg++) {
			for (int currentFloor = 2; currentFloor <= floors; currentFloor++) {
				
				dp[currentEgg][currentFloor] = Integer.MAX_VALUE;
				
				for (int k = 1; k < currentFloor; k++) {
					max = 1 + Math.max(dp[currentEgg - 1][k - 1], dp[currentEgg][currentFloor - k]);					
					dp[currentEgg][currentFloor] = Math.min(dp[currentEgg][currentFloor], max);
				}			
			}
		}
		
		return dp[eggs][floors];
	}	
}

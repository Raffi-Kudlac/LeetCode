package medium.questions;

import helper.SolutionOutline;

public class StoneGame extends SolutionOutline {
	public void runTest() {
		this.setClassName("StoneGame");
		this.setDifficulty("Medium");
		
		int[] test = {11,5,3,4,5, 1};		
		this.setInput(test);
		boolean answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/stone-game/description/
	public boolean solution(int[] piles) {
		
		int[][] dp = new int[piles.length][piles.length];
		
		buildDP(dp, 0, piles.length - 1, 1, piles);
		
		return dp[0][piles.length - 1] > 0;
	}
	
	public int buildDP(int[][] dp, int i, int j, int turn, int[] piles) {
		
		if (i > j) {
			return 0;
		} else if (dp[i][j] != 0) {
			return dp[i][j];
		}
		
		if (turn == 1) {
			dp[i][j] = Math.max(piles[i] + buildDP(dp, i + 1, j, turn * -1, piles),
					piles[j] + buildDP(dp, i, j - 1, turn * -1,  piles));
		} else {
			dp[i][j] = Math.min(-piles[i] + buildDP(dp, i + 1, j, turn * -1, piles),
					-piles[j] + buildDP(dp, i, j - 1, turn * -1,  piles));
		}
		
		return dp[i][j];
	}
}

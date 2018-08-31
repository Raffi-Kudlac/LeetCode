package medium.questions;

import helper.SolutionOutline;

public class PredictTheWinner extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("PredictTheWinner");
		this.setDifficulty("Medium");
		
		int[] test = {1, 5, 233, 7};		
		this.setInput(test);
		boolean answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public boolean solution(int[] nums) {
		Integer[][] dp = new Integer[nums.length][nums.length];
		return Predict(nums, 0, nums.length - 1, 1, dp) >= 0;		
	}
	
	// https://leetcode.com/problems/predict-the-winner/description/
	public int Predict(int[] nums, int left, int right, int turn, Integer[][] dp) {
		
		if (left > right) {
			return 0;
		}
		
		if (dp[right][left] != null) {
			return dp[right][left];
		}
		
		int goLeft;
		int goRight;
		
		if (turn == 1) {
			goLeft = nums[left] + Predict(nums, left + 1, right, turn * -1, dp);
			goRight = nums[right] + Predict(nums, left, right - 1, turn * -1, dp);
			
			dp[right][left] = Math.max(goLeft, goRight);
			
		} else {
			goRight = -nums[right] + Predict(nums, left, right - 1, turn * -1, dp);
			goLeft = -nums[left] + Predict(nums, left + 1, right, turn * -1, dp);
			
			dp[right][left] = Math.min(goLeft, goRight);
		}		
		
		return dp[right][left];
	}
}

package medium.questions;

import helper.SolutionOutline;

public class MaximumProductSubArray extends SolutionOutline {
	public void runTest() {
		this.setClassName("MaximumProductSubArray");
		this.setDifficulty("Medium");
		
		int[] test = {2,3,-2,4};		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/maximum-product-subarray/description/
	public int solution(int[] nums) {
				
		int max = nums[0];
		int min = nums[0];
		int answer = nums[0];
		int tempMax, tempMin;
		for (int index = 1; index < nums.length; index++) {
			tempMax = max; tempMin = min;
			
			max = Math.max(nums[index], Math.max(nums[index] * tempMax, nums[index] * tempMin));
			min = Math.min(nums[index], Math.min(nums[index] * tempMax, nums[index] * tempMin));
			answer = Math.max(max, answer);
		}
				
		return answer;
	}	
}

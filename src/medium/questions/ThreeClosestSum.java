package medium.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class ThreeClosestSum extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("ThreeClosestSum");
		this.setDifficulty("Medium");
		
		int[] test = {-1, 2, 1, -4};		
		this.setInput(test);
		int answer = this.solution(test, 1);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(int[] nums, int target) {
		
		Arrays.sort(nums);
		
		int left, middle, right, sum, answer;
		answer = nums[0] + nums[1] + nums[nums.length - 1];
		
		for (int x = 0; x < nums.length - 2; x++) {
			left = x;
			
			middle = x + 1;
			right = nums.length - 1;
			
			while (middle < right) {
				sum = nums[left] + nums[middle] + nums[right];
				
				if (sum < target) {
					middle++;
				} else if (sum > target) {
					right--;
				}
				
				if (Math.abs(target - answer) > Math.abs(target - sum)) {
					answer = sum;
				}
			}
		}
		
		return answer;
	}	
}

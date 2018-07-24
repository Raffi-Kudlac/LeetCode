package medium.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class FindDuplicate extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("FindDuplicate");
		this.setDifficulty("Medium");
		
		int[] test = {1, 3, 7, 8, 4, 2, 5, 3, 6};
		//int[] test = {1,3,4,2,2};
		this.setInput(Arrays.toString(test));
		String answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public String solution(int[] nums) {
		int hair = nums[0];
		int turtle = nums[0];
		
		do {
			
			turtle = nums[turtle];
			hair = nums[nums[hair]];
		} while(hair != turtle);
		
		int newPointer = nums[0];
		
		while(newPointer != turtle) {
			newPointer = nums[newPointer];
			turtle = nums[turtle];
		}
		
		return Integer.toString(newPointer);
	}	
}

package medium.questions;

import helper.SolutionOutline;

public class ReachANumber extends SolutionOutline {
	
	/**
	 * Idea
	 * 		https://leetcode.com/problems/reach-a-number/discuss/112968/Short-JAVA-Solution-with-Explanation
	 */
	public void runTest() {
		this.setClassName("ReachANumber");
		this.setDifficulty("Medium");
		
		int test = 5;		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}
	
	// https://leetcode.com/problems/reach-a-number/description/
	public int solution(int target) {
		target = Math.abs(target);
		int steps = 0;
		int sum = 0;
		
		while (sum < target) {
			steps++;
			sum += steps;			
		}
		
		int diff = sum - target;
		
		while (diff % 2 != 0) {
			steps++;
			diff+= steps;			
		}
		
		return steps;
	}
}

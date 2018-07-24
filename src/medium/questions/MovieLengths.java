package medium.questions;

import java.util.Arrays;
import java.util.HashMap;

import helper.SolutionOutline;

public class MovieLengths extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("");
		this.setDifficulty("Medium");
		
		int sum = 20;
		int[] test = {1, 2, 4, 6, 7, 8};		
		this.setInput(Arrays.toString(test) + "target sum: " + sum);
		boolean answer = this.solution(test, sum);
		this.setOutput(Boolean.toString(answer));
		this.printResult();
	}  
	
	
	public boolean solution(int[] nums, int sum) {
		
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		int opposite;
		for (int num : nums) {
			opposite = sum - num;
			
			if (map.containsKey(opposite)) {
				return true;
			}
			
			map.put(num, true);
		}
		
		return false;
	}		
}

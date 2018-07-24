package hard.questions;

import java.util.ArrayList;
import java.util.Collections;

import helper.SolutionOutline;

public class CountSmaller extends SolutionOutline {

	public void runTest() {
		this.setClassName("CountSmaller");
		this.setDifficulty("Hard");
		
		int[] test = {5,2,6,1};		
		this.setInput(test);
		int[] answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int[] solution(int[] nums) {		
		int[] answer = new int[nums.length];
		ArrayList<Integer> toTheRight = new ArrayList<Integer>();
		answer[nums.length -1] = 0;
		toTheRight.add(nums[nums.length - 1]);
		
		for (int x = nums.length - 2; x >= 0; x--) {
			answer[x] = getNumsBelow(nums[x], toTheRight);
			placeNum(nums[x], toTheRight);
		}
		
		
		return answer;
	}
	
	public void placeNum(int n, ArrayList<Integer> toTheRight) {
		int location = Collections.binarySearch(toTheRight, n);
		
		if (location < 0) {
			toTheRight.add(Math.abs(location) - 1, n);
		} else {
			toTheRight.add(location, n);
		}
	}
	
	public int getNumsBelow(int n, ArrayList<Integer> toTheRight) {
		
		int location = Collections.binarySearch(toTheRight, n);
		
		if (location < 0) {
			return Math.abs(location) - 1;
		}
		
		int val = toTheRight.get(location);
		int current = val;
		while (current != val) {
			location--;
			val = toTheRight.get(location);
		}
		
		return location;
	}
	
	
}

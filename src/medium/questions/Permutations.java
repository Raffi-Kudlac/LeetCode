package medium.questions;

import java.util.ArrayList;
import java.util.List;

import helper.SolutionOutline;

public class Permutations extends SolutionOutline {
	public void runTest() {
		this.setClassName("Permutations");
		this.setDifficulty("Medium");
		
		int[] test = {1,2,3, 4};		
		this.setInput(test);
		List<List<Integer>> answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public List<List<Integer>> solution(int[] nums) {
		
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> entry = new ArrayList<Integer>();		
		for (Integer element : nums) {
			entry.add(element);
		}
		
		List<Integer> first = new ArrayList<>(entry);
		
		do {			
			answer.addAll(helper(entry));			
		} while(!first.equals(entry));
		
		return answer;
	}
	
	
	public List<List<Integer>> helper(List<Integer> nums) {
		List<List<Integer>> answer = new ArrayList<>();
		Integer target = nums.remove(0);
		List<Integer> entry;
		
		for (int index = 0; index < nums.size(); index++) {
			entry = new ArrayList<>(nums);
			entry.add(index, target);
			answer.add(entry);
		}
		
		nums.add(target);
		return answer;
	}
}

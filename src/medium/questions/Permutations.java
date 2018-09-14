package medium.questions;

import java.util.ArrayList;
import java.util.List;

import helper.SolutionOutline;

public class Permutations extends SolutionOutline {
	public void runTest() {
		this.setClassName("Permutations");
		this.setDifficulty("Medium");
		
		int[] test = {1, 2, 3, 4};
		this.setInput(test);
		List<List<Integer>> answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}

	// https://leetcode.com/problems/permutations/description/
	public List<List<Integer>> solution(int[] nums) {
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> baseEntry = new ArrayList<>();
		for (Integer element : nums) {
			baseEntry.add(element);
		}

		int nextSwap = 0;
		while(nextSwap < baseEntry.size()){
			swap(0, nextSwap, baseEntry);
			answer.addAll(helper(baseEntry));
			nextSwap++;
		}

		return answer;
	}

	public void swap(int index1, int index2, List<Integer> entry) {
		int val = entry.get(index1);
		entry.set(index1, entry.get(index2));
		entry.set(index2, val);
	}

	public List<List<Integer>> helper(List<Integer> nums) {
		List<List<Integer>> answer = new ArrayList<>();
		List<Integer> first = new ArrayList<>(nums);

		int current = nums.size() - 1;

		do {
			swap(current, current - 1, nums);
			answer.add(new ArrayList<>(nums));

			if (current == 2) {
				current = nums.size() - 1;
			} else {
				current--;
			}
		} while(!first.equals(nums));
		return answer;
	}
}

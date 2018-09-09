package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class CombinationSum extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("CombinationSum");
		this.setDifficulty("Medium");

		int[] test = {2, 3, 5, 10};
		this.setInput(Arrays.toString(test));
		String answer = this.solution(test, 10).toString();
		this.setOutput(answer);
		this.printResult();
	}

	public List<List<Integer>> solution(int[] nums, int target) {
		Arrays.sort(nums);
		List<List<Integer>> answer = new ArrayList<>();
		//travserse(nums, answer, target, 0, 0, new ArrayList<>());

		HashMap<String, List<List<Integer>>> dp = new HashMap<>();
		return travserse2(nums, target, 0, dp);
	}

	// https://leetcode.com/problems/combination-sum/description/
	public void travserse(int[] nums, List<List<Integer>> answer, int target, int index, int sum,
						  List<Integer> current) {

		if (sum == target) {
			answer.add(new ArrayList<>(current));
			return;
		} else if (index == nums.length || sum > target) {
			return;
		}

		current.add(nums[index]);
		travserse(nums, answer, target, index, sum + nums[index], current);

		current.remove(current.size() - 1);
		travserse(nums, answer, target, index + 1, sum, current);
	}

	// https://leetcode.com/problems/combination-sum-ii/description/
	public List<List<Integer>> travserse2(int[] nums, int target, int index, HashMap<String, List<List<Integer>>> dp) {

		List<List<Integer>> answer = new ArrayList<>();
		String key = index + "+" + target;
		if (dp.containsKey(key)) {
			return dp.get(key);
		} else if (0 == target) {
			List<Integer> elm = new ArrayList<>();
			answer.add(elm);
			return answer;
		} else if (target < 0 || index == nums.length) {
			return answer;
		}

		List<List<Integer>> skip;
		List<List<Integer>> include;

		skip = travserse2(nums, target, index + 1, dp);
		include = travserse2(nums, target - nums[index], index + 1, dp);

		for (List<Integer> elm : include) {
			elm.add(nums[index]);
		}

		answer.addAll(skip);
		answer.addAll(include);

		dp.put(key, answer);
		return answer;
	}
}

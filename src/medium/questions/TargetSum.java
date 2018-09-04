package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;
import java.util.HashMap;

public class TargetSum extends SolutionOutline {

    public void runTest() {
        this.setClassName("TargetSum");
        this.setDifficulty("Medium");

        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        this.setInput("Nums: " + Arrays.toString(nums) + "\n\ttarget: " + target);
        int answer = this.solution(nums, target);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/target-sum/description/
    public int solution(int[] nums, int target) {
        HashMap<String, Integer> mem = new HashMap<>();
        return traverse(mem, nums, 0, 0, target);
    }

    public int traverse (HashMap<String, Integer> mem, int[] nums, int index, int sum, int target) {

        String key = index + "+" + sum;
        if (mem.containsKey(key)) {
            return mem.get(key);
        }

        if (index == nums.length) {
            return sum == target ? 1 : 0;
        }

        int plus = traverse(mem, nums, index + 1, sum + nums[index], target);
        int minus = traverse(mem, nums, index + 1, sum - nums[index], target);
        mem.put(key, plus + minus);
        return mem.get(key);
    }
}

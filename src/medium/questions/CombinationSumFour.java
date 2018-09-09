package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CombinationSumFour extends SolutionOutline {

    public void runTest() {
        this.setClassName("CombinationSumFour");
        this.setDifficulty("Medium");

        int[] test = {1,2,3};
        this.setInput(test);
        int answer = this.solution(test, 10);
        this.setOutput(answer);
        this.printResult();
    }

    public int solution(int[] nums, int target) {
        Arrays.sort(nums);
        ArrayList<Integer> path = new ArrayList<>();
        return traverse(nums, 0, 0, target, path);
    }

    public int traverse(int[] nums, int index, int currentSum, int targetSum, ArrayList<Integer> path) {
        if (currentSum == targetSum) {
            return calcPath(path);
        } else if (currentSum > targetSum || index == nums.length) {
            return 0;
        }

        path.add(nums[index]);
        int takeSelf = traverse(nums, index, currentSum + nums[index], targetSum, path);

        path.remove(path.size() - 1);
        int skip = traverse(nums, index + 1, currentSum, targetSum, path);
        return skip + takeSelf;
    }

    public int calcPath(ArrayList<Integer> path) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (Integer val : path) {
            if (map.putIfAbsent(val, 1) != null){
                map.put(val, map.get(val) + 1);
            }
        }
        int j = factorial(path.size());

        for (Integer n : map.values()) {
            j = j / factorial(n);
        }
        return j;
    }

    public int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n -1);
    }
}

package easy.questions;

import helper.SolutionOutline;

import java.util.HashMap;

public class ClimbingStairs extends SolutionOutline {

    public void runTest() {
        this.setClassName("ClimbingStairs");
        this.setDifficulty("Easy");

        int[] test = {0,0,1,1};
        this.setInput(test);
        int answer = this.solution2(test);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/min-cost-climbing-stairs/description/
    public int solution(int[] cost) {
        HashMap<String, Integer> dp = new HashMap<>();
        return Math.min(traverse(cost, 0, 0, dp), traverse(cost, 0, 1, dp));
    }

    public int solution2(int[] cost) {

        int one = 0; int two = 0; int current = 0;
        for (int x = cost.length - 1; x >= 0; x--) {
            current = cost[x] + Math.min(one, two);
            two = one;
            one = current;
        }

        return Math.min(one, two);
    }

    public int traverse(int[] cost, int sum, int index, HashMap<String, Integer> dp) {

        String key = index + "+" + sum;

        if (index >= cost.length) {
            return sum;
        } else if (dp.containsKey(key)) {
            return dp.get(key);
        }

        int step1 = traverse(cost, sum + cost[index], index + 1, dp);
        int step2 = traverse(cost, sum + cost[index], index + 2, dp);

        dp.put(key, Math.min(step1, step2));
        return dp.get(key);
    }
}

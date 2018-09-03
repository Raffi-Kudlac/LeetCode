package medium.questions;

import helper.SolutionOutline;

import java.util.HashMap;

public class DeleteAndEarn extends SolutionOutline {

    /**
     * Stole solution from here
     * https://leetcode.com/problems/delete-and-earn/discuss/109895/JavaC++-Clean-Code-with-Explanation
     */

    public void runTest() {
        this.setClassName("DeleteAndEarn");
        this.setDifficulty("Medium");

        int[] test = {2, 2, 3, 3, 3, 4};
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/delete-and-earn/description/
    public int solution(int[] nums) {

        HashMap<Integer, Integer> sums = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            sums.putIfAbsent(num, 0);
            sums.put(num, sums.get(num) + num);
            max = Math.max(max, num);
        }

        int take = 0; int skip = 0;
        int takei, skipi;

        for (int x = 0; x < max + 1; x++) {
            takei = skip + sums.getOrDefault(x, 0);
            skipi = Math.max(take, skip);
            take = takei;
            skip = skipi;
        }

        return Math.max(take, skip);
    }
}

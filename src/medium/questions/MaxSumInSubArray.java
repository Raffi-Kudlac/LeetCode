package medium.questions;

import helper.SolutionOutline;

public class MaxSumInSubArray extends SolutionOutline {

    public void runTest() {
        this.setClassName("MaxSumInSubArray");
        this.setDifficulty("Medium");

        int[] test = {1, -10, 2, 5, -9, 2, 10};
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public int solution(int[] nums) {
        int answer = nums[0];
        int lastVal = nums[0];

        int temp;
        for (int x = 1; x < nums.length; x++) {
            temp = Math.max(nums[x], lastVal + nums[x]);
            lastVal = temp;
            answer = Math.max(answer, temp);
        }

        return answer;
    }
}

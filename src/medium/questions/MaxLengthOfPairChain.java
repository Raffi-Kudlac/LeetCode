package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;

public class MaxLengthOfPairChain extends SolutionOutline {

    public void runTest() {
        this.setClassName("MaxLengthOfPairChain");
        this.setDifficulty("Medium");

        int[][] test = buildPairs();
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public int[][] buildPairs(){
        int[][] pairs = {
                {-10,-8},
                {8,9},
                {-5,0},
                {6,10},
                {-6,-4},
                {1,7},
                {9,10},
                {-4,7}
        };
        return pairs;
    }

    // https://leetcode.com/problems/maximum-length-of-pair-chain/
    public int solution(int[][] pairs) {

        Arrays.sort(pairs, (a, b) -> a[1] - b[1]);

        int cur = Integer.MIN_VALUE, ans = 0;
        for (int[] pair: pairs) if (cur < pair[0]) {
            cur = pair[1];
            ans++;
        }
        return ans;
    }
}

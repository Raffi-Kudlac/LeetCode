package hard.questions;

import helper.SolutionOutline;

public class EditDistance extends SolutionOutline {

    public void runTest() {
        this.setClassName("EditDistance");
        this.setDifficulty("Hard");

        String word1 = "telivision";
        String word2 = "stokes";
        this.setInput("word1: " + word1 + "\n\tword2: " + word2);
        int answer = this.solution(word1, word2);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/edit-distance/description/
    public int solution(String w1, String w2) {

        int[][] dp = new int[w1.length() + 1][w2.length() + 1];
        for (int x = 0; x <= w2.length(); x++) {
            dp[0][x] = x;
        }

        for (int x = 0; x <= w1.length(); x++) {
            dp[x][0] = x;
        }

        for (int x = 1; x <= w1.length(); x++) {
            for (int y = 1; y <= w2.length(); y++) {

                if (w1.charAt(x - 1) != w2.charAt(y -1)) {
                    dp[x][y] = Math.min(dp[x-1][y], dp[x][y-1]) + 1;
                } else {
                    dp[x][y] = dp[x-1][y-1];
                }
            }
        }
        return dp[w1.length()][w2.length()];
    }
}

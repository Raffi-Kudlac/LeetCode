package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParentheses extends SolutionOutline {

    public void runTest() {
        this.setClassName("GenerateParentheses");
        this.setDifficulty("Medium");

        int test = 4;
        this.setInput(test);
        List<String> answer = this.solution(test);
        this.setOutput(Arrays.toString(answer.toArray()));
        this.printResult();
    }

    public List<String> solution(int n) {
        List<String> answer = new ArrayList<>();
        traverse(answer, n, n, "");
        return answer;
    }

    public void traverse(List<String> answer,
         int open, int close, String s) {

        if (open == 0 && close == 0) {
            answer.add(s);
        }

        if (open > 0) {
            traverse(answer, open - 1, close, s + "(");
        }

        if (close > open) {
            traverse(answer, open, close - 1, s + ")");
        }
    }
}

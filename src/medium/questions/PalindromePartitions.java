package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;

public class PalindromePartitions extends SolutionOutline {

    public void runTest() {
        this.setClassName("PalindromePartitions");
        this.setDifficulty("Medium");

        String test = "aab";
        this.setInput(test);
        ArrayList<ArrayList<String>> answer = this.solution(test);
        this.setOutput(TwoDStringListToString(answer));
        this.printResult();
    }

    // https://leetcode.com/problems/palindrome-partitioning/description/
    public ArrayList<ArrayList<String>> solution(String s) {
        ArrayList<ArrayList<String>> answer = new ArrayList<>();
        check(answer, s, new ArrayList(), 0);
        return answer;
    }

    public void check(ArrayList<ArrayList<String>> answer, String s,
                      ArrayList<String> current, int start) {
        if (start == s.length()) {
            answer.add(new ArrayList<>(current));
            return;
        }


        for (int x = start; x < s.length(); x++) {
            if (isPalindrome(s.substring(start, x + 1))) {
                current.add(s.substring(start, x + 1));
                check(answer, s, current, x + 1);
                current.remove(current.size() - 1);
            }
        }
    }
}

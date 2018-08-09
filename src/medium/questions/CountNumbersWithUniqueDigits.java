package medium.questions;

import helper.SolutionOutline;

import java.util.HashMap;

public class CountNumbersWithUniqueDigits extends SolutionOutline {

    public void runTest() {
        this.setClassName("CountNumbersWithUniqueDigits");
        this.setDifficulty("Medium");

        int test = 5;
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
        System.out.println(bruteForce(test));
    }

    public int solution(int n) {

        int counter = 9;
        int current = 9;
        int answer = 10;

        for (int k = 2; k <= n; k++) {
            current *= counter;
            answer += current;
            counter--;
        }

        return answer;
    }

    public int bruteForce(int n) {
        int answer = 0;
        for (int counter = 0; counter < Math.pow(10, n); counter++) {
            answer = containsUniqueChars(counter) ? answer + 1 : answer;
        }

        return answer;
    }

    public boolean containsUniqueChars(int n) {
        String s = Integer.toString(n);

        HashMap<Character, Boolean> map = new HashMap<>();

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) {
                return false;
            } else {
                map.put(c, true);
            }
        }

        return true;
    }
}

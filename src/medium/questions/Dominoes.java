package medium.questions;

import helper.SolutionOutline;

public class Dominoes  extends SolutionOutline {

    public void runTest() {
        this.setClassName("Dominoes");
        this.setDifficulty("Medium");

        String test = ".L.R...LR..L..";
        this.setInput(test);
        String answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/push-dominoes/description/
    public String solution(String s) {

        s = "L" + s;
        s = s + "R";

        int leftLetter = 0;
        int rightLetter = 1;
        while(rightLetter < s.length()) {
            while(s.charAt(rightLetter) == '.') {
                rightLetter++;
            }

            if (rightLetter == leftLetter + 1) {
                leftLetter = rightLetter;
                rightLetter++;
                continue;
            }

            char left = s.charAt(leftLetter);
            char right = s.charAt(rightLetter);

            if (left == 'L' && right == 'L') {
              s = setChars(s, leftLetter + 1, rightLetter, 'L');
            } else if (left=='R' && right == 'R') {
               s = setChars(s, leftLetter + 1, rightLetter, 'R');

            } else if(right == 'L' && left == 'R') {
               s = converge(s, leftLetter + 1, rightLetter - 1);
            }

            leftLetter = rightLetter;
            rightLetter++;
        }

        StringBuilder b = new StringBuilder(s);
        b.deleteCharAt(s.length() - 1);
        b.deleteCharAt(0);

        return b.toString();
    }

    public String converge(String s, int start, int end) {
        StringBuilder b = new StringBuilder(s);

        while(start < end) {
            b.setCharAt(start, 'R');
            b.setCharAt(end, 'L');
            start++; end--;
        }

        return b.toString();
    }

    public String setChars(String s, int start, int end, char letter) {
        StringBuilder b = new StringBuilder(s);

        for(int x = start; x < end; x ++) {
            b.setCharAt(x, letter);
        }

        return b.toString();
    }
}

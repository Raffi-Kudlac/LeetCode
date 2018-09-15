package easy.questions;

import helper.SolutionOutline;

public class ExcelSheetColumnTitle extends SolutionOutline {

    public void runTest() {
        this.setClassName("ExcelSheetColumnTitle");
        this.setDifficulty("Medium");

        int test = 2000;
        this.setInput(test);
        String answer = this.solution2(test);
        this.setOutput(answer);
        this.printResult();
    }

    public String traverse (int n) {

        if (n == 0) {
            return "";
        }
        char c = (char) ((--n % 26) + 'A');
        return traverse(n/26) + c;
    }

    // https://leetcode.com/problems/excel-sheet-column-number/description/
    public String solution2(int n) {
        return traverse(n);
    }

    // https://leetcode.com/problems/excel-sheet-column-number/description/
    public String solution(int n) {
        String answer = "";

        while (n > 0) {
            // minus one because we do not count zero?
            n--;
            int mod = n % 26;
            answer = (char) ('A' + mod) + answer;
            n /= 26;
        }
        return answer;
    }
}

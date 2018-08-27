package medium.questions;

import helper.SolutionOutline;

public class ExactNummberOfAs extends SolutionOutline {

    public void runTest() {
        this.setClassName("ExactNummberOfAs");
        this.setDifficulty("Medium");

        int test = 13;
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public int solution(int n) {
        int sum = 0;
        int counter = 2;

        while (n > 1) {

            while (n % counter == 0) {
                sum += counter;
                n /= counter;
            }
            counter++;
        }
        return sum;
    }

}

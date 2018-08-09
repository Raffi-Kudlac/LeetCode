package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;

public class WorkProblem extends SolutionOutline {

    /**
     * The problem
     *      Find a four didget number n, with no repeating digits such that
     *      if d' = the digits of n sorted in acceding order and that if
     *      d'' = the digits of n sorted in decreasing order then
     *
     *      res = d'' - d'
     *
     *      and all the digits in res are present in n
     */

    public void runTest() {
        this.setClassName("WorkProblem");
        this.setDifficulty("Medium");

        String test = "";
        this.setInput(test);
        String answer = this.solution();
        this.setOutput(answer);
        this.printResult();
    }

    public String solution() {

        int[] number = new int[4];
        number[0] = 9;
        number[1] = 8;
        number[2] = 7;
        number[3] = 6;

        int current = number.length - 1;
        int attempt;
        int compressed;
        while (number[0] > 3) {
            compressed = arrayToInt(number);
            attempt = formula(compressed);
            if (containsSameNumbers(attempt, compressed)) {
                return Integer.toString(compressed);
            }
            if (number[current] >= 1) {
                number[current]--;
            } else if (number[current] == 0) {

                // each location in the array has a lower bound on what value
                // it can hold, eg the last index can not hold a value
                // lower than zero
                while(number[current - 1] <= (number.length - 1 - (current-1))) {
                    current--;
                }

                number[current - 1]--;
                reset(number, current);
                current = number.length - 1;
            }
        }

        return "";
    }

    public void reset(int[] number, int current) {

        for (int x = current; x < number.length; x++) {
            number[x] = number[x-1] - 1;
        }
    }

    public int arrayToInt(int[] array) {
        String builtNumber = "";
        for (int nn : array) {
            builtNumber += Integer.toString(nn);
        }

        return Integer.parseInt(builtNumber);
    }

    public int formula(int n) {
        String reverse = "";
        int ascendant, descendant;
        char[] c = Integer.toString(n).toCharArray();

        Arrays.sort(c);
        ascendant = Integer.parseInt(new String(c));

        for (char cc : c ) {
            reverse = cc + reverse;
        }

        descendant = Integer.parseInt(reverse);
        return descendant - ascendant;
    }

    public boolean containsSameNumbers(int num1, int num2) {
        String n1 = Integer.toString(num1);
        String n2 = Integer.toString(num2);

        char[] c1 = n1.toCharArray();
        char[] c2 = n2.toCharArray();

        Arrays.sort(c1);
        Arrays.sort(c2);

        return Arrays.equals(c1, c2);
    }
}

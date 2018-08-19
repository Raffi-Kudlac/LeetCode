package medium.questions;

import helper.SolutionOutline;

public class GenerateLowerPalindrome extends SolutionOutline {

    public void runTest() {
        this.setClassName("GenerateLowerPalindrome");
        this.setDifficulty("Medium");

        int test = 1001;
        this.setInput(test);
        String answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public String solution(int n) {
        int nCopy = n - 1;
        int right = (int)(Math.log10(nCopy));
        int copyLength = right;
        int left = 0;

        while (left <= right) {

            if ((int)(Math.log10(nCopy)) != copyLength) {
                return solution((int) Math.pow(10, copyLength));
            }

            int leftNumb = getChar(nCopy, left);
            int rightNumb = getChar(nCopy, right);

            if (rightNumb > leftNumb) {
                nCopy -= Math.pow(10, left)*(rightNumb - leftNumb);
            } else if (leftNumb > rightNumb) {
                nCopy -= Math.pow(10, left)*(10 - (leftNumb - rightNumb));
            } else if (rightNumb == leftNumb) {
                left++;
                right--;
            }
        }

        return Integer.toString(nCopy);
    }


    public int getChar(int numb, int index) {

        String n = Integer.toString(numb);

        return Character.getNumericValue(n.charAt(index));
    }

}

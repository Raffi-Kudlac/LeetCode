package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;
import java.util.Stack;

public class ZerosToTheRight extends SolutionOutline {

    public void runTest() {
        this.setClassName("ZerosToTheRight");
        this.setDifficulty("Medium");

        int[] test = buildOrderedArray(20, 100);
        addInZeros(5, test);
        this.setInput(test);
        int[] answer = this.solution(test);
        this.setOutput(Arrays.toString(answer));
        this.printResult();
    }

    public void addInZeros(int n, int[] nums) {

        int index;
        int count = 0;
        while (count < n) {
            index = (int) (Math.random() * nums.length);
            if (nums[index] != 0) {
                nums[index] = 0;
                count++;
            }
        }
    }

    public int[] solution(int[] nums) {

        Stack<Integer> stack = new Stack<>();

        for (int x = 0; x < nums.length; x++) {
            if (nums[x] == 0) {
                stack.push(x);
            }
        }

        while (stack.size() > 0) {
            moveToTheEnd(stack.pop(), nums);
        }

        return nums;
    }

    public void moveToTheEnd(int start, int[] nums) {
        int x = start;
        while (x < nums.length - 1 && nums[x+1] != 0 ) {
            swap(x, x+1, nums);
            x++;
        }
    }

    public void swap(int index1, int index2, int[] nums) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

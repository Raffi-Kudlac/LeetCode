package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
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

        Queue<Integer> que = new LinkedList<>();

        int element;
        for (int x = 0; x < nums.length; x++) {
            element = nums[x];
            if (element == 0) {
                que.add(x);
            } else if (element != 0 && que.size() > 0) {
                swap(x, que.remove(), nums);
                que.add(x);
            }
        }

        return nums;
    }

    public void swap(int index1, int index2, int[] nums) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }
}

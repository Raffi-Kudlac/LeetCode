package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum extends SolutionOutline {

    public void runTest() {
        this.setClassName("FourSum");
        this.setDifficulty("Medium");

        int[] test = {1, 0, -1, 0, -2, 2};
        int target = 0;
        this.setInput(test);
        List<List<Integer>> answer = this.solution(test, target);
        this.setOutput(answer);
        this.printResult();
    }

    public List<List<Integer>> solution(int[] array, int target) {

        List<List<Integer>> answer = new ArrayList<>();
        Arrays.sort(array);

        for (int pA = 0; pA < array.length - 3; pA++) {
            if (pA > 0 && array[pA] == array[pA - 1]) {
                continue;
            }

            for (int pB = pA + 1; pB < array.length - 2; pB++) {
                if (pB > pA + 1 && array[pB] == array[pB - 1]) {
                    continue;
                }
                int sum1 = array[pA] + array[pB];
                int left = pB + 1;
                int right = array.length - 1;

                while (left < right) {

                    if (left > pB + 1 && array[left] == array[left - 1]) {
                        left++;
                        continue;
                    } else if (right < array.length - 1 && array[right] == array[right + 1]) {
                        right--;
                        continue;
                    }
                    int total = sum1 + array[left] + array[right];

                    if (total < target) {
                        left++;
                        continue;
                    } else if (total > target) {
                        right--;
                        continue;
                    } else {
                        List<Integer> entry = new ArrayList<>();
                        entry.add(array[pA]); entry.add(pB);
                        entry.add(array[left]); entry.add(array[right]);
                        answer.add(entry);
                        left++;
                        right--;
                    }
                }
            }
        }
        return answer;
    }
}

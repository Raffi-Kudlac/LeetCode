package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PairsWithSmallestSum extends SolutionOutline {

    public void runTest() {
        this.setClassName("");
        this.setDifficulty("Medium");

        int[] nums1 = {1,6, 9, 10};
        int[] nums2 = {4, 7, 9, 13};
        int k = 5;
        this.setInput("Array 1: " + Arrays.toString(nums1) +
                "\n\tArray 2: " + Arrays.toString(nums2) +
                "\n\tK: " + k);

        List<int[]> answer = this.solution(nums1, nums2, k);
        this.setOutput(buildAnswerString(answer));
        this.printResult();
    }

    public String buildAnswerString(List<int[]> answer) {
        String s = "";
        for (int[] part: answer) {
            s += Arrays.toString(part) + "\n\t";
        }
        return s;
    }

    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/description/
    public List<int[]> solution(int[] nums1, int[] nums2, int k) {

        List<int[]> answer = new ArrayList<>();
        int base = 0;
        // 0 - index of nums1, 1 - index of nums 2
        int[] nums1Root = {base, base +1};
        int[] nums2Root = {1 + base, base};
        answer.add(new int[] {nums1[base], nums2[base]});

        int upperBound = nums1[base+1] + nums2[base + 1];
        int val1Total, val1A, val1B, val2Total, val2A, val2B;
        while(answer.size() < k) {
            val1A = nums1[nums1Root[0]];
            val1B = nums2[nums1Root[1]];
            val1Total = val1A + val1B;

            val2A = nums1[nums2Root[0]];
            val2B = nums2[nums2Root[1]];
            val2Total = val2A + val2B;

            if (val1Total > upperBound && val2Total > upperBound) {
                base++;
                upperBound = nums1[base + 1] + nums2[base + 1];
                nums1Root = new int[] {base, base +1};
                nums2Root = new int[]{1 + base, base};
                answer.add(new int[] {nums1[base], nums2[base]});

            } else if (val1Total < val2Total) {
                answer.add(new int[]{val1A, val1B});
                nums1Root[1]++;
            }  else if (val2Total < val1Total) {
                answer.add(new int[]{val2A, val2B});
                nums2Root[0]++;
            } else {
                answer.add(new int[]{val1A, val1B});
                nums1Root[1]++;
                answer.add(new int[]{val2A, val2B});
                nums2Root[0]++;
            }
        }

        return answer;
    }
}

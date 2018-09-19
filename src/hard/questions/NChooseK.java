package hard.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.List;

public class NChooseK extends SolutionOutline {

    public void runTest() {
        this.setClassName("NChooseK");
        this.setDifficulty("Hard");

        int N = 5;
        int K = 2;
        this.setInput( N + " choose " + K);
        List<List<Integer>> answer = this.solution(N, K);
        this.setOutput(answer);
        this.printResult();
    }

    public List<List<Integer>> solution(int N, int K) {
        List<List<Integer>> answer = new ArrayList<>();

        // need a list of pointers to shift, needs to be K long
        List<Integer> pointers = new ArrayList<>();
        int[] nums = new int[N];

        for (int x = 0; x < N; x++) {
            nums[x] = x + 1;
        }

        for (int x = 0; x < K; x++) {
            pointers.add(x);
        }

        while (pointers.get(0) < N - K) {
            answer.add(buildEntry(pointers, nums));
            shiftPointers(pointers, pointers.size() - 1, nums.length - 1);
        }

        answer.add(buildEntry(pointers, nums));
        return answer;
    }

    // shift the last pointer over one, if it can not then shift th pointer that comes
    // after it
    public void shiftPointers(List<Integer> pointers, int index, int limit) {
        if (pointers.get(index) == limit){
            shiftPointers(pointers, index - 1, limit - 1);
        } else {
            pointers.set(index, pointers.get(index) + 1);
            resetPointers(pointers, index);
        }
    }

    // after one pointer shifts then all that come after it need to be reset
    public void resetPointers(List<Integer> pointers, int index) {

        int current = index + 1;
        while (current < pointers.size()) {
            pointers.set(current, pointers.get(current - 1) + 1);
            current++;
        }
    }

    // loop through the array of pointers and create an entry to add
    public List<Integer> buildEntry(List<Integer> pointers, int[] nums) {
        List<Integer> entry = new ArrayList<>();

        for (int p : pointers) {
            entry.add(nums[p]);
        }

        return entry;
    }
}

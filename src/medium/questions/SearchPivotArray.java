package medium.questions;

import helper.SolutionOutline;

public class SearchPivotArray extends SolutionOutline {


    public void runTest() {
        this.setClassName("SearchPivotArray");
        this.setDifficulty("Medium");

        int[] test = {4,5,6,7,0,1,2};
        this.setInput(test);
        int answer = this.solution(test, 2);
        this.setOutput(answer);
        this.printResult();
    }

    public int solution(int[] array, int target) {

        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low <= high) {

            mid = (low + high) / 2;
            if (array[mid] == target) {
                return mid;
            }

            if (array[low] <= array[mid]) {

                if (target >= array[low] && target < array[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > array[mid] && target <= array[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }

        return -1;
    }
}

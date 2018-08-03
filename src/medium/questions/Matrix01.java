package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;

public class Matrix01 extends SolutionOutline {

    public void runTest() {
        this.setClassName("Matrix01");
        this.setDifficulty("Medium");

        int[][] test = buildMatrix(7, 5);
        this.setInput(test);
        int[][] answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    private int[][] buildMatrix(int size, int zeros) {
        int[][] matrix = new int[size][size];

        for (int[] arr: matrix) {
            Arrays.fill(arr, 1);
        }

        int x,y;
        while(zeros > 0) {
            x = (int) (Math.random()*size);
            y = (int) (Math.random()*size);

            if (matrix[x][y] != 0) {
                matrix[x][y] = 0;
                zeros--;
            }
        }

        return matrix;
    }

    public int[][] solution(int[][] matrix) {

        int[][] answer = new int[matrix.length][matrix[0].length];

        for (int[] arr: matrix) {
            Arrays.fill(arr, Integer.MAX_VALUE - 1);
        }

        // pass from top left to bottom right
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {

                if (matrix[x][y] == 0) {
                    answer[x][y] = 0;
                    continue;
                }

                if (y + 1 < matrix[x].length) {
                    answer[x][y] = Math.min(answer[x][y], answer[x][y + 1] + 1);
                }

                if (x + 1 < matrix.length) {
                    answer[x][y] = Math.min(answer[x][y], answer[x + 1][y] + 1);
                }
            }
        }

        // pass from bottom right to top left
        for (int x = matrix.length - 1; x >= 0; x--) {
            for (int y = matrix[x].length; y >= 0; y--) {

                if (matrix[x][y] == 0) {
                    answer[x][y] = 0;
                    continue;
                }

                if (y - 1 >= 0) {
                    answer[x][y] = Math.min(answer[x][y], answer[x][y - 1] + 1);
                }

                if (x - 1 >= 0) {
                    answer[x][y] = Math.min(answer[x][y], answer[x - 1][y] + 1);
                }
            }
        }

        return answer;
    }
}

package medium.questions;

import helper.SolutionOutline;

import java.util.Arrays;

public class OrderOfLargestPlusSign extends SolutionOutline {
    public final int LEFT = 0;
    public final int RIGHT = 1;
    public final int TOP = 2;
    public final int BOTTOM = 3;

    public void runTest() {
        this.setClassName("OrderOfLargestPlusSign");
        this.setDifficulty("Medium");

        int size = 5;
        int[][] mines = buildMines();

        this.setInput("Size: " + size + "\n mines: \n\t" +
                TwoDIntArrayToString(mines));
        int[][] board = buildBoard(size, mines);
        int answer = this.solution(board);
        this.setOutput(answer);
        this.printResult();
    }

    public int[][] buildMines(){
        int[][] mines = {{3,0},{3,3}};
        return mines;
    }

    // https://leetcode.com/problems/largest-plus-sign/description/
    public int solution(int[][] board) {
        dpUnit[][] dp = new dpUnit[board.length][board.length];

        // pass from top left to bottom right
        for (int x = 0; x < dp.length; x++) {
            for (int y = 0; y < dp.length ; y++) {

                if (board[x][y] == 0) {
                    dp[x][y] = new dpUnit(0);
                    continue;
                }
                dp[x][y] = new dpUnit(1);
                dp[x][y].setDirection(TOP,
                        getValFromDP(x - 1, y, dp, TOP) + 1);
                dp[x][y].setDirection(LEFT,
                        getValFromDP(x,y - 1, dp, LEFT) + 1);
            }
        }

        // pass from bottom right to top left
        int max = 0;
        for (int x = dp.length - 1; x >= 0; x--) {
            for (int y = dp.length - 1; y >=0; y--) {

                if (board[x][y] == 0) {
                    continue;
                }

                dp[x][y].setDirection(RIGHT,
                        getValFromDP(x, y + 1, dp, RIGHT) + 1);
                dp[x][y].setDirection(BOTTOM,
                        getValFromDP(x + 1, y, dp, BOTTOM) + 1);

                max = Math.max(max, dp[x][y].getMin());
            }
        }
        return max;
    }

    public int getValFromDP(int x , int y, dpUnit[][] dp, int index) {
        try{
            return dp[x][y].getDirection(index);
        }catch (Exception e) {
            return 0;
        }
    }

    public int[][] buildBoard(int size, int[][] mines) {
        int[][] board = new int[size][size];

        for (int[] arr : board) {
            Arrays.fill(arr, 1);
        }

        for(int[] arr : mines) {
            board[arr[0]][arr[1]] = 0;
        }

        return board;
    }

    private class dpUnit {
        int[] directions = new int[4];

        dpUnit(int val) {
            Arrays.fill(directions, val);
        }

        public int getDirection(int index) {
            return directions[index];
        }

        public void setDirection(int index, int val) {
            directions[index] = val;
        }

        public int getMin() {
            int min = directions[0];

            for (int num : directions) {
                min = Math.min(min, num);
            }

            return min;
        }
    }
}

package medium.questions;

import helper.SolutionOutline;

public class ArithmeticSlices extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("ArithmeticSlices");
		this.setDifficulty("Medium");
		
		int[] test = {1,2,3,4,5,6};		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(int[] A) {
		
		int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
	}	
}

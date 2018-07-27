package medium.questions;

import helper.SolutionOutline;

public class DecodeWays extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("DecodeWays");
		this.setDifficulty("Medium");
		
		String test = "22617";		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/decode-ways/description/
	public int solution(String s) {
		
		int[] dp = new int[s.length() + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		int last = 0;
		int secondLast = 0;
		for (int x = 2; x <= s.length(); x++) {
			last = Integer.parseInt(s.substring(x - 1, x));
			secondLast = Integer.parseInt(s.substring(x - 2, x));
			
			if (last != 0) {
				dp[x] += dp[x -1];
			}
			
			if (secondLast >= 10 && secondLast <= 26) {
				dp[x] += dp[x-2];
			}
		}
				
		return dp[s.length()];
	}	
}

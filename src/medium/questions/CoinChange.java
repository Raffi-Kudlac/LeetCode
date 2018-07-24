package medium.questions;
import java.util.Arrays;

import helper.SolutionOutline;

public class CoinChange extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("CoinChange");
		this.setDifficulty("Medium");
		
		int[] coins = {186,419,83,408};
		int target = 6249;
		
		this.setInput("Coins: " + Arrays.toString(coins) + ", " + target);
		int answer = this.solution(coins, target);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(int[] coins, int amount) {
		
		
		int[] dp = new int[amount+1];		
		Arrays.fill(dp, -1);		
		dp[0] = 0;
		int sum = 1;
		
		while (sum < amount+1) {			
			
			for (int coin : coins) {
				
				if (sum >= coin && dp[sum-coin] != -1) {
					
					if (dp[sum] == -1) {
						dp[sum] = dp[sum-coin] + 1;
					} else {
						dp[sum] = Math.min(dp[sum], dp[sum-coin] + 1);
					}					
				}				
			}
			sum++;
		}		
		
		return dp[amount];
	}		
}

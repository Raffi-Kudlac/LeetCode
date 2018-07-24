package easy.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class BestTimeToBuyAndSellStocks extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("BestTimeToBuyAndSellStocks");
		this.setDifficulty("Easy");
		
		int[] test = {1,2,3,0,2};		
		this.setInput(Arrays.toString(test));

		int answer = this.withCoolDown(test);
		this.setOutput(Integer.toString(answer));
		this.printResult();
	} 
	
	// only allowed one transaction
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/	
	public int solutionV1(int[] prices) {
		
		int maxProfit = 0;
		int min = Integer.MAX_VALUE;
		
		for (int n : prices) {
			if (n > min) {
				maxProfit = Math.max(maxProfit, n - min);
			} else {
				min = n;
			}
		}
		
		return maxProfit;
	}	

	
	// allowed multiple transactions
	// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
	public int solutionV2(int[] prices) {
		
		int maxProfit = 0;		
		int diff;
		
		for (int x = 1; x < prices.length; x++) {
			diff = prices[x] - prices[x-1];
			if (diff > 0) {
				maxProfit += diff;
			}						
		}
		
		return maxProfit;
	}

	public int withCoolDown(int[] prices) {
		int sell = 0, sell1 = 0, sell2 = 0;
		int buy = Integer.MIN_VALUE, buy1 = Integer.MIN_VALUE;
		
		for (int price : prices) {
			buy = Math.max(sell2 - price, buy1);
			sell = Math.max(buy1 + price, sell1);
			
			buy1 = buy;
			sell2 = sell1;
			sell1 = sell;			
		}
		
		
		return sell;
	}
	

	public int solutionV3(int[] prices, int transactions) {
		
		int[][] dp = new int[transactions + 1][prices.length];
		
		for (int day = 0; day < prices.length; day++) {
			dp[0][day] = 0;
		}
		
		for (int trans = 0; trans <= transactions; trans++) {
			dp[trans][0] = 0;
		}
				
		int maxDay;
		for (int trans = 1; trans <= transactions; trans++) {
			
			maxDay = Integer.MIN_VALUE;
			for (int day = 1; day < prices.length; day++) {
				maxDay = Math.max(maxDay, dp[trans - 1][day - 1] - prices[day - 1]);
				dp[trans][day] = Math.max(dp[trans][day -1], prices[day] + maxDay);
			}
		}
		
		return dp[transactions][prices.length - 1];
	}
}

package medium.questions;

import helper.SolutionOutline;

public class DailyTemperatures extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("DailyTemperatures");
		this.setDifficulty("Medium");
		
		//int[] test = {73, 74, 75, 71, 69, 72, 76, 73};
		int[] test = {34,80,80,34,34,80,80,80,80,34};
		this.setInput(test);
		int[] answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/daily-temperatures/description/
	public int[] solution(int[] temperatures) {
		
		int[] answer = new int[temperatures.length];		
		answer[temperatures.length - 1] = 0;
		int right = temperatures.length - 1;				
		
		for (int x = temperatures.length - 2; x >= 0; x--) {
			
			while (true) {
				if (temperatures[x] < temperatures[right]) {
					answer[x] = right - x;
					right = x;					
					break;
				} else if (temperatures[x] >= temperatures[right] && answer[right] == 0) {
					answer[x] = 0;
					right = x;
					break;
				} else {
					right = answer[right] + right;					
				}
			}
		}
				
		return answer;
	}		

}

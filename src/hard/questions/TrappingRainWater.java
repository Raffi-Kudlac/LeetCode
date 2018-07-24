package hard.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class TrappingRainWater extends SolutionOutline {

	public void runTest() {
		this.setClassName("TrappingRainWater");
		this.setDifficulty("Hard");
		
		//int[] test = {0,1,0,2,1,0,1,3,2,1,2,1};
		int[] test = {5,4,1,2};
		this.setInput(Arrays.toString(test));
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(int[] heights) {
		
		int answer = 0;
		int smallMax = 0;
		int smallMaxIndex = 0;
		int left;
		int right = 0;
		for (int leftIndex = 0; leftIndex < heights.length - 1; leftIndex++) {
			
			left = heights[leftIndex];
			int rightIndex;
			smallMax = 0;
			smallMaxIndex = 0;
			
			for (rightIndex = leftIndex + 1; rightIndex < heights.length; rightIndex++) {
				right = heights[rightIndex];				
				
				if (right >= left) {
					smallMaxIndex = rightIndex;
					break;
				} else if (right >= smallMax){					
					smallMaxIndex = rightIndex;
					smallMax = right;										
				}								
			}			
			
			answer += calculateValley(smallMaxIndex, leftIndex, heights);
			leftIndex = smallMaxIndex - 1;							
		}
				
		return answer;
	}
	
	public int calculateValley(int rightIndex, int leftIndex, int[] heights) {
		
		int ht = Math.min(heights[rightIndex], heights[leftIndex]);
		int width = rightIndex - leftIndex - 1;		
		int maxWater = ht*width;
		
		for (int x = leftIndex + 1; x < rightIndex; x++) {
			maxWater -= heights[x];
		}
		
		return Math.max(0, maxWater);
		
	}
	
}

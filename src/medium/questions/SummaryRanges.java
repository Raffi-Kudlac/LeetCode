package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.SolutionOutline;

public class SummaryRanges extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("SummaryRanges");
		this.setDifficulty("Medium");
		
		//int[] test = {0,1,2,4,5,7};
		int[] test = {0,2,3,4,6,8,9};
		this.setInput(test);
		
		List<String> answer = this.solution(test);
		this.setOutput(Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	// https://leetcode.com/problems/summary-ranges/description/
	public List<String> solution(int[] array) {
		
		List<String> answer = new ArrayList<>();
		int start = 0;
		int end = 0;
		int next;
		String arrow = " -> ";		
		
		for (int index = 0; index < array.length - 1; index++) {
			next = index + 1;
			if (array[end] + 1 == array[next]) {
				end = next;
			} else {				
				if (start == end) {
					answer.add(array[start] + "");
				} else {
					answer.add(array[start] + arrow + array[end]);
				}
				
				start = next;
				end = next;
			}			
		}
		
		if (start == end) {
			answer.add(array[start] + "");
		} else {
			answer.add(array[start] + arrow + array[end]);
		}
				
		return answer;
	}		
}

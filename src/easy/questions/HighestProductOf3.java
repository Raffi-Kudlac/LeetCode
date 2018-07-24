package easy.questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import helper.SolutionOutline;

public class HighestProductOf3 extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("HighestProductOf3");
		this.setDifficulty("Easy");
		
		int[] test = {-10, -30, 0, 1, 4, 9, 8};		
		this.setInput(Arrays.toString(test));
		int answer = this.solution(test);
		this.setOutput(Integer.toString(answer));
		this.printResult();
	} 
	
	public int solution(int[] nums) {
				
		Comparator<Integer> c = new Comparator<Integer>() {			
			public int compare(Integer n1, Integer n2) {
				if (n1 > n2) {
					return -1;
				} else if (n1 < n2) {
					return 1;
				}			
				return 0;
			}
		};
		PriorityQueue<Integer> highest = new PriorityQueue<Integer>();
		PriorityQueue<Integer> lowest = new PriorityQueue<Integer>(c);		
		for (int n : nums) {
			highest.add(n);
			lowest.add(n);
			
			if (highest.size() > 3) {
				highest.remove();
			}
			
			if (lowest.size() > 2) {
				lowest.remove();
			}
		}
		
		Integer[] low = new Integer[2];
		lowest.toArray(low);
		Integer[] high = new Integer[3]; 
		highest.toArray(high);		
		
		int neg = low[0] * low[1];
		int posA = high[0] * high[1];
		int posB = high[1] * high[2];
		int answer;
		if (neg > 0 && low[0] < 0) {
			if (neg > posB) {
				answer = neg * high[1];
			} else if (neg > posA) {
				answer = neg * high[2];
			} else {
				answer = posA * high[2];
			}
		} else {
			answer = posA * high[2];
		}		
		return answer;
	}	
}

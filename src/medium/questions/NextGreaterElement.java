package medium.questions;

import java.util.HashMap;
import java.util.Stack;

import helper.SolutionOutline;

public class NextGreaterElement extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("NextGreaterElement");
		this.setDifficulty("Medium");
		
		// int[] test = {1,3,5,4,2};
		int[] test = {1,2,1};
		int[] subTest = {4,1,2};
		this.setInput(test);
		int[] answer = this.solution2(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/next-greater-element-i/description/
	public int[] solution1(int[] array, int[] subset) {
		int[] answer = new int[subset.length];
		
		HashMap<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		
		for (int n : array) {
			while(!stack.isEmpty() && stack.peek() < n) {
				map.put(stack.pop(), n);
			}
			stack.push(n);			
		}
		
		for (int index = 0; index < subset.length; index++) {
			answer[index] = map.getOrDefault(subset[index], -1);
		}
		
		
		return answer;
	}

	// https://leetcode.com/problems/next-greater-element-ii/description/
	public int[] solution2(int[] array) {
		int[] answer = new int[array.length];		
		HashMap<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		
		int index = 0;
		int n;
		int pass = 0;
		while (map.keySet().size() < array.length - 1 && pass < 2) {
			if (index == array.length) {
				index = 0;
				pass++;
			}
			
			n = array[index];			
			
			while(!stack.isEmpty() && array[stack.peek()] < n) {
				
				if (!map.containsKey(stack.peek())) {
					map.put(stack.pop(), n);
				} else {
					stack.pop();
				}				
			}
			stack.push(index);
			index++;
		}
		
		for (int x = 0; x < array.length; x++) {
			answer[x] = map.getOrDefault(x, -1);
		}
		
		return answer;
	}
}

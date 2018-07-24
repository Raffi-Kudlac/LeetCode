package medium.questions;

import java.util.ArrayList;

import helper.SolutionOutline;

public class Fibonacci extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("Fibonacci");
		this.setDifficulty("Medium");
		
		int test = 50;		
		this.setInput(Integer.toString(test));
		long answer = this.fib(test);
		this.setOutput(Long.toString(answer));
		this.printResult();
	} 
	
	public long fib(int n) {
		if (n == 1 || n == 2) {
			return 1;
		}
		
		return fib(n-2) + fib(n-1);
	}
	
	public int solution(int n) {
		ArrayList<Integer> stack = new ArrayList<Integer>();
		stack.add(0);
		stack.add(1);
		
		for (int x = 2; x <= n; x++) {
			int val = stack.get(0) + stack.get(1);
			stack.remove(0);
			stack.add(val);
		}
		
		return stack.get(1);
	}			
}

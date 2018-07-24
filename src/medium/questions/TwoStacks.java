package medium.questions;

import java.util.Arrays;
import java.util.Stack;

import helper.SolutionOutline;

public class TwoStacks extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("TwoStacks");
		this.setDifficulty("Medium");
		
		Integer[] test = buildTest();		
		this.setInput(Arrays.toString(test));
		String answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}
	
	public Integer[] buildTest() {
		Integer[] test = new Integer[10];
		
		test[0] = 4;
		test[1] = null;
		test[2] = 67;
		test[3] = 100;
		test[4] = 101;
		test[5] = null;
		test[6] = 5;
		test[7] = 9;
		test[8] = null;
		test[9] = 34;
		return test;
	};
	
	public String solution(Integer[] test) {
		
		queue q = new queue();
		
		for (Integer i: test) {
			if (i !=  null) {
				q.enqueue(i);
			}
			else {
				System.out.println(q.dequeue() + " was dequeued");
			}
		}
		return q.toString();
	}
	
	
	public class queue {
		Stack<Integer> main = new Stack<Integer>();
		Stack<Integer> tempMemory = new Stack<Integer>();
		
		queue() {
			
		}
		
		public String toString() {
			String answer = "";
			int firstSize = main.size();
			for (int n = 0; n < firstSize; n++) {
				answer += main.pop() + ", ";
			}
			
			return answer;
		}
		
		public void enqueue(int n) {
			main.add(n);
		}
		
		public int dequeue() {
			int value;
			int firstSize = main.size();
			for (int n = 0; n < firstSize - 1; n++) {
				tempMemory.add(main.pop());
			}
			
			value = main.pop();
			firstSize = tempMemory.size();
			for (int n = 0; n < firstSize; n++) {
				main.add(tempMemory.pop());
			}
			
			return value;			
		}
	}
}

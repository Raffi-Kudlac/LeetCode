package easy.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import helper.SolutionOutline;

public class TedEdNumberCode extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("TedEdNumberCode");
		this.setDifficulty("Easy");
		
		String test = "No Input required";		
		this.setInput(test);
		List<Integer> answer = this.solution(new ArrayList<Integer>(), 0);
		this.setOutput(Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	public List<Integer> solution(List<Integer> path, int current) {
		
		if (current > 60 || path.contains(current)) {
			return null;
		}
		
		path.add(current);
		if (checkDoneCondition(path)) {
			return path;
		}
		List<Integer> temp;
		temp = solution(new ArrayList<Integer>(path), current + 7);
		
		if (temp != null) {
			return temp;
		}
		
		double d = Math.sqrt(current);				
		if (d % 1 == 0 && d != 0) {
			temp = solution(new ArrayList<Integer>(path), (int) d);
		}
		
		if (temp != null) {
			return temp;
		}
		
		temp = solution(new ArrayList<Integer>(path), current + 5);
		if (temp != null) {
			return temp;
		}
		
		return temp;
	}
	
	public boolean checkDoneCondition(List<Integer> path) {
		
		Stack<Integer> st = new Stack<Integer>();
		st.add(14);
		st.add(10);
		st.add(2);
		
		for (Integer i: path) {
			if (st.peek() == i) {
				st.pop();
				if (st.size() == 0) {
					return true;
				}
			}
		}
		
		return false;
	}
}

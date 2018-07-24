package medium.questions;

import java.util.Stack;

import helper.SolutionOutline;

public class Brackets extends SolutionOutline {

	
	public void runTest() {
		this.setClassName("Brackets");
		this.setDifficulty("Medium");
		
		//String test = "{ [ ] ( ) }";	
		String test = "{ [ ( ] ) }";
		this.setInput(test);
		boolean answer = this.solution(test);
		this.setOutput(Boolean.toString(answer));
		this.printResult();
	} 
	
	public boolean solution(String s) {
		
		Stack<Character> stack = new Stack<Character>();
		
		char current;
		for (int x = 0; x < s.length(); x++) {
			current = s.charAt(x);
			if (current == '[' || current == '{' || current == '(') {
				stack.add(current);
			} else if (current == ']') {
				if (stack.pop() != '[') {
					return false;
				}
			} else if (current == '}') {
				if (stack.pop() != '{') {
					return false;
				}
			} else if (current == ')') {
				if (stack.pop() != '(') {
					return false;
				}
			}
		}
		
		return true;
	}	

}

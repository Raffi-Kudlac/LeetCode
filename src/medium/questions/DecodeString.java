package medium.questions;

import java.util.Stack;

import helper.SolutionOutline;

public class DecodeString extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("DecodeString");
		this.setDifficulty("Medium");
		
		String test = "2[abc]3[cd]ef";		
		this.setInput(test);
		String answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public String solution(String s) {
		Stack<String> sections = new Stack<String>();
		Stack<Integer> counts = new Stack<Integer>();		
		String answer = "";
		
		int index = 0;
		char current;
		while (index < s.length()) {
			
			current = s.charAt(index);
			
			if (Character.isDigit(current)) {
				String digit = "";
				while (Character.isDigit(current)) {
					digit += current;
					index++;
					current = s.charAt(index);
				}
				counts.push(Integer.parseInt(digit));
				
			} else if (current == '[') {
				sections.push(answer);
				answer = "";
				index++;
			} else if (current == ']') {
				String section = sections.pop();
				int n = counts.pop();
				String temp = "";
				
				for (int x = 0; x < n; x++) {
					temp += answer;
				}
				answer = section + temp;
				index++;
			} else {
				answer += current;
				index++;
			}
		}
		
		return answer;
	}
}

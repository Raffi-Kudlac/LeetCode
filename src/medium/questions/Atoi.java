package medium.questions;

import helper.SolutionOutline;

public class Atoi extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("Atoi");
		this.setDifficulty("Medium");
		
		String test = "-91283472332  ";		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(String test) {
		
		test = test.trim();
		test = test.toLowerCase();
		char first = test.charAt(0);
		
		if (first == '-' || first == '+') {
			test = test.substring(1, test.length());
		} else {
			first = '@';
		}
		
		String answer = "";
		char current;
		for (int x = 0; x < test.length(); x++) {
			current = test.charAt(x);
			
			if (current > '9' || current < '0') {
				break;
			}
			
			answer = answer + current;			
		}
		
		if (answer.length() == 0) {
			return 0;
		}
		
		if (first == '-') {
			answer = first + answer;
		}
		
		long val = Long.parseLong(answer);
		int submit;		
		if (val > Integer.MAX_VALUE) {
			submit = Integer.MAX_VALUE;
		} else if (val < Integer.MIN_VALUE) {
			submit = Integer.MIN_VALUE;
		} else {
			submit = (int) val;
		}
		return submit;
	}		
}

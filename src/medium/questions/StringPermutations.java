package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;

import helper.SolutionOutline;

public class StringPermutations extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("StringPermutations");
		this.setDifficulty("Medium");
		
		String test = "cake";		
		this.setInput(test);
		String answer = Arrays.toString(this.solution(test).toArray());
		this.setOutput(answer);
		this.printResult();
	} 
	
	public ArrayList<String> solution(String s) {
		
		if (s.length() == 1) {
			ArrayList<String> combos = new ArrayList<String>();
			combos.add(s);
			return combos;
		}
		
		ArrayList<String> combos = solution(s.substring(1, s.length()));
		ArrayList<String>  answer = new ArrayList<String>();
		// first character
		String target = s.substring(0, 1);
		String current;
		for (int comboIndex = 0; comboIndex < combos.size(); comboIndex++) {
			current = combos.get(comboIndex);
			
			for (int x = 0; x <= current.length(); x++) {
				answer.add(current.substring(0, x) + target + current.substring(x));
			}
		}
		
		
		return answer;
	}		
}

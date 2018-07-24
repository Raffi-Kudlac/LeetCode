package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class LetterCombinations extends SolutionOutline {

	public void runTest() {
		this.setClassName("LetterCombinations");
		this.setDifficulty("Medium");
		
		String test = "249";		
		this.setInput(test);
		List<String> answer = this.solution(test);
		this.setOutput(Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	public List<String> solution(String number) {
		
		HashMap<String, String> map = new HashMap<String, String>();
		ArrayList<String> answer = new ArrayList<String>();
		
		map.put("2", "abc");
		map.put("3", "def");
		map.put("4", "ghi");
		map.put("5", "jkl");
		map.put("6", "mno");
		map.put("7", "pqrs");
		map.put("8", "tuv");
		map.put("9", "wxyz");
		
		Combo[] list = new Combo[number.length()];
		
		for (int x = number.length() - 1; x >= 0; x--) {
			String s = number.substring(x, x + 1);
			
			if (x == number.length() -1) {
				list[x] = new Combo(map.get(s), null, x);
			} else {
				list[x] = new Combo(map.get(s), list[x+1], x);
			}			
		}
				
		StringBuilder entry = buildEntry(list);
		while(list[list.length -1].reset == false) {
						
			answer.add(entry.toString());
			list[0].incrementPos(entry);			
		}		
		
		return answer;
	}
	
	public StringBuilder buildEntry(Combo[] list) {
		String entry = "";
		
		for (Combo c : list) {
			entry += c.getEntry();
		}
		
		return new StringBuilder(entry);
	}	
	
	public class Combo {
		String chars;
		int pos;
		boolean reset = false;
		Combo next;
		int masterPos;
		
		Combo(String c, Combo n, int mp) {
			chars = c;
			pos = 0;
			next = n;
			masterPos = mp;
		}
		
		public char getEntry() {
			return chars.charAt(pos);
		}
		
		public void incrementPos(StringBuilder entry) {
			
			pos++;			
			
			if (pos >= chars.length()) {
				pos = 0;				
				reset = true;
				if (next != null) {
					next.incrementPos(entry);
				}				
			}
			entry.setCharAt(masterPos, getEntry());
		}		
	}
}

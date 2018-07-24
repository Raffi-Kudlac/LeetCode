package medium.questions;

import java.util.ArrayList;
import java.util.List;

import helper.SolutionOutline;

public class WordBreak extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("WordBreak");
		this.setDifficulty("Medium");
		
		//String test = "leetcode";
		String test = "peachapplesause";
		this.setInput(test);
		boolean answer = this.solution(buildDictionary(), test);
		this.setOutput(Boolean.toString(answer));
		this.printResult();
	}
	 
	public List<String> buildDictionary() {
		ArrayList<String> dict = new ArrayList<String>();
		
//		dict.add("leet");
//		dict.add("cod");
		
		dict.add("apple");
		dict.add("applesause");
		dict.add("peach");
		return dict;
	}
	
	public boolean solution(List<String> dict, String s) {
		
		String current = "";
		List<String> matches = new ArrayList<String>(dict);		
		
		for (int x = 0; x < s.length(); x++) {
			current += s.charAt(x);
			matches = getMatches(matches, current);
			
			if (matches.size() == 0) {
				return false;
			} else if (x == s.length() -1 && !matches.contains(current)) {
				return false;
			}
			
			if (matches.size() == 1 && matches.contains(current)) {
				current = "";
				matches.clear();
				matches.addAll(dict);
			} else if (matches.size() > 1 && matches.contains(current)) {
				String bestMatch = checkAllPossibles(matches, x - current.length() + 1, s);				
				x = (x - current.length()) + bestMatch.length();
				current = "";
				matches.clear();
				matches.addAll(dict);
			}			
		}
				
		return true;
	}
	
	public String checkAllPossibles(List<String> matches, int index, String s) {
		String bestMatch = "";
		String partial;
		int length;
		
		for (String m : matches) {
			length = m.length();
			
			try {
				partial = s.substring(index, index + length);
				if (partial.equals(m) && length > bestMatch.length()) {
					bestMatch = m;
				}
			} catch (Exception e) {
				// skip it and go onto the next one
			}			
		}
		
		return bestMatch;
	}
	
	public List<String> getMatches(List<String> dict, String target) {
		
		ArrayList<String> matches = new ArrayList<String>();
		
		for (String s: dict) {			
			if (s.startsWith(target)) {
				matches.add(s);
			}
		}
		return matches;
	}
}

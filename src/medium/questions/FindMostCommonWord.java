package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class FindMostCommonWord extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("FindMostCommonWord");
		this.setDifficulty("Medium");
		
		String test = "Jack'''s pen wasn't the best pen";
		List<String> exclude = new ArrayList<String>();
		this.setInput(test);
		List<String> answer = this.solution(test, exclude);
		this.setOutput(answer.toString());
		this.printResult();
	} 
	
	public List<String> solution(String sentence, List<String> exclude) {
		
		
		HashMap<String, Boolean> newExclude = new HashMap<String, Boolean>();
		HashMap<String, Integer> count = new HashMap<String, Integer>();
		
		for (String s: exclude) {
			newExclude.put(s, true);
		}
		
		String[] words = sentence.split(" ");
		
		for (String word: words) {
			
			//List<String> innerWords = getInnerWords(word);
			String[] innerWords = regex(word);
			
			for (String partial: innerWords) {
				
				if (!newExclude.containsKey(partial)) {
					if (count.containsKey(partial)) {
						count.put(partial, count.get(partial) + 1);
					} else {
						count.put(partial, 1);
					}
				}
			}
		}
		
		ArrayList<String> answer = new ArrayList<String>();
		int max = 0;
		int elementCount;
		for (String key: count.keySet()) {
			elementCount = count.get(key);
			
			if (elementCount > max) {
				answer.clear();
				answer.add(key);
				max = elementCount;
			} else if (elementCount == max) {
				answer.add(key);
			}			
		}
		
		return answer;
	}		
	
	public String[] regex(String word) {
		return word.split("\\P{Alpha}+");
	}
	
	public List<String> getInnerWords(String word) {
		
		ArrayList<String> words = new ArrayList<String>();
		
		String current = "";
		word = word.toLowerCase();
		for (char c : word.toCharArray()) {
			if (c >= 'a' && c <= 'z') {
				current = current + c;
			} else if (current.length() != 0) {				
				words.add(current);
				current = "";
			}
		}
		
		if (current.length() != 0) {
			words.add(current);
		} 
		
		return words;
		
	}
}

package easy.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class FindAnagrams extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("FindAnagrams");
		this.setDifficulty("Medium");
		
		String test = "cbaebabacd"; // 10
		String anagram = "abc"; // 3
		this.setInput("Test String: " + test + ". Anagram: " + anagram);
		List<Integer> answer = this.solution(test, anagram);
		this.setOutput(Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	public List<Integer> solution(String test, String anagram) {
				
		List<Integer> answer = new ArrayList<Integer>();
        HashMap<Character, Integer> pCounter = new HashMap<Character, Integer>();
        char[] charArray = anagram.toCharArray();
        for (char c : charArray) {
            Character cObject = c;
            if (pCounter.containsKey(cObject)) {
            	
                pCounter.put(cObject, pCounter.get(cObject) + 1);                
            } else {
                pCounter.put(cObject, 1);
            }            
        }                   
        
        for(int index = 0; index < (test.length() - anagram.length()); index++) {
            
            if (check(test.substring(index, index + anagram.length()), anagram, new HashMap<Character, Integer>(pCounter))) {
               answer.add(index);
            }
            
        }
        
        return answer;        
    }
	
	public HashMap<Character, Integer> buildCharCounter(String anagram) {
		HashMap<Character, Integer> pCounter = new HashMap<Character, Integer>();
        char[] charArray = anagram.toCharArray();
        for (char c : charArray) {
            Character cObject = c;
            if (pCounter.containsKey(cObject)) {            	
                pCounter.put(cObject, pCounter.get(cObject) + 1);                
            } else {
                pCounter.put(cObject, 1);
            }            
        } 
        
        return pCounter;
	}
	
	public boolean check(String s, String p, HashMap<Character, Integer> pCount) {
        Character c;                    
        for (int x = 0; x < s.length(); x++) {
            c = s.charAt(x);

            if (pCount.containsKey(c)) {
                int counter = pCount.get(c);

                if (counter <= 0) {
                    return false;
                } else {
                    pCount.put(c, counter-1);
                }
            } else {
                return false;
            }
        }

        return true;
    }
}

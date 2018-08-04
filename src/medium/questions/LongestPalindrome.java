package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class LongestPalindrome extends SolutionOutline {
	
	/**
	 * Idea (solution 2)
	 * 		check if each index in the original string is the middle index of a palindrome.
	 * 		Need to do two checks, 1 for a palindrome with an odd and even number of characters
	 */	
	
	
	public void runTest() {
		this.setClassName("LongestPalindrome");
		this.setDifficulty("Medium");
		
		String test = "babad";		
		this.setInput(test);
		String answer = this.solution2(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	
	// https://leetcode.com/problems/longest-palindromic-substring/description/
	public String solution2(String s) {
		
		String p1, p2, answer = "";		
		for (int index = 0; index < s.length(); index++) {
			p1 = checkPalindrome(s, index, index + 1);
			p2 = checkPalindrome(s, index, index);
			
			answer = answer.length() < p2.length() ? p2 : answer;
			answer = answer.length() < p1.length() ? p1 : answer;
		}
		
		return answer;
	}
	
	public String checkPalindrome(String s, int l, int r) {
		int left = l, right = r;
		String answer = "";
		while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
			answer = s.substring(left, right + 1);
			left--;
			right++;			
		}
		
		return answer;
	}
	
	public String solution(String s) {
		
		HashMap<Character, List<Integer>> bucket = new HashMap<>();
		String answer = "";
		
		Character c;
		List<Integer> entry;
		for (int index = 0; index < s.length(); index++) {
			c = s.charAt(index);
			if (bucket.containsKey(c)) {
				bucket.get(c).add(index);
			} else {
				entry = new ArrayList<Integer>();
				entry.add(index);
				bucket.put(c, entry);
			}
		}
		
		String sub;
		for (Character key: bucket.keySet()) {
			entry = bucket.get(key);
			
			for (int left = 0; left < entry.size(); left++) {
				for (int right = left; right < entry.size(); right++) {
					
					sub = s.substring(entry.get(left), entry.get(right) + 1);
					if (isPalindrome(sub) && sub.length() > answer.length()) {
						answer = sub;
					}
				}
			}
		}
		
		return answer;
	}
	
	public boolean isPalindrome(String s) {
		
		for (int x = 0; x < s.length()/2; x++) {
			if (s.charAt(x) != s.charAt(s.length() - x - 1)) {
				return false;
			}
		}
		return true;
	}
}

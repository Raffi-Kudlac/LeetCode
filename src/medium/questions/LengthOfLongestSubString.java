package medium.questions;

import java.util.HashMap;

import helper.SolutionOutline;

public class LengthOfLongestSubString extends SolutionOutline {
	
	/**
	 * 	Idea
	 * 		See solution 3 https://leetcode.com/problems/longest-substring-without-repeating-characters/solution/
	 */
	
	public void runTest() {
		this.setClassName("LengthOfLongestSubString");
		this.setDifficulty("Medium");
		
		String test = "abcabcbb";		
		this.setInput(test);
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
	public int solution(String s) {
		
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int left = 0, right = 0, max = 0;
		
		while (right < s.length()) {
			
			if (map.containsKey(s.charAt(right))) {
				left = Math.max(left, map.get(s.charAt(right)));
			}
			
			max = Math.max(max, right - left + 1);
			map.put(s.charAt(right), right + 1);
			right++;
		}
				
		return max;
	}
}

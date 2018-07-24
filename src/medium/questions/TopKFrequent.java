package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class TopKFrequent extends SolutionOutline {
	public void runTest() {
		this.setClassName("TopKFrequent");
		this.setDifficulty("Medium");
		//String[] test = {"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"};
		String[] test = {"i", "love", "leetcode", "i", "love", "coding"};
		this.setInput(Arrays.toString(test));
		String answer = solution(test, 2).toString();
		this.setOutput(answer);
		this.printResult();
	}
	
	public List<Entry> solution(String[] words, int k) {
		HashMap<String, Entry> map = new HashMap<String, Entry>();
		List<Entry> answer = new ArrayList<Entry>();
		
		for (String word: words) {
			if (map.containsKey(word)) {
				Entry e = map.get(word);
				e.add1Occurance();
				map.put(word, e);
			} else {				
				map.put(word, new Entry(word, 1));
			}
		}
		
		Entry e;
		int index;
		for (String key : map.keySet()) {
			e = map.get(key);
			index = Collections.binarySearch(answer, e);
			index = Math.abs(index+1);
			if (index < k) {
				answer.add(index, e);
				if (answer.size() > k) {
					answer.remove(k);
				}
			}
		}
		return answer;
	}
	
	private class Entry implements Comparable<Entry>{
		String word;
		int occurances;
		
		Entry(String w, int o) {
			word = w;
			occurances = o;
		}
		
		public int compareTo(Entry e) {
			if (this.occurances > e.occurances ) {
				return -1;
			} else if (this.occurances < e.occurances) {
				return 1;
			} else if (this.word.compareToIgnoreCase(e.word) < 0) {
				return -1;
			} else if (this.word.compareToIgnoreCase(e.word) > 0) {
				return 1;
			}
			
			return 0;
		}
		
		public String toString() {
			return word;
		}		
		
		public void add1Occurance() {
			occurances++;
		}
	}
}

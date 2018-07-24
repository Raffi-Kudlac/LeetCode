package medium.questions;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

import helper.SolutionOutline;

public class CustomSortString extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("CustomSortString");
		this.setDifficulty("Medium");
		
		String order = "bhatszr";
		String target = "abfladsjfhd";
		this.setInput("New order: " + order + "\n\t" + "target: " + target);
		String answer = this.solution(order, target);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public String solution(String order, String target) {
		
		char[] ranking = order.toCharArray();
		
		HashMap<Character, Integer> askii = new HashMap<Character, Integer>();
		
		int counter = 0;
		for (char c : ranking) {
			askii.put(c, counter);
			counter++;
		}
		
		Character[] targetArray = new Character[target.length()];
		for (int index = 0; index < target.length(); index++) {
			targetArray[index] = target.charAt(index);
		}
		
		Arrays.sort(targetArray, new Comparator<Character>() {
			
			public int compare(Character o1, Character o2) {

				return askii.getOrDefault(o1, Integer.MAX_VALUE) - askii.getOrDefault(o2, Integer.MAX_VALUE);
			}
			
		});		
				
		return Arrays.toString(targetArray);
	}	
}

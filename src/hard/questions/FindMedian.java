package hard.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.lang3.ArrayUtils;

import helper.SolutionOutline;

public class FindMedian extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("FindMedian");
		this.setDifficulty("Hard");
		
		int[] arr1 = buildOrderedArray(5, 100);
		int[] arr2 = buildOrderedArray(7, 100);
		String s = Arrays.toString(arr1) + "\n\t";
		s += Arrays.toString(arr2);
		System.out.println("Proper answer: " + answerCheck(arr1, arr2));
		this.setInput(s);
		double answer = this.solution(arr1, arr2);
		this.setOutput(answer);
		this.printResult();
	}
	
	// https://leetcode.com/problems/median-of-two-sorted-arrays/description/
	// got algorithm from https://www.youtube.com/watch?v=LPFhl65R7ww
	public double answerCheck(int[] arr1, int[] arr2) {
		ArrayList<Integer> total = new ArrayList<Integer>();
		total.addAll(Arrays.asList(ArrayUtils.toObject(arr1)));
		total.addAll(Arrays.asList(ArrayUtils.toObject(arr2)));
		Collections.sort(total);
		
		int middle = (int) (total.size() / 2);
		System.out.println(Arrays.toString(total.toArray()));
		if (total.size() % 2 == 0) {			
			return (total.get(middle) + total.get(middle - 1)) / 2;
		}
				
		return total.get(middle);
	}  
	
	public double solution(int[] arr1, int[] arr2) {
		int[] shorter = arr2;
		int[] longer =  arr1;
		double answer = 0;
		if (arr1.length <= arr2.length) {
			shorter = arr1;
			longer = arr2;
		}
		int start = 0;
		int end = shorter.length - 1;
		int posX, posY;
		boolean evenTotal = (shorter.length + longer.length) % 2 == 0;			
		int shortMin, shortMax, longMin, longMax;
		
		while (true) {
			posX = (start + end) / 2;
			posY = (( shorter.length + longer.length + 1) / 2) - posX;			
			
			shortMin = posX == 0 ? Integer.MIN_VALUE : shorter[posX - 1];
			shortMax = posX == shorter.length ? Integer.MAX_VALUE : shorter[posX];			
			longMin = posY == 0 ? Integer.MIN_VALUE : longer[posY - 1];
			longMax = posY == longer.length ? Integer.MAX_VALUE : longer[posY];
			
			if (shortMin <= longMax && longMin <= shortMax) {				
				if (evenTotal) {
					answer = (Math.max(shortMin, longMin) + Math.min(shortMax, longMax)) / 2;
				} else {
					answer = Math.max(shortMin, longMin);
				}
				
				break;
			} else if (shortMin > longMax) {
				end = posX - 1;
			} else if (longMin > shortMax) {
				start = posX + 1;
			}			
		}		
		
		return answer;
	}
}

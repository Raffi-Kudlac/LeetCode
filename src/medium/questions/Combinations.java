package medium.questions;

import java.util.ArrayList;
import java.util.List;

import helper.SolutionOutline;

public class Combinations extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("Combinations");
		this.setDifficulty("Medium");
		
		int testN = 10;
		int testK = 2;
		
		this.setInput("N: " + Integer.toString(testN) + "\n\t" + "K: " + Integer.toString(testK));
		List<List<Integer>> answer = this.solution(testN, testK);
		this.setOutput(TwoDListToString(answer));
		this.printResult();
	} 
	
	public List<List<Integer>> solution(int n, int k) {
		
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		boolean done = false;
		int answerSize = NChooseK(n, k);
		
		int[] pointers = new int[k];
		for (int x = 0; x < pointers.length; x++) {
			pointers[x] = x;
		}
		
		int[] numbs = new int[n];
		for (int x = 0; x < n; x++) {
			numbs[x] = x + 1;
		}
		
		
		while (done == false) {
			// 1 record entry
			// shift pointers
			// done check
			
			recordEntry(answer, pointers, numbs);
			
			
			boolean end = false;
			int counter = pointers.length - 1;
			// shift pointers
			while (end == false && counter >= 0) {
				int pointer = pointers[counter];
				
				if (pointer != numbs.length - 1 && validRightCheck(numbs, pointers, counter)) {
					pointers[counter] = ++pointer;
					repositionPointers(numbs, pointers, counter);
					end = true;
				} else if (pointer == numbs.length - 1 || validRightCheck(numbs, pointers, counter) == false) {
					counter--;
				}
			}
			
			//done check
			done = answer.size() == answerSize;
		}
				
		return answer;
	}
	
	
	public int NChooseK(int n, int k) {
		return factorial(n) / (factorial(k) * factorial(n - k));
	}
	
	public int factorial(int k) {
		if (k == 1) {
			return 1;
		}
		
		return k * factorial(k-1);
	}
	
	
	public boolean repositionPointers(int[] numbs, int[] pointers, int current) {
		
		if (current == pointers.length - 1) {
			return false;
		}
				
		for (int x = current + 1; x < pointers.length; x++) {
			pointers[x] = pointers[x-1] + 1; 
		}
		
		return true;
	}
	
	public boolean validRightCheck(int[] n, int[] pointers, int current) {
		
		if (current == pointers.length - 1) {
			return true;
		}
		
		int next = current + 1;
		int nextValue = pointers[next];
		
		return nextValue != pointers[current] + 1;		
	}
	
	public void recordEntry(List<List<Integer>> answer, int[] pointers, int[] n) {
		
		List<Integer> entry = new ArrayList<Integer>();
		
		for (int p : pointers) {
			entry.add(n[p]);
		}
		
		answer.add(entry);
	}
}

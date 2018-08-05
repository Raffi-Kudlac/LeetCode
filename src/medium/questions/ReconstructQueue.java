package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import helper.SolutionOutline;

public class ReconstructQueue extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("ReconstructQueue");
		this.setDifficulty("Medium");
		
		int[][] test = buildData();		
		this.setInput(TwoDIntArrayToString(test));
		int[][] answer = this.solution(test);
		this.setOutput(TwoDIntArrayToString(answer));
		this.printResult();
	}
	
	public int[][] buildData() {
		
		int[][] data = {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
		return data;
	}
	
	public int[][] solution(int[][] people) {
		
		Arrays.sort(people, new Comparator<int[]>() {
						
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0]) {
					return a[1] - b[1];
				} else {
					return b[0] - a[0];
				}
			}
		});
		
		List<int[]> list = new ArrayList<int[]>();
		for (int x = 0; x < people.length; x++) {
			list.add(null);
		}
		
		
		for (int[] person: people) {			
			list.add(person[1], person);			
		}
		
		int index = 0;
		while (index < list.size()) {
			if (list.get(index) == null) {
				list.remove(index);
			} else {
				index++;
			}
		}
		
		int[][] result = new int[list.size()][2];
		for (int x = 0; x < list.size(); x ++) {
			result[x] = list.get(x);
		}		
		
		return result;
	}		

}

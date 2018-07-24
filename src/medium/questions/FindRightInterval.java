package medium.questions;

import java.util.Arrays;
import java.util.HashMap;

import helper.SolutionOutline;
public class FindRightInterval extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("FindRightInterval");
		this.setDifficulty("Medium");
		
		Interval[] test = buildTest2();
		
		this.setInput(Arrays.toString(test));
		int[] answer = this.solution(test);
		this.setOutput(Arrays.toString(answer));
		this.printResult();
	}
	
	public Interval[] buildTest2() {
		Interval[] test = new Interval[5];
		test[0] = new Interval(1, 10);
		test[1] = new Interval(2, 9);
		test[2] = new Interval(3, 8);
		test[3] = new Interval(4, 7);
		test[4] = new Interval(5, 6);
		
		return test;
	}
	
	public Interval[] buildTest1() {
		Interval[] test = new Interval[3];
		test[0] = new Interval(3,4);
		test[1] = new Interval(2,3);
		test[2] = new Interval(1,2);
		
		return test;
	}
	
	public int[] solution(Interval[] interval) {
		
		int[] start = new int[interval.length];
		HashMap<Integer, Integer> indexMap = new HashMap<Integer, Integer>();
		
		Interval element;
		for (int x = 0; x < interval.length; x++) {
			element = interval[x];
			indexMap.put(element.start, x);
			start[x] = element.start;
		}
		
		Arrays.sort(start);
		int[] answer = new int[start.length];				
		int singleAnswer;
		Interval i;
		for (int x = 0; x < interval.length; x++) {
			i = interval[x];
			int startIndex = Arrays.binarySearch(start, i.end);
			
			if (startIndex < 0) {
				startIndex = -(startIndex + 1);
			}
			
			if (startIndex >= interval.length) {
				singleAnswer = -1;
			} else {
				singleAnswer = indexMap.get(start[startIndex]);
			}
			
			answer[x] = singleAnswer;
		}
		
		return answer;		
	}
	
	class Interval {
	  int start;
	  int end;
	  Interval() { start = 0; end = 0; }
	  Interval(int s, int e) { start = s; end = e; }
	  
	  public String toString() {
		  return "[ " + start + "," + end + "]";
	  }
	}	
}

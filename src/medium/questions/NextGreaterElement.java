package medium.questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import org.apache.commons.lang3.ArrayUtils;

import helper.SolutionOutline;

public class NextGreaterElement extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("NextGreaterElement");
		this.setDifficulty("Medium");
		
		// int[] solution2 = {1,2,1};	// for solution 2
		// int[] solution1 = {1,3,5,4,2}; // for solution 1;
		// int[] solution1Sub = {4,1,2}; // for solution 1
		
		int solution3 = 534976;
		
		this.setInput(solution3);
		int answer = this.solution3(solution3);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/next-greater-element-i/description/
	public int[] solution1(int[] array, int[] subset) {
		int[] answer = new int[subset.length];
		
		HashMap<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		
		for (int n : array) {
			while(!stack.isEmpty() && stack.peek() < n) {
				map.put(stack.pop(), n);
			}
			stack.push(n);			
		}
		
		for (int index = 0; index < subset.length; index++) {
			answer[index] = map.getOrDefault(subset[index], -1);
		}
		
		
		return answer;
	}

	// https://leetcode.com/problems/next-greater-element-ii/description/
	public int[] solution2(int[] array) {

		int[] answer = new int[array.length];		
		HashMap<Integer, Integer> map = new HashMap<>();
		Stack<Integer> stack = new Stack<>();
		
		int index = 0;
		int n;
		int pass = 0;
		while (map.keySet().size() < array.length - 1 && pass < 2) {
			if (index == array.length) {
				index = 0;
				pass++;
			}
			
			n = array[index];			
			
			while(!stack.isEmpty() && array[stack.peek()] < n) {
				
				if (!map.containsKey(stack.peek())) {
					map.put(stack.pop(), n);
				} else {
					stack.pop();
				}				
			}
			stack.push(index);
			index++;
		}
		
		for (int x = 0; x < array.length; x++) {
			answer[x] = map.getOrDefault(x, -1);
		}
		
		return answer;
	}
	
	// https://leetcode.com/problems/next-greater-element-iii/description/
	public int solution3(int num) {
		
		Character[] arr = ArrayUtils.toObject(Integer.toString(num).toCharArray());
		
		// find first number that is smaller than the number before it (moving from right to left) 
		int index = arr.length - 2;
		while (index >= 0 && arr[index] >= arr[index + 1]) {
			index--;
		}
		
		// the number is sorted in descending order, there is no answer
		if (index < 0) {
			return -1;
		}
		
		// find the number to the right of index that is the smallest number larger than
		// the number at index
		int smallestLargest = index + 1;		
		for (int x = index + 1; x < arr.length; x++) {
			if (arr[x] > arr[index]) {
				if (arr[x] == Math.min(arr[x], arr[smallestLargest])) {
					smallestLargest = x;
				}
			}
		}
		
		// swap the values
		char temp = arr[index];
		arr[index] = arr[smallestLargest];
		arr[smallestLargest] = temp;
		
		// sort the numbers in between the swapped numbers (including smallestLargest)
		// could use bucket sort for this and get linear time
		Arrays.sort(arr, index + 1, smallestLargest + 1, (i1, i2) -> { return i2 - i1;});
		
		return Integer.parseInt(new String(ArrayUtils.toPrimitive(arr)));
	}
}

package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class CombinationSum extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("CombinationSum");
		this.setDifficulty("Medium");

		int[] test = {2,3,5 , 10};
		this.setInput(Arrays.toString(test));
		String answer = this.solution(test, 10).toString();
		this.setOutput(answer);
		this.printResult();
	}
	
	public List<List<Integer>> solution(int[] nums, int target) {
		
		List<List<Integer>> answer = DPSolution(nums, target);
		return answer;
	}
	
	public List<Integer> makeList(int n) {
		List<Integer> list = new ArrayList<Integer>();
		list.add(n);
		return list;
	}
	
	public void addToMap(HashMap<Integer, List<List<Integer>>> map, List<Integer> value, int key) {
		if (map.containsKey(key)) {
			List<List<Integer>> keyThEntry = map.get(key);
			keyThEntry.add(value);
			map.put(key, keyThEntry);
		} else {
			List<List<Integer>> keyThEntry = new ArrayList<List<Integer>>();
			keyThEntry.add(value);
			map.put(key, keyThEntry);
		}
	}
	
	public void checkForDuplicates(List<List<Integer>> entryX, List<List<Integer>> count) {
		
		if (entryX.size() == 0) {
			entryX.addAll(count);
			return;
		}
		
		
		for (List<Integer> liC : count) {
			
			for (int index = 0; index < entryX.size(); index++) {
				List<Integer> liX = entryX.get(index);
				if (liX.equals(liC)) {
					break;
				}
				
				if (index == entryX.size() - 1) {
					entryX.add(liC);
				}
			}			
		}
	}
	
	
	public List<List<Integer>> DPSolution(int[] nums, int target) {
		
		HashMap<Integer, List<List<Integer>>> dpCombinations = new HashMap<Integer, List<List<Integer>>>();
		
		if (search(nums, 1)) {
			List<Integer> entry = makeList(1);			
			addToMap(dpCombinations, entry, 1);
		}
				
		int oppositeCount;
		for (int x = 2; x <= target; x++) {
			
			List<List<Integer>> entryX = new ArrayList<List<Integer>>();
			for (int count = 1; count <= x/2; count++) {
				oppositeCount = x - count;
				
				List<List<Integer>> countList;
				if (oppositeCount == count) {
					List<List<Integer>> element = dpCombinations.get(count);					
					countList = combineSameList(element);
				} else {
					countList = combineLists(dpCombinations.get(count),dpCombinations.get(oppositeCount));
				}
				
				if (countList != null) {
					checkForDuplicates(entryX, countList);
					//entryX.addAll(countList);
				}
			}
			
			if (search(nums, x)) {
				List<Integer> entry = makeList(x);
				entryX.add(entry);				
			}
			
			if (entryX.size() > 0) {
				dpCombinations.put(x, entryX);
			}
			
		}
		
		return dpCombinations.get(target);
	}
	
	public List<List<Integer>> combineSameList(List<List<Integer>> num1) {
		if (num1 == null) {
			return null;
		}
		
		List<List<Integer>> combined = new ArrayList<List<Integer>>();
		
		for (int pointer1 = 0; pointer1 < num1.size(); pointer1++) {
			for (int pointer2 = pointer1 ; pointer2 < num1.size(); pointer2++) {
				List<Integer> entry = new ArrayList<Integer>();
				entry.addAll(num1.get(pointer1));
				entry.addAll(num1.get(pointer2));
				Collections.sort(entry);
				combined.add(entry);
			}
		}
		
		return combined;
	}
	
	public List<List<Integer>> combineLists(List<List<Integer>> num1, List<List<Integer>> num2) {
		
		if (num1 == null || num2 == null) {
			return null;
		}
		
		List<List<Integer>> combined = new ArrayList<List<Integer>>();
		
		for (List<Integer> l1 : num1) {
			for (List<Integer> l2: num2) {
				List<Integer> entry = new ArrayList<Integer>();
				entry.addAll(l1);
				entry.addAll(l2);
				Collections.sort(entry);
				combined.add(entry);
			}
		}
		
		return combined;
	}
	
	public boolean search(int[] nums, int target) {
		
		for(int x : nums) {
			if (x == target) {
				return true;
			}
		}
		
		return false;
	}
	
	public List<List<Integer>> checkMatch(int[] nums, int target) {
		
		List<List<Integer>> answer = new ArrayList<List<Integer>>();		
		int oppositeCounter;
		for (int counter = 1; counter <= target/2; counter++) {
			oppositeCounter = target - counter;
			
			List<List<Integer>> num1;
			if (counter != 1) {
				num1 = checkMatch(nums, counter);
			} else {
				num1 = new ArrayList<List<Integer>>();
			}
			
			List<List<Integer>> num2;
			if (counter != oppositeCounter) {
				num2 = checkMatch(nums, oppositeCounter);
			} else {
				num2 = num1;
			}			
			System.out.println("oppositeCounter:" + oppositeCounter);
			System.out.println("counter:" + counter);
			for (int num : nums) {
				if (counter == num) {
					List<Integer> entry = new ArrayList<Integer>();
					entry.add(counter);
					num1.add(entry);
				} else if (oppositeCounter == num && oppositeCounter != counter) {
					List<Integer> entry = new ArrayList<Integer>();
					entry.add(oppositeCounter);
					num2.add(entry);
				}
			}
			
			List<List<Integer>> combinations = combineLists(num1, num2);
			if (combinations != null) {
				answer.addAll(combinations);
			}			
		}
		
		for (int num : nums) {
			if (num == target) {
				List<Integer> entry = new ArrayList<Integer>();
				entry.add(target);
				answer.add(entry);
			}
		}
		
		return answer;
	}
	
	
}

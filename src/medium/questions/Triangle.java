package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class Triangle extends SolutionOutline {
	public void runTest() {
		this.setClassName("Triangle");
		this.setDifficulty("Medium");
		
		List<List<Integer>> test = buildTriangle();		
		this.setInput(TwoDListToString(test));
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}
	
	public List<List<Integer>> buildTriangle() {
		List<List<Integer>> triangle= new ArrayList<List<Integer>>();
		
		List<Integer> l0 = new ArrayList<Integer>();
		List<Integer> l1 = new ArrayList<Integer>();
		List<Integer> l2 = new ArrayList<Integer>();
		List<Integer> l3 = new ArrayList<Integer>();
		
					l0.add(2);
				l1.add(3); l1.add(4);		
			l2.add(6); l2.add(5); l2.add(7);
		l3.add(4); l3.add(1); l3.add(8); l3.add(3);		
		
		triangle.add(l0);
		triangle.add(l1);
		triangle.add(l2);
		triangle.add(l3);		
		return triangle;
	}
	
	// https://leetcode.com/problems/triangle/description/
	public int solution(List<List<Integer>> triangle) {
		
		HashMap<String, Integer> map = new HashMap<>();		
		return traverse(map, triangle, 0, 0);
	}
	
	public int traverse(HashMap<String, Integer> map, 
			List<List<Integer>> triangle, int currentIndex, int currentLevel) {
		String key = currentLevel + "+" + currentIndex;
		if (map.containsKey(key)) {
			return map.get(key);
		} else if (triangle.size() == currentLevel) {
            return 0;
        }                
        
        int val = triangle.get(currentLevel).get(currentIndex);
        int left = val + traverse(map, triangle, currentIndex, currentLevel + 1);
        int right = val + traverse(map, triangle, currentIndex + 1, currentLevel + 1);   
        map.put(key, Math.min(left, right));
        return map.get(key);
    }
}

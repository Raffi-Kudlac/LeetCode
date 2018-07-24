package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import helper.SolutionOutline;

public class Subsets extends SolutionOutline {
	public void runTest() {
		this.setClassName("Subsets");
		this.setDifficulty("Medium");
		
		int[] test = {1,2,3};		
		this.setInput(test);
		List<List<Integer>> answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/subsets/description/
	public List<List<Integer>> solution(int[] nums) {
		ArrayList<List<Integer>> answer = new ArrayList<List<Integer>>();        
        ArrayList<Integer> clone = new ArrayList<Integer>(Arrays.asList( ArrayUtils.toObject(nums)));
        ArrayList<Integer> base = new ArrayList<Integer>();
        ArrayList<Integer> entry;
        
        while(clone.size() > 0) {
            
            for (int index = 0; index < clone.size(); index++) {
                
                base.add(clone.get(index));
                if (base.size() == 1) {
                	answer.add(new ArrayList<Integer>(base));
                }
                
                for (int pointer = index + 1; pointer < clone.size(); pointer++) {
                    entry = new ArrayList<Integer>(base);
                    entry.add(clone.get(pointer));
                    answer.add(entry);
                }
            }
            
            base.clear();
            clone.remove(0);
        }
        
        return answer;
	}		
}

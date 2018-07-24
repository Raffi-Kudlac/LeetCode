package medium.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class BindarySearch extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("BindarySearch");
		this.setDifficulty("Medium");
		
		int[] test = {2,3,3,5,5,5,5,9};		
		this.setInput(test);
		int[] answer = this.solution(test, 5);
		this.setOutput(Arrays.toString(answer));
		this.printResult();
	} 
	
	public int[] solution(int[] nums, int target) {
		int[] answer = new int[2];        
        int pos = searchTarget(nums, (double) target);
        
        if (pos < 0) {
            answer[0] = answer[1] = -1;
        } else if (pos > 0) {
            pos = searchTarget(nums, target - 0.5);
            answer[0] = pos > 0 ? pos + 1 : -(pos + 1);
            
            pos = searchTarget(nums, target + 0.5);
            answer[1] = pos > 0 ? pos - 1 : -pos - 2;
            
        }
        
        return answer;
	}
	
	
	public int searchTarget(int[] nums, double target) {
		boolean found = false;
        int pointer = nums.length / 2;
        int low = 0;
        int high = nums.length - 1;
        
        while (found == false) {
            
            if (nums[pointer] == target) {
                found = true;
            } else if (high == low) {                
                pointer = low;
                break;                                
            } else if (target > nums[pointer]) {
                low = pointer + 1;
                pointer = (high + low) / 2;
            } else if (target < nums[pointer]) {
                high = pointer - 1;
                pointer = (high + low) / 2;
            }            
        }
        
        if (found) {
            return pointer;
        } else if (target < nums[pointer]) {
            return -pointer - 1;
        } else if (target > nums[pointer]) {
        	return -pointer - 2;
        }
        
        return -1;
	}

}

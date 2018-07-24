package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import helper.SolutionOutline;

public class ThreeSum extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("ThreeSum");
		this.setDifficulty("Medium");
		
		int[] test = {-1, 0, 1, 2, -1, -4};		
		this.setInput(test);
		List<List<Integer>> answer = this.solution(test);
		this.setOutput(TwoDListToString(answer));
		this.printResult();
	} 
	
	public List<List<Integer>> solution(int[] nums) {
		
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
		
		Arrays.sort(nums);
		int x = 0;
		while (x < nums.length - 2) {
			if (nums[x] > 0) {
				break;
			}
			int j = x + 1;
			int k = nums.length - 1;
			int sum;
			while (j < k) {
				sum = nums[x] + nums[j] + nums[k];
				if (sum == 0) {					
					answer.add(Arrays.asList(nums[x], nums[j], nums[k]));
				}
				
				if (sum >= 0) {
					do {
						k--;
					} while(nums[k] == nums[k+1] && j < k);
				}
				
				if (sum <= 0) {					
					do {
						j++;
					} while(nums[j] == nums[j-1] && j < k);
				}
			}
			
			do {
				x++;
			} while(nums[x] == nums[x-1] && x < nums.length - 2);
				
		}
				
		return answer;
	}
	
	public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums.length < 3) return result;
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 2) {
            if(nums[i] > 0) break;
            int j = i + 1;
            int k = nums.length - 1;
            while(j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0) result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                if(sum <= 0) while(nums[j] == nums[++j] && j < k);
                if(sum >= 0) while(nums[k--] == nums[k] && j < k);
            }
            while(nums[i] == nums[++i] && i < nums.length - 2);
        }
        return result;
    } 
		
}

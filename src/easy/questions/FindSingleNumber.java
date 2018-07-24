package easy.questions;

import java.util.Arrays;

import helper.SolutionOutline;

public class FindSingleNumber extends SolutionOutline{
	public void runTest() {
		this.setClassName("FindSingleNumber");
		this.setDifficulty("Easy");
		
		//int[] test = {5, 5, 6};
		int[] test = {5, 5, 5, 5, 6, 6, 6, 6,34, 34, 34, 34, 78, 78, 78,78, 89, 89, 89,89, 3};
		this.setInput(Arrays.toString(test));
		int answer = this.solution2(test);
		this.setOutput(Integer.toString(answer));
		this.printResult();
	}
	
	
	public int solution2(int[] nums) {
		
		int answer = 0;
		
		for (int n : nums) {
			answer = answer ^ n;
		}
		
		return answer;
	}
	
	public int solution(int[] nums) {		
		int[] answer = new int[32];
				
		for (int n: nums) {						
			String[] binaryNumber = Integer.toBinaryString(n).split("");
			
			boolean done = false;
			int counter = 0;
			int answerIndex;
			int numberIndex;
			while (done == false) {
				answerIndex = 31 - counter;
				numberIndex = binaryNumber.length - 1 - counter;
				
				try {
					answer[answerIndex] += Integer.parseInt(binaryNumber[numberIndex]);
				} catch (Exception e) {
					done = true;
				}
				counter++;
			}			
		}
		
		//System.out.println(Arrays.toString(answer));
		
		for (int x = 0; x < answer.length; x++) {
			answer[x] = answer[x] % 2;
		}
		
		String a = Arrays.toString(answer).replaceAll(",", "");
		a = a.replaceAll("\\[", "");
		a = a.replaceAll("\\]", "");
		a = a.replaceAll(" ", "");
		//System.out.println(Arrays.toString(answer));
		//System.out.println(a);
		
		return Integer.parseInt(a, 2);
	}	
}

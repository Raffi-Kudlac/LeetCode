package medium.questions;

import helper.SolutionOutline;

public class IntegerBreak extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("IntegerBreak");
		this.setDifficulty("Medium");
		
		int test = 15;		
		this.setInput(test);		
		this.setOutput(this.solution(test));
		this.printResult();
	} 
	
	// works for n > 3
	public int solution(int n) {
		
		if (n == 2) return 2;
        if (n == 3) return 3;
        if (n == 4) return 4;
        return solution(n - 3) * 3; 
	}	

}

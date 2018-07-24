package medium.questions;

import helper.SolutionOutline;

public class GenerateNextPalindrome extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("GenerateNextPalindrome");
		this.setDifficulty("Medium");
		
		int test = 10045;		
		this.setInput(test);
		String answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public String solution(int n) {
		
		int count = 0;
		int nCopy = n;
		int length = Integer.toString(nCopy).length();
		System.out.println("log: " + (int)(1 + Math.log10(n)));
		
		while(count < length/2) {
			int leftNumb = getChar(nCopy, count);
			int rightNumb = getChar(nCopy, length - count - 1);
			
			if (rightNumb < leftNumb) {
				nCopy+= Math.pow(10, count)*(leftNumb - rightNumb);
			} else if (rightNumb > leftNumb) {
				nCopy+= Math.pow(10, count)*(10 + leftNumb - rightNumb);
			} else if (rightNumb == leftNumb) {
				count++;
			}			
		}
		
		return Integer.toString(nCopy);
	}
	
	public int getChar(int numb, int index) {
		
		String n = Integer.toString(numb);
		
		return Character.getNumericValue(n.charAt(index));
	}
	

}

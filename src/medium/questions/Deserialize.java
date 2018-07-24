package medium.questions;

import java.util.ArrayList;
import java.util.Stack;

import helper.SolutionOutline;

public class Deserialize extends SolutionOutline {
	public void runTest() {
		this.setClassName("");
		this.setDifficulty("Medium");
		
		String test = "";		
		this.setInput(test);
		String answer = this.solution();
		this.setOutput(answer);
		this.printResult();
	}
	
	// note solution wont run locally, need to run it on the site
	//https://leetcode.com/problems/mini-parser/description/
	public NestedInteger solution(String s) {
		int current = 0;
        int currentChar;
        Stack<NestedInteger> stack = new Stack<NestedInteger>();
        NestedInteger currentElem = null;
        
        if (s.isEmpty()) {
            return null;
        }
        
        
        if (s.charAt(0) != '[') {
            return new NestedInteger(Integer.valueOf(s));
        }
        
        while (current < s.length()) {
            currentChar = s.charAt(current);            
            
            if (Character.isDigit(currentChar) || currentChar == '-') {
                
                String sNum = "";                
                while (Character.isDigit(s.charAt(current)) || s.charAt(current) == '-') {
                    
                    sNum += s.charAt(current);                    
                    current++;                    
                }
                current--;
                
                currentElem.add(new NestedInteger(Integer.valueOf(sNum)));                                
            } else if (currentChar == '[') {
                
                if (currentElem != null) {
                    stack.push(currentElem);                    
                }
                
                currentElem = new NestedInteger();
                
            } else if (currentChar == ']') {
                
                if (!stack.isEmpty()) {
                    
                    NestedInteger pop = stack.pop();
                    pop.add(currentElem);
                    currentElem = pop;
                }
            }
            
            current++;
        }
        
        return currentElem;
	}
	
	public class NestedInteger {
		
		int val;
		ArrayList<NestedInteger> list;
		
		NestedInteger(int n) {
			val = n;
		}
		
		NestedInteger(){}
		
		public void add(NestedInteger n) {
			
		}
	}
}

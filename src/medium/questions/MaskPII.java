package medium.questions;

import helper.SolutionOutline;

public class MaskPII extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("MaskPII");
		this.setDifficulty("Medium");
		//String test = "86-(10)12345678";
		String test = "AB@qq.Com";
		this.setInput(test);
		String answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}
	
	public String solution(String s) {
		
		s = s.toLowerCase();		
		 if (s.charAt(0) <= 'z' && s.charAt(0) >= 'a') {
			 return this.maskEmail(s);
		 } else {
			 return this.maskPhone(s);
		 }		
	}
	
	public String maskPhone(String s) {
		s = s.replaceAll("-", "");
		s = s.replaceAll("\\+", "");
		s = s.replaceAll("\\(", "");
		s = s.replaceAll("\\)", "");
		s = s.replaceAll(" ", "");
		
		String answer;		
		String last4 = s.substring(s.length() - 4, s.length());
		String firstDidgets = s.substring(0, s.length() - 10);
		
		answer = starMaker(3) + "-" + starMaker(3) + "-" + last4;
		if (firstDidgets.length() > 0) {
			answer = "+" + starMaker(firstDidgets.length()) + "-" + answer;
		}
		
		
		return answer;
	}
	
	public String starMaker(int n) {
		String star = "";
		
		for (int x = 0; x < n; x++) {
			star += "*";
		}
		
		return star;
	}
	
	public String maskEmail(String s) {
		String answer = "";		
		String stars = "*****";
		int index = s.indexOf('@');
		
		answer += s.charAt(0) + stars + s.charAt(index - 1) + s.substring(index, s.length());
		
		return answer;
	}
	
}



















package medium.questions;

import helper.SolutionOutline;

public class AdditiveNumber extends SolutionOutline {
	public void runTest() {
		this.setClassName("AdditiveNumber");
		this.setDifficulty("Medium");

		String test = "199100199";
		this.setInput(test);
		boolean answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	}

	public boolean solution(String num) {

		for (int firstNum = 1; firstNum < num.length() - 2; firstNum++) {

			for (int secondNum = 1; secondNum < num.length() - firstNum - 1; secondNum++) {

				String num1 = num.substring(0, firstNum);
				String num2 = num.substring(firstNum, firstNum + secondNum);
				String rest = num.substring(firstNum + secondNum);

				if (check(Integer.parseInt(num1), Integer.parseInt(num2), rest)) {
					return true;
				}
			}
		}
		
		return false;
	}

	public boolean check(int num1, int num2, String s) {

		String clone = new String(s);
		String nextNum;
		int sum;
		while (clone.length() != 0) {

			sum = num1 + num2;

			try {
				nextNum = clone.substring(0, (int) Math.log10(sum) + 1);
			} catch (Exception e) {
				return false;
			}

			if (!nextNum.equals(Integer.toString(sum))) {
				return false;
			}

			num1 = num2;
			num2 = sum;
			clone = clone.substring((int) Math.log10(sum) + 1);
		}

		return true;
	}

}

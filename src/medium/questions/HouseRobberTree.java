package medium.questions;

import helper.SolutionOutline;

public class HouseRobberTree extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("HouseRobberTree");
		this.setDifficulty("Medium");
		
		String test = "";		
		this.setInput(test);
		String answer = this.solution();
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(TreeNode root) {
		int[] ans = rob(root);
		
		return Math.max(ans[0], ans[1]);
	}
	
	public int[] rob(TreeNode node) {
		
		if (node == null) {
			return new int[]{0, 0};
		}
		
		int[] left = rob(node.left);
		int[] right = rob(node.right);
		int[] answer = new int[2];
		answer[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		answer[1] = left[0] + node.val + right[0];
		return answer;
	}

}

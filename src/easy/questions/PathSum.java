package easy.questions;

import helper.SolutionOutline;

public class PathSum extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("PathSum");
		this.setDifficulty("Easy");
		
		String test = "10,5,-3,3,2,null,11,3,-2,null,1";		
		this.setInput(test);
		int answer = this.solution(deserialize(test), 8);
		this.setOutput(answer);
		this.printResult();
	} 
	
	public int solution(TreeNode root, int sum) {
		return checkNode(root, sum, sum);
	}
	
	public int checkNode(TreeNode node, int target, int subTarget) {
		if (node == null) {
			return 0;
		}
		
		int count = 0;
		if (subTarget - node.val == 0) {
			count++;
		} else {
			count += checkNode(node.left, target, subTarget - node.val);
			count += checkNode(node.right, target, subTarget - node.val);
		}
		
		count += checkNode(node.left, target, target);
		count += checkNode(node.right, target, target);
		
		return count;
	}

}

package medium.questions;

import helper.SolutionOutline;

public class CountCompleteTreeNodes extends SolutionOutline {
	/**
	 * Idea
	 * 		https://leetcode.com/problems/count-complete-tree-nodes/discuss/61958/Concise-Java-solutions-O(log(n)2)
	 */
	public void runTest() {
		this.setClassName("CountCompleteTreeNodes");
		this.setDifficulty("Medium");
		
		TreeNode test = deserialize("1,2,3,4,5,6,7,8,9,10,11,12");		
		this.setInput(serialize(test));
		int answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 
	
	// https://leetcode.com/problems/count-complete-tree-nodes/description/
	public int solution(TreeNode root) {
		
		int height = findHeight(root);
		int nodes = 0;
		TreeNode fakeRoot = root;
		
		while (fakeRoot != null) {
			
			if (findHeight(fakeRoot.right) == height - 1) {
				nodes += Math.pow(2, height);
				fakeRoot = fakeRoot.right;
			} else {
				nodes += Math.pow(2, height - 1);
				fakeRoot = fakeRoot.left;
			}
			
			height--;
		}		
		
		return nodes;
	}
	
	public int findHeight(TreeNode root) {
		
		return root == null ? -1 : 1 + findHeight(root.left);
	} 
}

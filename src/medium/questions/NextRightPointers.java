package medium.questions;

import helper.SolutionOutline;

public class NextRightPointers extends SolutionOutline {
	TreeLinkNode root;
	public void runTest() {
		this.setClassName("NextRightPointers");
		this.setDifficulty("Medium");
		
		TreeLinkNode test = null;
		root = test;
		this.setInput("");
		this.solution(test);
		this.setOutput("");
		this.printResult();
	} 
	
	// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/description/
	public void solution(TreeLinkNode root) {
		
		traverseTree(root, 1, 1);
	}
	
	public void traverseTree(TreeLinkNode node, int current, int level) {
		
		if (node == null) {
			return;
		}
		
		if (node.left != null) {
			node.left.next = node.right;
		}
		
		if (node.next == null) {
			int nextIndex = Math.pow(2, level) >= current + 1 ? -1 : current + 1;		
			if (nextIndex != -1) {			
				node.right = getNode(nextIndex, 1, root);
			}
		}
		
		traverseTree(node.left, current*2, level + 1);
		traverseTree(node.right, current*2 + 1, level + 1);		
	}
	
	public TreeLinkNode getNode(int target, int current, TreeLinkNode node) {
		if (node == null || current > target) {
			return null;
		} else if (current == target) {
			return node;
		}
		
		TreeLinkNode left = getNode(target, current*2, node.left);		
		return left == null ? getNode(target, current*2 + 1, node.right) : left;
	}
	
	private class TreeLinkNode {
		TreeLinkNode left;
		TreeLinkNode right;
		TreeLinkNode next;
		int val;
		
		TreeLinkNode(int v) {val = v;}
	}

}

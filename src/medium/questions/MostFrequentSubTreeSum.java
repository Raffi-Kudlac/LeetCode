package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class MostFrequentSubTreeSum extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("MostFrequentSubTreeSum");
		this.setDifficulty("Medium");
		
		TreeNode test = buildTree();		
		this.setInput(test.toString());
		List<Integer> answer = this.solution(test);
		this.setOutput(answer.toString());
		this.printResult();
	} 
	
	public List<Integer> solution(TreeNode root) {
		
		HashMap<Integer, Integer> sumMap = new HashMap<Integer, Integer>();
		traverseTree(root, sumMap);
		
		int max = 0;
		ArrayList<Integer> answer = new ArrayList<Integer>();
		int element;
		
		for (int key: sumMap.keySet()) {
			element = sumMap.get(key);
			if (element > max) {
				answer.clear();
				answer.add(key);
				max = element;
			} else if (element == max) {
				answer.add(key);
			}
		}
				
		return answer;
	}
	
	public int traverseTree(TreeNode node, HashMap<Integer, Integer> sumMap) {
		
		if (node == null) {
			return 0;
		}
						
		int sum = node.val + traverseTree(node.left, sumMap) + traverseTree(node.right, sumMap);
		sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
		return sum;		
	}
	
	public TreeNode buildTree() {
		TreeNode root = new TreeNode(10);
		
		TreeNode two = new TreeNode(5);
		TreeNode three = new TreeNode(5);
		
		TreeNode four = new TreeNode(-5);
		TreeNode five = new TreeNode(-5);
		
		TreeNode six = new TreeNode(2);
		TreeNode seven = new TreeNode(3);
		
		root.left = two;
		root.right = three;
		
		two.left = four;
		two.right = five;
		
		three.left =six;
		three.right = seven;
		
		return root;
	}
	
	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		TreeNode(int n) {
			this.val = n;
		}
		
		public String toString() {
			String answer = "";
			
			if (right != null && left != null) {
				answer = val + "-> ( " + left.toString() + ", " + right.toString() + " )";
			} else {
				answer = val + "";
			}
						
			return answer;
		}
	}
}

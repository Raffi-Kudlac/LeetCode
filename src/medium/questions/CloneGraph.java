package medium.questions;

import java.util.HashMap;

import helper.SolutionOutline;

public class CloneGraph extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("CloneGraph");
		this.setDifficulty("Medium");
		
		String test = "";		
		this.setInput(test);
		String answer = this.solution();
		this.setOutput(answer);
		this.printResult();
	} 
	
	//https://leetcode.com/problems/clone-graph/description/
	public UndirectedGraphNode solution(UndirectedGraphNode node) {
		
		if (node == null) {
			return null;
		}
				
		HashMap<Integer, UndirectedGraphNode> clonedNodes = new HashMap<Integer, UndirectedGraphNode>();
		
		cloneNode(node, clonedNodes);		
		return clonedNodes.get(node.label);
	}
	
	public void cloneNode(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> clonedNodes) {
		
		if (clonedNodes.containsKey(node.label)) {
			return;
		}
		
		UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
		clonedNodes.put(newNode.label, newNode);
		
		for (UndirectedGraphNode neighbourNode: node.neighbors) {			
			cloneNode(neighbourNode, clonedNodes);
			newNode.neighbors.add(clonedNodes.get(neighbourNode.label));			
		}		
	}
}

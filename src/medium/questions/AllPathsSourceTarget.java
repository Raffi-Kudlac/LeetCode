package medium.questions;

import java.util.ArrayList;
import java.util.List;

import helper.SolutionOutline;

public class AllPathsSourceTarget extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("AllPathsSourceTarget");
		this.setDifficulty("Medium");
		
		int[][] test = {{1,2}, {3}, {3}, {}} ;		
		this.setInput(test);
		List<List<Integer>> answer = this.solution(test);
		this.setOutput(answer);
		this.printResult();
	} 

	// https://leetcode.com/problems/all-paths-from-source-to-target/description/
	public List<List<Integer>> solution(int[][] graph) {
		List<List<Integer>> answer = new ArrayList<List<Integer>>();
			
		List<Integer> path = new ArrayList<>();
		path.add(0);
		travel(graph, 0, path, answer);		
		return answer;
	}
	
	public void travel(int[][] graph, int node, List<Integer> path, List<List<Integer>> answer) {
		
		if (node == graph.length - 1) {
			answer.add( new ArrayList<>(path));
			return;
		}
		
		for (int nextNode : graph[node]) {
			path.add(nextNode);
			travel(graph, nextNode, path, answer);
			path.remove(path.size() - 1);
		}		
	}
}

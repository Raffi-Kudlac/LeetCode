package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import helper.SolutionOutline;

public class LadderLength  extends SolutionOutline{
	public void runTest() {
		this.setClassName("LadderLength");
		this.setDifficulty("Medium");
		
		List<String> dictionary = buildDictionary();
		String start = "hit";
		String finish = "cog";
		this.setInput("Start: " + start + "\n\t" + "Finish: " + finish + "\n\t" + "Library: " + dictionary.toString());
		//List<String> answer = this.solution(start, finish, dictionary);
		List<String> answer = findShortestPathUsingGraph(start, finish, dictionary);
		this.setOutput("Path length: " + answer.size() + "\n\t List: " + Arrays.toString(answer.toArray()));
		this.printResult();
	} 
	
	public List<String> solution(String start, String finish, List<String> dictionary) {
		
		List<String> startPath = new ArrayList<String>();
		startPath.add(start);
		List<String> path = findPath(start, finish, dictionary, startPath);		
		
		if (path == null) {
			return new ArrayList<String>();
		}
		
		return path;
	}
	
	public List<String> findShortestPathUsingGraph(String start, String finish, List<String> dictionary) {
		
		Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);
		g.addVertex(start);
		
		for (String s: dictionary) {
			g.addVertex(s);
			for (String node : g.vertexSet()) {
				if (!node.equals(s) && withinOneChar(s, node)) {
					
					g.addEdge(node, s);
				}
			}			
		}		
		
		GraphPath<String, DefaultEdge> shortestPath = DijkstraShortestPath.findPathBetween(g, start, finish);
		List<String> list = shortestPath.getVertexList();		
		
		return list;
	}
	
	public List<String> buildDictionary() {
		List<String> dictionary = new ArrayList<String>();
		dictionary.add("hot");
		dictionary.add("dog");
		dictionary.add("dot");
		dictionary.add("lot");
		dictionary.add("log");
		dictionary.add("cog");
		return dictionary;
	}
	
	public List<String> findPath(String start, String end, List<String> dictionary, List<String> path) {
		
		if (start.equals(end)) {			
			return path;
		}
		List<String> possiblePaths = getAllWordsWithinOneChar(start, dictionary);
		List<String> temp;
		List<String> bestPath = null;
		List<String> nextPath;
		for (String word : possiblePaths) {
			
			if (path.contains(word)) {
				continue;
			}
			
			nextPath = new ArrayList<String>(path);
			nextPath.add(word);
			temp = findPath(word, end, dictionary, nextPath);
			if (temp != null) {
				if (bestPath == null) {
					bestPath = temp;
				} else if (bestPath.size() > temp.size()) {
					bestPath = temp;
				}
			}			
		}
		
		return bestPath;
	}
	
	public boolean withinOneChar(String target, String challenger) {
		
		char currentTarget;
		char currentChallenger;
		int counter = 0;
		for (int x = 0; x < target.length(); x++) {
			currentTarget = target.charAt(x);
			currentChallenger = challenger.charAt(x);
			if (currentTarget != currentChallenger) {
				counter++;
			}
		}
		
		return counter == 1;
	} 
	
	
	public List<String> getAllWordsWithinOneChar(String target, List<String> dictionary) {
		List<String> answer = new ArrayList<String>();
		
		int offChars = 0;
		for (String word : dictionary) {
			offChars = 0;
			for (int x = 0; x < word.length(); x++) {
				if (word.charAt(x) != target.charAt(x)) {
					offChars++;
				}
				if (offChars > 1) {
					break;
				}
			}
			
			if (offChars == 1) {
				answer.add(word);
			}
		}
		
		return answer;
	}
}

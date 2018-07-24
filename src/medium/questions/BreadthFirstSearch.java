package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.ClosestFirstIterator;

import helper.SolutionOutline;

public class BreadthFirstSearch extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("BreadthFirstSearch");
		this.setDifficulty("Medium");
		Graph<String, DefaultEdge> g = createStringGraph();
		
		this.setInput(g.toString());
		String answer = this.solution(g);
		this.setOutput(answer);
		this.printResult();
	}
	
	public String solution(Graph<String, DefaultEdge> g) {
		
		String start = "A";
		String answer = "";
		Queue<String> nodesToVisit = new LinkedList<String>();
		HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
		
		nodesToVisit.add(start);
		visited.put(start, true);
		String pointer;
		
		while(!nodesToVisit.isEmpty()) {
			pointer = nodesToVisit.remove();
			answer += ", " + pointer;
			ArrayList<String> neighbours = getNeighbours(g, pointer, visited);
			for (String s: neighbours) {
				visited.put(s, true);
			}					
			
			nodesToVisit.addAll(neighbours);			
		}
				
		return answer;
	}
	
	public ArrayList<String> getNeighbours(Graph<String, DefaultEdge> g, String vertex, 
			HashMap<String, Boolean> visited) {
		ArrayList<String> list = new ArrayList<String>();
		
		
		ClosestFirstIterator<String, DefaultEdge> neighbours = 
				new ClosestFirstIterator<String, DefaultEdge>(g, vertex, 1);
		
		String key;
		while(neighbours.hasNext()) {
			key = neighbours.next();
			if (!visited.containsKey(key) && key != vertex) {
				list.add(key);
			}
		}			
		
		return list;
	} 
	
	private Graph<String, DefaultEdge> createStringGraph()
    {
        Graph<String, DefaultEdge> g = new SimpleGraph<>(DefaultEdge.class);

        String A = "A";
        String B = "B";
        String C = "C";
        String D = "D";
        String E = "E";
        String F = "F";
        String G = "G";
        String H = "H";

        // add the vertices
        g.addVertex(A);
        g.addVertex(B);
        g.addVertex(C);
        g.addVertex(D);
        g.addVertex(E);
        g.addVertex(F);
        g.addVertex(H);
        g.addVertex(G);

        // add edges to create a circuit
        g.addEdge(A, B);
        g.addEdge(A, D);
        g.addEdge(A, G);
        g.addEdge(B, E);
        g.addEdge(B, F);
        g.addEdge(G, E);
        g.addEdge(D, F);
        g.addEdge(F, C);
        g.addEdge(C, H);        

        return g;
    }
}

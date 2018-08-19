package hard.questions;

import helper.SolutionOutline;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.traverse.ClosestFirstIterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BusRoutes extends SolutionOutline {

    public void runTest() {
        this.setClassName("BusRoutes");
        this.setDifficulty("Hard");

        int[][] test = {{1, 2, 7}, {3, 6, 7}};
        this.setInput(test);
        int answer = this.solution(test, 1, 6);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/bus-routes/description/
    public int solution(int[][] routes, int start, int stop) {
        DefaultDirectedGraph<Integer, Connection> graph = BuildGraph(routes);

        //getNeighbours(graph, 7);


        GraphPath<Integer, Connection> shortestPath =
                DijkstraShortestPath.findPathBetween(graph, start, stop);

        List<Connection> edges = shortestPath.getEdgeList();
        HashMap<Integer, Boolean> seenEdges = new HashMap<>();
        for (Connection c : edges) {
            seenEdges.putIfAbsent(c.route, true);
        }

        return seenEdges.size();
    }

    public void getNeighbours(
            DefaultDirectedGraph<Integer, Connection> g,
            Integer vertex) {
        ArrayList<String> list = new ArrayList<String>();


        ClosestFirstIterator<Integer, Connection> neighbours =
                new ClosestFirstIterator<>(g, vertex, 1);

        while(neighbours.hasNext()) {
            Integer i = neighbours.next();
            System.out.print(i + ", ");
        }
    }

    public DefaultDirectedGraph<Integer, Connection>
        BuildGraph(int[][] routes) {

        DefaultDirectedGraph<Integer, Connection> graph =
            new DefaultDirectedGraph<Integer, Connection>(Connection.class);
        int prevStop;

        for (int index = 0; index < routes.length; index++) {

            prevStop = routes[index][0];
            graph.addVertex(prevStop);

            for (int stop = 1; stop < routes[index].length; stop++) {
                graph.addVertex(routes[index][stop]);

                graph.addEdge(prevStop, routes[index][stop],
                        new Connection(index));
                prevStop = routes[index][stop];
            }

            int lastIndex = routes[index].length - 1;
            graph.addEdge(routes[index][lastIndex],
                routes[index][0], new Connection(index));
        }

        return graph;
    }

    public class Connection {
        int route;

        Connection(int r) {
            this.route = r;
        }
    }
}

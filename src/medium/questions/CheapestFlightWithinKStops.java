package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CheapestFlightWithinKStops extends SolutionOutline {

    public void runTest() {
        this.setClassName("CheapestFlightWithinKStops");
        this.setDifficulty("Medium");

        int[][] test = buildFlights();
        int src = 0; int dst = 2; int k = 2;
        this.setInput("Flights: \n\t" + TwoDIntArrayToString(test) + "\n Start: " + src + "\n finish: " + dst
        + "\n In k stops: " + k);
        int answer = this.solution(5, test, src, dst, k);
        this.setOutput(answer);
        this.printResult();
    }

    public int[][] buildFlights() {
        int[][] flights = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        return flights;
    }

    // https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
    public int solution(int n, int[][] flights, int src, int dst, int k) {

        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();

        int start, finish, price;
        ArrayList<Integer> flightData;
        for (int[] flight : flights) {
            start = flight[0]; finish = flight[1]; price = flight[2];
            if (!map.containsKey(start)) {
                map.put(start, new ArrayList<>());
            }

            flightData = new ArrayList<>();
            flightData.add(finish); flightData.add(price);
            map.get(start).add(flightData);
        }

        HashMap<Integer, Integer> dp = new HashMap<>();
        Integer answer = findLowestCost(map, src, dst, k, dp);
        return answer == null ? -1 : answer;
    }

    public Integer findLowestCost(HashMap<Integer, List<List<Integer>>> map, int src, int dst, int k,
                                  HashMap<Integer, Integer> dp) {

        if (src == dst) {
            return 0;
        } else if (!map.containsKey(src) || k < 0) {
            return null;
        } if (dp.containsKey(src)) {
            return dp.get(src);
        }

        int min = Integer.MAX_VALUE;
        Integer travel;
        for (List<Integer> paths : map.get(src)) {
            travel = findLowestCost(map, paths.get(0), dst, k - 1, dp);
            if (travel != null) {
                min = Math.min(travel + paths.get(1), min);
            }
        }

        dp.put(src, min == Integer.MAX_VALUE ? null : min);
        return dp.get(src);
    }
}

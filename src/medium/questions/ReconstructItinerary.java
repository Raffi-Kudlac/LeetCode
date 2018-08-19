package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import helper.SolutionOutline;

public class ReconstructItinerary extends SolutionOutline {
	
	int numTickets = 0;
	int ticketsUsed = 0;
	List<String> path = new ArrayList<>();
	
	public void runTest() {
		this.setClassName("ReconstructItinerary");
		this.setDifficulty("Medium");
		
		String[][] test = {{"JFK","SFO"},{"JFK","ATL"},{"SFO","ATL"},{"ATL","JFK"},{"ATL","SFO"}};		
		this.setInput(TwoDStringArrayToString(test));
		String[] answer = this.solution(test);
		this.setOutput(Arrays.toString(answer));
		this.printResult();
	} 
	
	
	// https://leetcode.com/problems/reconstruct-itinerary/description/
	public String[] solution(String[][] tickets) {
		HashMap<String, List<String>> map = new HashMap<>();
		List<String> list;
		for (String[] pair: tickets) {
			
			if (map.containsKey(pair[0])) {
				map.get(pair[0]).add(pair[1]);
			} else {
				list = new ArrayList<String>();
				list.add(pair[1]);
				map.put(pair[0], list);
			}
		}
		
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			Collections.sort(entry.getValue());
		}
		numTickets = tickets.length;
		path.add("JFK");
		findPath("JFK", map);
		return path.toArray( new String[path.size()]);
	}
	
	public void findPath(String airport, HashMap<String, List<String>> map) {
		
		if (!map.containsKey(airport)) {
			return;
		}
		
		List<String> dest = map.get(airport);
		
		for (int i = 0; i < dest.size(); i++) {
			String stop = dest.remove(i);
			
			path.add(stop);
			ticketsUsed++;
			findPath(stop, map);
			
			if (ticketsUsed == numTickets) {
				return;
			}
			dest.add(i, stop);
			ticketsUsed--;
			path.remove(path.size()-1);
		}
	}
}

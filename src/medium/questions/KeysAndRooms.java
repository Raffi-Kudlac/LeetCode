package medium.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import helper.SolutionOutline;

public class KeysAndRooms extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("KeysAndRooms");
		this.setDifficulty("Medium");
		
		List<List<Integer>> test = buildTest();		
		this.setInput(TwoDListToString(test));
		boolean answer = this.solution(test);
		this.setOutput(Boolean.toString(answer));
		this.printResult();
	} 
	
	public List<List<Integer>> buildTest() {		
		List<List<Integer>> rooms = new ArrayList<List<Integer>>();		
		rooms.add(new ArrayList<Integer>(Arrays.asList(1)));
		rooms.add(new ArrayList<Integer>(Arrays.asList(2)));
		rooms.add(new ArrayList<Integer>(Arrays.asList(3)));
		rooms.add(new ArrayList<Integer>());
		
		return rooms;
	}
	
	public boolean solution(List<List<Integer>> rooms) {
		
		Stack<Integer> roomsToVisit = new Stack<Integer>();
		roomsToVisit.add(0);
		
		boolean[] visitedRooms = new boolean[rooms.size()];	
		Arrays.fill(visitedRooms, false);
		
		
		while (roomsToVisit.size() != 0) {
			
			int room = roomsToVisit.pop();			
			if (visitedRooms[room] == false) {				
				List<Integer> keys = rooms.get(room);				
				roomsToVisit.addAll(keys);
				visitedRooms[room] = true;				
			}
		}
		
		int counter = 0;
		for (boolean b : visitedRooms) {
			if (b) {
				counter++;
			}
		}
		
		return counter == visitedRooms.length;
	}
}

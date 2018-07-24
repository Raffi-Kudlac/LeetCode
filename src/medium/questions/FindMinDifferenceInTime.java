package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import helper.SolutionOutline;

public class FindMinDifferenceInTime extends SolutionOutline{
	
	
	public void runTest() {
		this.setClassName("FindMinDifferenceInTime");
		this.setDifficulty("Medium");

		List<String> test = buildTestList();
		this.setInput(test.toString());
		int answer = this.solution(test);
		this.setOutput(Integer.toString(answer));
		this.printResult();
	}
	
	private List<String> buildTestList() {
		
		List<String> testList = new ArrayList<String>();
		testList.add("2:31");
		testList.add("4:03");
		testList.add("15:41");
		testList.add("0:04");
		testList.add("23:50");
		return testList;
	}
 	
	public int solution(List<String> timesPoints) {
		
		HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		
		for (String time: timesPoints) {
			map.put(convertStringTime(time), true);
		}
		
		int firstTime = -1;
		int currentMin = Integer.MAX_VALUE;
		int lastSeenTime = -1;
		int maxKey = 23*60 + 59;
		
		for (int x = 0; x <= maxKey; x++) {
			if (map.containsKey(x)) {
				if (firstTime == -1) {
					firstTime = x;
					lastSeenTime = x;
				} else {
					currentMin = Math.min(currentMin, x - lastSeenTime);
					lastSeenTime = x;
				}
			}
		}
		
		int lastGap = firstTime + (maxKey - lastSeenTime);
		currentMin = Math.min(currentMin, lastGap);
		return currentMin;
	}
	
	public int convertStringTime(String time) {
		String[] times = time.split(":");
		
		int minutes =  Integer.parseInt(times[1]);
		int hours = Integer.parseInt(times[0]) * 60;
		return hours + minutes;
	}
}

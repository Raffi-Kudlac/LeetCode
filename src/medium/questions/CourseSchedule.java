package medium.questions;

import java.util.ArrayList;
import java.util.HashMap;

import helper.SolutionOutline;

public class CourseSchedule extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("CourseSchedule");
		this.setDifficulty("Medium");
		
		int[][] test = buildCourseSchedule();		
		this.setInput(TwoDIntArrayToStirng(test));
		boolean answer = this.solution(4, test);
		this.setOutput(answer);
		this.printResult();
	}
	
	public int[][] buildCourseSchedule() {
		
		int[][] prerequisites = {
			{1, 4},
			{2, 4},
			{4, 1}
		};
		
		return prerequisites;
	}
	
	public boolean solution(int numCourses, int[][] prerequisites) {
		
		HashMap<Integer, Boolean> takenCourses = new HashMap<>();
		ArrayList<Integer> currentPath = new ArrayList<>();
		
		int index = 0;
		while (takenCourses.size() < numCourses && index < prerequisites.length) {
			int[] course = prerequisites[index];
			
			if (canTakeCourse(course, takenCourses, currentPath, prerequisites)) {				
				for (int courseId : currentPath) {
					takenCourses.put(courseId, true);
				}
			}
			index++;
		}		
		
		return takenCourses.size() >= numCourses;
	}
	
	public boolean canTakeCourse(int[] course, HashMap<Integer, Boolean> takenCourses, 
			ArrayList<Integer> currentPath, int[][] prerequisites) {
		
		if (takenCourses.containsKey(course[0])) {						
			return true;
		} else if (currentPath.contains(course[0])) {
			return false;		
		} else {
			currentPath.add(course[0]);
			int[] prerequisiteCourse = searchForCourse(course[1], prerequisites);
			
			if (prerequisiteCourse == null) {
				currentPath.add(course[1]);
				return true;
			}			
			return canTakeCourse(prerequisiteCourse, takenCourses, currentPath, prerequisites);
		}		
	}
	
	public int[] searchForCourse(int id, int[][] courses) {
		
		for (int[] course : courses) {
			if (course[0] == id) {
				return course;
			}
		}
		
		return null;
	}
}

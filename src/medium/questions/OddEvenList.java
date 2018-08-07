package medium.questions;

import helper.SolutionOutline;

public class OddEvenList extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("OddEvenList");
		this.setDifficulty("Medium");
		
		ListNode test = buildOrderedList(10);		
		this.setInput(test.printVal());
		ListNode answer = this.solution(test);
		this.setOutput(answer.printVal());
		this.printResult();
	} 
	
	// https://leetcode.com/problems/odd-even-linked-list/description/
	public ListNode solution(ListNode list) {
		
		ListNode odd = list;
		ListNode even = list.next;
		ListNode current = even.next;
		
		ListNode oddEnd = odd;
		ListNode evenEnd = even;
		
		int index = 3;
		while (current != null) {
			
			if (index % 2 == 0) {
				evenEnd.next = current;
				evenEnd = evenEnd.next;
			} else {
				oddEnd.next = current;
				oddEnd = oddEnd.next;
			}
			
			index++;
			current = current.next;
		}
		
		oddEnd.next = even;
		evenEnd.next = null;
		return odd;
	}	
}

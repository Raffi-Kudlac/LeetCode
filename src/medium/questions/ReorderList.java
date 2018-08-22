package medium.questions;

import java.util.ArrayList;

import helper.SolutionOutline;

public class ReorderList extends SolutionOutline {
	
	/**
	 * 	Idea
	 * 
	 * 		 Separate the list into two lists, one holding the even indexes and one
	 * 		 holding the odd indexes. Then bounce between the two lists creating the new
	 * 		 correct list.
	 */
	
	public void runTest() {
		this.setClassName("ReorderList");
		this.setDifficulty("Medium");
		
		ListNode test = buildOrderedList(9);		
		this.setInput(test.printVal());
		ListNode answer = this.solution(test);
		this.setOutput(answer.printVal());
		this.printResult();
	}
	
	// https://leetcode.com/problems/reorder-list/description/
	public ListNode solution(ListNode head) {
		
		ArrayList<ListNode> odd = new ArrayList<>();
		ArrayList<ListNode> even = new ArrayList<>();
		
		ListNode pointer = head;
		ListNode tempNode;
		ArrayList<ListNode> temp = odd;
		while (pointer != null) {
			tempNode = pointer;
			pointer = pointer.next;
			tempNode.next = null;
			
			temp.add(tempNode);
			temp = temp == odd ? even : odd;			
		}
		
		ListNode newHead = new ListNode(-1);
		pointer = newHead;
		ArrayList<ListNode> top = odd;
		ArrayList<ListNode> bottom = odd.size() == even.size() ? even : odd;
		int limit = top == bottom ? 1 : 0;
		
		while (bottom.size() != limit) {
			pointer.next = top.remove(0);
			pointer = pointer.next;
			top = top == even ? odd : even;			
			
			pointer.next = bottom.remove(bottom.size() - 1);
			pointer = pointer.next;
			bottom = bottom == even ? odd : even;
		}
		
		if (odd.size() == 1) {
			pointer.next = odd.get(0);
		}
		
		return newHead.next;
	}
}

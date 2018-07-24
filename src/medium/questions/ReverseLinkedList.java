package medium.questions;

import helper.SolutionOutline;

public class ReverseLinkedList extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("ReverseLinkedList");
		this.setDifficulty("Medium");
		
		privateList test = createList();
		this.setInput(stringList(test));
		privateList answer = this.solution(test);
		this.setOutput(stringList(answer));
		this.printResult();
	}
	
	public privateList solution(privateList head) {
		
		privateList oldHead, newHead, current;
		newHead = head;		
		current = newHead.next;
		oldHead = current.next;
		newHead.next = null;
		while (current != null) {
						
			current.next = newHead;						
			newHead = current;
			current = oldHead;
			if (current != null) {
				oldHead = current.next;
			}
		}
		
		return newHead;
		
	}
	
	public String stringList(privateList head) {
		String answer = head.getString();
		
		privateList pointer = head.next;
		
		while(pointer != null) {			
			answer = answer + " -> " + pointer.getString();
			pointer = pointer.next;
		}
		
		return answer;
	}
	
	public privateList createList() {
		
		privateList head = new privateList(0, null);
		
		for (int x = 1; x < 5; x++) {
			addValueToList(head, x);
		}
		
		return head;
	}
	
	public void addValueToList(privateList head, int v) {
		
		privateList pointer = head;
		
		while (pointer.next != null) {
			pointer = pointer.next;
		}
		
		pointer.next = new privateList(v, null);
	}
	
	
	class privateList {
		privateList next;
		int s;
		
		privateList(int s, privateList node) {
			this.s = s;
			this.next = node;
		}
		
		public String getString() {
			return "(" + Integer.toString(s) + ")";
		} 
	}
}

package medium.questions;

import helper.SolutionOutline;

public class RemoveNthLastNode extends SolutionOutline {
	
	public void runTest() {
		this.setClassName("RemoveNthLastNode");
		this.setDifficulty("Medium");
		
		ListNode test = buildList();
		int n = 1;
		this.setInput(test.toString() + ", remove: " + n);
		ListNode answer = this.solution(test, n);
		this.setOutput(answer.toString());
		this.printResult();
	} 
	
	public ListNode solution(ListNode head, int n) {
		ListNode trailing = head;
        ListNode leading = head;       
        
        for (int x = 0; x < n; x++) {
            if (leading == null) {
                return null;
            }
            leading = leading.next;
        }
        
        if (leading == null) {
            return head.next;
        }
        
        while (leading.next != null) {
            leading = leading.next;
            trailing = trailing.next;
        }
                
        ListNode rest;
        if (trailing.next == null) {
            rest = null;
        } else {
            rest = trailing.next.next;
        }
                
        trailing.next = rest;        
        return head;        
	}
	
	public ListNode buildList() {
		ListNode head = new ListNode(null, 1);
		ListNode pointer = head;
		int size = 5;
		for (int x = 2; x <= size; x++) {
			pointer.next = new ListNode(null, x);
			pointer = pointer.next;
		}
		
		return head;
	}
	
	public class ListNode {
		ListNode next;
		int val;
		
		ListNode(ListNode node, int v) {
			next = node;
			val = v;				
		}
		
		public String toString() {
			
			if (next == null) {
				return Integer.toString(val);
			} else {
				return Integer.toString(val) + ", " + next.toString();
			}			
		}
	}
}

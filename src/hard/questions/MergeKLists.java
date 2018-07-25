package hard.questions;

import helper.SolutionOutline;

/**
 * The Idea
 * 		
 * 		Create a new node that will be the beginning of the answer list. (The result of two
 * 		merged lists). Loop through two lists at a time and at the minimum of the heads of each
 * 		list to the answer list. Take the minimum out of one list and put it into the answer list.
 * 		Do this until both lists now exist in the master list.
 *  
 * @author Raffi Kudlac
 *
 */


public class MergeKLists extends SolutionOutline {
		
	public void runTest() {
		this.setClassName("MergeKLists");
		this.setDifficulty("Hard");
		
		ListNode[] lists = buildLists();		
		this.setInput(printLists(lists));
		ListNode answer = this.solution(lists);
		this.setOutput(answer.printVal());
		this.printResult();
	}
	
	public ListNode[] buildLists() {
		ListNode[] answer = new ListNode[3];
		int max = 100;
		
		answer[0] = buildOrderedList(5, max);
		answer[1] = buildOrderedList(5, max);
		answer[2] = buildOrderedList(5, max);
		return answer;
	}
	
	public ListNode solution(ListNode[] lists) {
				
		ListNode answer = mergeTwoLists(lists[0], lists[1]);
        for (int x = 2; x < lists.length; x++) {
            answer = mergeTwoLists(answer, lists[x]);
        }
        
        return answer; 
	}
	
	// https://leetcode.com/problems/merge-k-sorted-lists/description/
	public ListNode mergeTwoLists(ListNode list1, ListNode list2) {		
		
        ListNode masterList = new ListNode(0);
        ListNode pointer1, pointer2, masterPointer;
        
        pointer1 = list1;
        pointer2 = list2;                
        masterPointer = masterList;
        
        while (pointer1 != null || pointer2 != null) {
            
            if (pointer1 == null) {
                pointer2 = insertNode(masterPointer, pointer2);                
            } else if (pointer2 == null) {
                pointer1 = insertNode(masterPointer, pointer1);                
            } else {                
                if (pointer1.val < pointer2.val) {
                    pointer1 = insertNode(masterPointer, pointer1);                    
                } else if (pointer2.val <= pointer1.val) {
                    pointer2 = insertNode(masterPointer, pointer2);
                }                
            }
            
            masterPointer = masterPointer.next;
        }
        
        return masterList.next;
    }

	public ListNode insertNode(ListNode masterPointer, ListNode node) {
	    
	    ListNode targetNode = node;
	    ListNode newHead = node.next;
	    targetNode.next = null;
	    
	    masterPointer.next = targetNode;                
	    return newHead;
	}
}

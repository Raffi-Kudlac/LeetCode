package hard.questions;

import helper.SolutionOutline;

/**
 * The question stated that recursion had to be used
 * 
 * The Idea
 * 		
 * 		Recursively go down the list. At each step, remove the head node and claim to
 * 		insert it into the rest of the list. This will continue until we hit the base case of
 * 		one element which can not be sorted. On the way up the recursion (after the rest of the
 * 		array is sorted) insert the "head" node into the now sorted list. This is done by 
 * 		Recursively searching for the node to insert beside.
 *  
 * @author Raffi Kudlac
 *
 */


public class SortLinkedList extends SolutionOutline {
	
	
	public void runTest() {
		this.setClassName("SortLinkedList");
		this.setDifficulty("Hard");
		
		ListNode test = buildUnorderedList(20, 100);		
		this.setInput(test.printVal());		
		ListNode answer = this.solution(test);
		this.setOutput(answer.printVal());
		this.printResult();
	} 
	
	public ListNode solution(ListNode list) {
		ListNode head = list;
		ListNode restOfList = list.next;
		head.next = null;
		return insertNodeInOrder(restOfList, head);		
	}
	
	public ListNode insertNodeInOrder(ListNode list, ListNode nodeToInsert) {
		
		if (list == null) {
			return nodeToInsert;
		}
		ListNode head = list;
		ListNode restOfList = list.next;
		head.next = null;		
				
		ListNode sortedList = insertNodeInOrder(restOfList, head);		
		ListNode insertAfterThisNode = 	findLastSmallestNode(sortedList, nodeToInsert.val);
		
		if (insertAfterThisNode == null) {
			nodeToInsert.next = sortedList;
			return nodeToInsert;
		} else {
			nodeToInsert.next = insertAfterThisNode.next;
			insertAfterThisNode.next = nodeToInsert;			
		}
		
		return sortedList;		
	}
	
	public ListNode findLastSmallestNode(ListNode node, int target) {
		
		// means that the head is larger than the target
		if (node.val > target) {
			return null;
		}		
		
		// means that all values in the list are smaller than the target
		if (node.next == null) {
			return node;
		}
		
		// the next node is larger than the target and the current node is smaller
		if (node.next.val >= target) {
			return node;
		}
		
		return findLastSmallestNode(node.next, target);
	}
}

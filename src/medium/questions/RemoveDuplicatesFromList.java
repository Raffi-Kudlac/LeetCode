package medium.questions;

import helper.SolutionOutline;

public class RemoveDuplicatesFromList extends SolutionOutline {

    public void runTest() {
        this.setClassName("RemoveDuplicatesFromList");
        this.setDifficulty("Medium");

        ListNode test = buildOrderedList(10, 100);
        this.setInput(test.printVal());
        ListNode answer = this.solution(test);
        this.setOutput(answer.printVal());
        this.printResult();
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
    public ListNode solution(ListNode head) {

        ListNode current = head;
        while (current != null && current.next != null) {
            if (current.next.val == current.val) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }
        return head;
    }
}

package medium.questions;

import helper.SolutionOutline;

public class RotateLinkedList extends SolutionOutline {

    public void runTest() {
        this.setClassName("RotateLinkedList");
        this.setDifficulty("Medium");
        int shift = 4;
        ListNode test = buildOrderedList(5);
        this.setInput("List: " + test.printVal() + "\n\tshift: " + shift);
        ListNode answer = this.solution(test, shift);
        this.setOutput(answer.printVal());
        this.printResult();
    }

    // https://leetcode.com/problems/rotate-list/description/
    public ListNode solution(ListNode head, int shift) {

        if (shift == 0) {
            return head;
        }
        ListNode pointer = head;
        ListNode tail = null;
        // find node shift + 1 from the end
        int target = shift + 1;
        int counter = 1;

        while (pointer.next != null) {
            pointer = pointer.next;
            tail = tail == null ? null : tail.next;
            counter++;
            if (counter == target) {
                tail = head;
            }
        }
        ListNode newHead = tail.next;
        tail.next = null;
        pointer.next = head;

        return newHead;
    }
}

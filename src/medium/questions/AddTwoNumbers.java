package medium.questions;

import helper.SolutionOutline;

public class AddTwoNumbers extends SolutionOutline {

    public void runTest() {
        this.setClassName("AddTwoNumbers");
        this.setDifficulty("Medium");

        ListNode num1 = buildUnorderedList(4, 9);
        ListNode num2 = buildOrderedList(3, 9);
        this.setInput(num1.printVal() + "\n\t" + num2.printVal());
        ListNode answer = this.solution(num1, num2);
        this.setOutput(answer.printVal());
        this.printResult();
    }

    public ListNode solution(ListNode l1, ListNode l2) {
        int extra = 0;
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode answer = new ListNode(-1);
        ListNode ap = answer;
        int sum, p1Val = 0, p2Val = 0;

        while (p1 != null || p2 != null) {

            if (p1 == null) {
                p2Val = p2.val;
                p2 = p2.next;
            } else if (p2 == null) {
                p1Val = p1.val;
                p1 = p1.next;
            } else {
                p2Val = p2.val;
                p2 = p2.next;

                p1Val = p1.val;
                p1 = p1.next;
            }

            sum = p1Val + p2Val + extra;

            if (sum < 10) {
                ap.next = new ListNode(sum);
                extra = 0;
            } else {
                extra = 1;
                ap.next = new ListNode(sum - 10);
            }

            p1Val = 0;
            p2Val = 0;
            ap = ap.next;
        }

        return answer.next;
    }
}

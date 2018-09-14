package medium.questions;

import helper.SolutionOutline;

public class LinkedListToBST extends SolutionOutline {

    public void runTest() {
        this.setClassName("LinkedListToBST");
        this.setDifficulty("Medium");

        ListNode list = buildOrderedList(11, 100);
        this.setInput(list.printVal());
        TreeNode answer = this.solution(list);
        this.setOutput(serialize(answer));
        this.printResult();
    }

    public TreeNode solution(ListNode head) {

        int length = getLength(head);
        return traverse(head, length);
    }

    public TreeNode traverse(ListNode node, int length) {

        if (length == 1) {
            return new TreeNode(node.val);
        } else if (length == 2) {
            TreeNode left = new TreeNode(node.val);
            TreeNode tree = new TreeNode(node.next.val);
            tree.left = left;
            return tree;
        }

        int middleIndex = (length / 2) + 1;
        ListNode left = getNode(node, middleIndex - 1);
        ListNode middle = left.next;
        ListNode right = middle.next;
        left.next = null;

        TreeNode tree = new TreeNode(middle.val);

        tree.left = traverse(node, middleIndex - 1);
        tree.right = traverse(right, length - middleIndex);

        return tree;
    }

    public ListNode getNode(ListNode node, int index) {

        ListNode pointer = node;
        int counter = 1;
        while(counter < index && pointer != null) {
            counter++;
            pointer = pointer.next;
        }

        return counter == index ? pointer : null;
    }

    public int getLength(ListNode node) {

        ListNode pointer = node;
        int counter = 0;
        while(pointer != null) {
            counter++;
            pointer = pointer.next;
        }

        return counter;
    }
}

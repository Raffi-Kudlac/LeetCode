package CrackIngTheCodingInterview.Chapter4;

import helper.SolutionOutline;

import java.util.LinkedList;
import java.util.Queue;

public class FindNextNode extends SolutionOutline {

    boolean thirdTraverse = false;

    public void runTest() {
        this.setClassName("FindNextNode");
        this.setDifficulty("Medium");

        TreeNode test = buildBST(10);
        int target = 4;
        this.setInput(serialize(test) + "\n\tTarget: " + target);
        TreeNode answer = this.solution(test, target);
        this.setOutput(answer.printVal());
        this.printResult();
    }

    public TreeNode solution(TreeNode root, int target) {

        if (root.val == target) {
            return root.right;
        }

        Queue<TreeNode> queue = new LinkedList<>();

        return thirdTraverse(root, target);

        //betterTraverse(root, queue, target);
        //traverse(root, queue, target);
//        if (isFound(queue, target)) {
//            queue.remove();
//            return queue.peek();
//        }
//        return null;
    }

    public TreeNode thirdTraverse(TreeNode node, int target) {

        if (node == null) {
            return null;
        } else if (node.val == target && node.right != null) {
            return node.right;
        } else if (node.val == target && node.right == null) {
            thirdTraverse = true;
            return null;
        }

        if (target > node.val) {
            return thirdTraverse(node.right, target);
        } else if (target < node.val) {
            TreeNode ans = thirdTraverse(node.left, target);
            if (ans != null) {
                return ans;
            } else if (thirdTraverse == true && node.val > target) {
                return node;
            }
        }

        return null;
    }

    public void betterTraverse(TreeNode node, Queue<TreeNode> queue,
           int target) {

        if (node == null || isFound(queue, target)) {
            return;
        }

        if (target > node.val) {

            betterTraverse(node.right, queue, target);
        } else if (target < node.val) {

            betterTraverse(node.left, queue, target);
            addToQueue(queue, target,node);
        } else if (target == node.val && node.right != null) {

            addToQueue(queue, target, node);
            addToQueue(queue, target, node.right);
        } else {

            addToQueue(queue, target, node);
        }
    }

    public void traverse(TreeNode node, Queue<TreeNode> queue,
         int target) {

        if (node == null || isFound(queue, target)) {
            return;
        }
        traverse(node.left, queue, target);

        if (addToQueue(queue, target,node)){
            return;
        }

        traverse(node.right, queue, target);
    }

    public boolean addToQueue(Queue<TreeNode> queue, int target,
                              TreeNode node) {

        queue.add(node);
        if (queue.size() == 3) {
            queue.remove();
        }

        return queue.peek().val == target;
    }

    public boolean isFound(Queue<TreeNode> queue, int target){
        if (queue.peek() == null) {
            return false;
        }
        return queue.peek().val == target;
    }
}

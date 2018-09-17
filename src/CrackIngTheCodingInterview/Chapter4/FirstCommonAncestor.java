package CrackIngTheCodingInterview.Chapter4;

import helper.SolutionOutline;

public class FirstCommonAncestor extends SolutionOutline {

    public void runTest() {
        this.setClassName("FirstCommonAncestor");
        this.setDifficulty("Medium");

        TreeNode test = buildBST(100);
        TreeNode n1 = new TreeNode(88);
        TreeNode n2 = new TreeNode(66);
        this.setInput(serialize(test));
        TreeNode answer = this.solution(test, n1, n2);
        this.setOutput(answer.printVal());
        this.printResult();
    }

    public TreeNode solution(TreeNode root, TreeNode n1,
             TreeNode n2) {

        return traverse(root, n1, n2);
    }

    public TreeNode traverse(TreeNode node, TreeNode n1, TreeNode n2){

        if (node.val == n1.val || node.val == n2.val) {
            return node;
        }
        boolean left = containsNode(node.left, n1);
        boolean right = containsNode(node.left, n2);

        // both nodes are on the left side
        if (left && right) {
            return traverse(node.left, n1, n2);
            // one node is to the left and one node is to the right
        } else if ((left && !right) || (!left && right)) {
            return node;
            // both nodes are to the right
        } else if (!left && !right) {
            return traverse(node.right, n1, n2);
        }

        return null;
    }

    public boolean containsNode(TreeNode node, TreeNode target) {

        if (node == null) {
            return false;
        } else if (node.val == target.val) {
            return true;
        }

        boolean onLeft = containsNode(node.left, target);
        return onLeft ? onLeft : containsNode(node.right, target);
    }
}

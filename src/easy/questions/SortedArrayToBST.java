package easy.questions;

import helper.SolutionOutline;

import java.util.Arrays;

public class SortedArrayToBST extends SolutionOutline {


    public void runTest() {
        this.setClassName("SortedArrayToBST");
        this.setDifficulty("Easy");

        int test[] = buildOrderedArray(10, 100);
        this.setInput(Arrays.toString(test));
        TreeNode answer = this.solution(test);
        this.setOutput(serialize(answer));
        this.printResult();
    }

    public TreeNode solution(int[] array) {

        return buildTree(array);
    }

    public TreeNode buildTree(int[] array) {

        if (array.length == 1) {
            return new TreeNode(array[0]);
        } else if (array.length == 2) {
            TreeNode root = new TreeNode(array[1]);
            root.left = new TreeNode(array[0]);
            return root;
        }

        int middle = array.length / 2;

        TreeNode root = new TreeNode(array[middle]);

        root.left = buildTree(Arrays.copyOfRange(array, 0, middle));
        root.right = buildTree(Arrays.copyOfRange(array, middle + 1, array.length));

        return root;
    }
}

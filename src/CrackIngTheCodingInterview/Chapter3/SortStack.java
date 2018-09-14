package CrackIngTheCodingInterview.Chapter3;

import helper.SolutionOutline;

import java.util.Arrays;
import java.util.Stack;

public class SortStack extends SolutionOutline {

    public void runTest() {
        this.setClassName("SortStack");
        this.setDifficulty("Easy");

        Stack<Integer> test = buildStack();
        this.setInput(Arrays.toString(test.toArray()));
        this.solution(test);
        this.setOutput(Arrays.toString(test.toArray()));
        this.printResult();
    }

    public Stack<Integer> buildStack() {
        Stack<Integer> stack = new Stack<>();

        for (int x = 0; x < 10; x++) {
            stack.push((int) (Math.random() * 100));
        }

        return stack;
    }

    public void solution(Stack<Integer> stack ) {
        sortStack(stack);
    }

    public void sortStack(Stack<Integer> stack) {
        Stack<Integer> clone = (Stack) stack.clone();

        stack.clear();
        stack.push(clone.pop());

        while (clone.size() > 0) {
            insertVal(stack, clone.pop());
        }
    }

    public void insertVal(Stack<Integer> stack, int i) {

        Stack<Integer> temp = new Stack<>();

        while (stack.size() > 0 && stack.peek() > i) {
            temp.push(stack.pop());
        }

        stack.push(i);
        while (temp.size() > 0) {
            stack.push(temp.pop());
        }
    }
}

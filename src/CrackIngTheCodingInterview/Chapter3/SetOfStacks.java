package CrackIngTheCodingInterview.Chapter3;

import helper.SolutionOutline;

import java.util.Stack;

public class SetOfStacks extends SolutionOutline {

    Stack<Stack> setOfStacks = new Stack<>();
    final int limit = 10;

    SetOfStacks(){
        setOfStacks.push(new Stack<Integer>());
    }

    public void push(Integer i) {
        Stack<Integer> last = setOfStacks.peek();
        if (last.size() < limit) {
            last.push(i);
        } else {
            last = new Stack<>();
            last.push(i);
            setOfStacks.push(last);
        }
    }

    public Integer pop () {
        Stack<Integer> last = setOfStacks.peek();

        if (last.size() == 0) {
            return null;
        }

        Integer result = last.pop();
        if (last.size() == 0 && setOfStacks.size() > 1) {
            setOfStacks.pop();
        }
        return result;
    }

    public Integer popAt(int index) {
        if (index >= setOfStacks.size()) {
            return null;
        } else if (index == setOfStacks.size() - 1) {
            return pop();
        }

        Stack<Stack> tempHolder = new Stack<>();

        int pops = setOfStacks.size() - index - 1;

        for (int x = 0; x < pops; x++) {
            tempHolder.push(setOfStacks.pop());
        }

        Integer result = pop();

        while (tempHolder.size() > 0) {
            setOfStacks.push(tempHolder.pop());
        }
        return result;
    }
}

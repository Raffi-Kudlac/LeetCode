package medium.questions;

import helper.SolutionOutline;
import java.util.HashMap;
import java.util.Stack;

public class WaterAndBucketProblem extends SolutionOutline {

    public void runTest() {
        this.setClassName("WaterAndBucketProblem");
        this.setDifficulty("Medium");

        int b1 = 5;
        int b2 = 3;
        int target = 4;
        this.setInput("bucket1: " + b1 + "\n\tbucket2: " + b2 + "\n\tTarget: " + target);
        boolean answer = this.solution3(b1, b2, target);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/water-and-jug-problem/description/
    public boolean solution(int x, int y, int target) {
        Bucket bx = new Bucket(x, 0);
        Bucket by = new Bucket(y, 0);
        HashMap<String, Boolean> seen = new HashMap<>();

        return makeChoice(seen, bx, by, target);
    }

    public boolean solution3(int x, int y, int target) {
        HashMap<String, Boolean> map = new HashMap<>();
        Stack<int[]> stack = new Stack<>();
        stack.add(new int[]{0, 0});
        int[] current;
        String key;
        int sum = 0;

        while (sum != target && !stack.empty()) {
            current = stack.pop();
            sum = current[0] + current[1];
            key = current[0] + "+" + current[1];

            if (map.containsKey(key) || sum == target) {
                continue;
            }
            map.put(key, true);

            // fill bucket X
            stack.add(new int[]{x,current[1]});

            // fill bucket Y
            stack.add(new int[]{current[0], y});

            // empty bucket Y
            stack.add(new int[]{current[0], 0});

            // empty bucket X
            stack.add(new int[]{0, current[1]});

            // pour y into x
            stack.add(pour(current[0], current[1], x));

            // pour x into y
            stack.add(pour(current[1], current[0], y));
        }

        return sum == target;
    }

    // pours Y into X
    public int[] pour(int x, int y, int xSize) {
        int space = xSize - x;
        if (space >= y) {
            x += y;
            y = 0;
        } else {
            x += space;
            y -= space;
        }

        return new int[] {x, y};
    }

    // iterative solution
    public boolean solution2(int x, int y, int target) {

        HashMap<String, Boolean> map = new HashMap<>();
        Stack<Bucket[]> stack = new Stack<>();
        Bucket[] current;
        String key;
        Bucket tempX, tempY;
        stack.add(new Bucket[]{
                new Bucket(x, 0),
                new Bucket(y, 0)
        });
        int sum = 0;
        while (sum != target && !stack.empty()) {
            current = stack.pop();
            sum = current[0].contains + current[1].contains;
            key = current[0].contains + "+" + current[1].contains;

            if (map.containsKey(key) || sum == target) {
                continue;
            }
            map.put(key, true);

            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);

            // fill tempX
            tempX.fill(-1);
            stack.add(new Bucket[]{tempX, tempY});

            // fill tempY
            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);
            tempY.fill(-1);
            stack.add(new Bucket[]{tempX, tempY});

            // empty tempX
            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);
            tempX.empty(-1);
            stack.add(new Bucket[]{tempX, tempY});

            // empty tempY
            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);
            tempY.empty(-1);
            stack.add(new Bucket[]{tempX, tempY});

            // pour tempX into tempY
            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);
            tempY.pour(tempX);
            stack.add(new Bucket[]{tempX, tempY});

            // pour tempY into tempX
            tempX = new Bucket(current[0]);
            tempY = new Bucket(current[1]);
            tempX.pour(tempY);
            stack.add(new Bucket[]{tempX, tempY});
        }
        return sum == target ;
    }

    // uses recursion
    public boolean makeChoice(HashMap<String, Boolean> seen, Bucket bx, Bucket by, int target){

        String key = bx.contains + "+" + by.contains;

        if (seen.containsKey(key)) {
            return false;
        } else if (bx.contains + by.contains == target) {
            return true;
        }

        seen.put(key, true);

        boolean response;
        Bucket tempBx, tempBy;

        //empty bx
        tempBx = new Bucket(bx);
        tempBx.empty(-1);
        response = makeChoice(seen, tempBx, by, target);

        // fill bx
        if (!response) {
            tempBx = new Bucket(bx);
            tempBx.fill(-1);
            response = makeChoice(seen, tempBx, by, target);
        }

        // pour by into bx
        if (!response) {
            tempBx = new Bucket(bx);
            tempBy = new Bucket(by);
            tempBx.pour(by);
            response = makeChoice(seen, tempBx, tempBy, target);
        }

        // empty by
        if (!response) {
            tempBy = new Bucket(by);
            tempBy.empty(-1);
            response = makeChoice(seen, bx, tempBy, target);
        }

        // fill by
        if (!response) {
            tempBy = new Bucket(by);
            tempBy.fill(-1);
            response = makeChoice(seen, bx, tempBy, target);
        }

        // pour bx into by
        if (!response) {
            tempBy = new Bucket(by);
            tempBx = new Bucket(bx);
            tempBy.pour(tempBx);
            response = makeChoice(seen, tempBx, tempBy, target);
        }

        return response;
    }

    public class Bucket {
        int size;
        int contains;

        Bucket(int s, int c) {
            size = s;
            contains = c;
        }

        Bucket(Bucket b) {
            this.size = b.size;
            this.contains = b.contains;
        }

        // return amount taken out
        public int empty(int amount) {
            amount = amount == -1 ? size : amount;
            int out = amount < contains ? amount : contains;
            contains = Math.max(0, contains - amount);

            return out;
        }

        public void fill(int amount) {

            if(amount == -1) {
                amount = size;
            }

            contains += amount;
            contains = Math.min(size, contains);
        }

        // taking from bucket b and putting into this bucket
        public void pour(Bucket b) {
            int space = size - contains;
            fill(b.empty(space));
        }
    }
}

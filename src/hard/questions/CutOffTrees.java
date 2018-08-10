package hard.questions;

import helper.SolutionOutline;

import java.util.*;

public class CutOffTrees extends SolutionOutline {

    public void runTest() {
        this.setClassName("CutOffTrees");
        this.setDifficulty("Medium");

        int[][] test = buildGolfCourse();
        this.setInput(test);
        int answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public int[][] buildGolfCourse(){
        int[][] course = {
            {2, 3, 4},
            {0, 0, 5},
            {8, 7, 6}
        };

        return course;
    }

    // https://leetcode.com/problems/cut-off-trees-for-golf-event/
    public int solution(int[][] course) {

        int answer = 0;
        Coords start = new Coords(0,0, course);
        Coords finish;
        Coords[] trees = findAllTrees(course);

        for (int index = 0; index < trees.length; index++) {
            finish = trees[index];
            answer += findShortestPath(course, start, finish, 0,
                    new HashMap<>());

            course[finish.x][finish.y] = 1;
            start = finish;
            finish.val = 1;
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }

    public Coords[] findAllTrees(int[][] course){
        List<Coords> list = new ArrayList<>();
        int val;

        for (int x = 0; x < course.length; x++) {
            for (int y = 0; y < course[x].length; y++) {
                val = course[x][y];

                if (val > 1) {
                    list.add(new Coords(x, y, course));
                }
            }
        }
        Coords[] answer = new Coords[list.size()];
        list.toArray(answer);

        Arrays.sort(answer, new Comparator<Coords>() {

            @Override
            public int compare(Coords o1, Coords o2) {

                if (o1.val > o2.val) {
                    return 1;
                } else if (o1.val < o2.val) {
                    return -1;
                }

                return 0;
            }
        });
        return answer;
    }

    public int findShortestPath(int[][] course, Coords start, Coords finish,
                int dist, HashMap<Coords, Boolean> visited) {

        try {
            if (finish.equals(start)) {
                return dist;
            } else if (visited.containsKey(start) ||
                    course[start.x][start.y] != 1) {
                return Integer.MAX_VALUE;
            }

            visited.put(start, true);
            // go up
            int x = start.x - 1;
            int y = start.y;
            int up = findShortestPath(course, new Coords(x, y, course),
                    finish, dist + 1, visited);

            // go down
            x = start.x + 1;
            y = start.y;
            int down = findShortestPath(course, new Coords(x, y, course),
                    finish, dist + 1, visited);

            // go right
            x = start.x;
            y = start.y + 1;
            int right = findShortestPath(course, new Coords(x, y, course),
                    finish, dist + 1, visited);

            // go left
            x = start.x;
            y = start.y - 1;
            int left = findShortestPath(course, new Coords(x, y, course),
                    finish, dist + 1, visited);

            int answer = Math.min(up, down);
            answer = Math.min(answer, left);
            answer = Math.min(answer, right);

            visited.remove(start);
            return answer;

        } catch(Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    private class Coords {
        int x;
        int y;
        int val;

        Coords(int x, int y, int[][] course) {
            this.x = x;
            this.y = y;

            try {
                this.val = course[x][y];
            } catch(Exception e) {
                this.val = Integer.MAX_VALUE;
            }
        }

        @Override
        public int hashCode(){
            return Objects.hash(x,y,val);
        }

        @Override
        public boolean equals(Object o1) {

            if (o1 == this) {
                return true;
            }

            if (!(o1 instanceof Coords)) {
                return false;
            }

            Coords c = (Coords) o1;
            return this.val == c.val && this.x == c.x &&
                    this.y == c.y;
        }
    }
}

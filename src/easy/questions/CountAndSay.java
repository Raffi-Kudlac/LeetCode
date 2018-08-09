package easy.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.List;

public class CountAndSay extends SolutionOutline {


    public void runTest() {
        this.setClassName("CountAndSay");
        this.setDifficulty("Easy");

        int test = 10;
        this.setInput(test);
        String answer = this.solution(test);
        this.setOutput(answer);
        this.printResult();
    }

    public String solution(int n) {
        String current = "1";

        for (int counter = 1; counter < n; counter++) {
            current = buildNextLevel(current);
        }

        return current;
    }

    public String buildNextLevel(String s) {
        List<Group> list = new ArrayList<>();
        char current = s.charAt(0);
        int counter = 1;
        char now;

        for (int index = 1; index < s.length(); index ++) {
            now = s.charAt(index);

            if (now == current) {
                counter++;
            } else {
                list.add(new Group(current, counter));
                current = now;
                counter = 1;
            }
        }
        list.add(new Group(current, counter));

        String level = "";
        for (Group g : list) {
            level += Integer.toString(g.occurrences) + g.character;
        }

        return level;
    }

    private class Group {
        char character;
        int occurrences;

        Group(char cc, int nn) {
            character = cc;
            occurrences = nn;
        }
    }
}

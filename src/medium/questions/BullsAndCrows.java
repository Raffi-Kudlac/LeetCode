package medium.questions;

import helper.SolutionOutline;

import java.util.HashMap;

public class BullsAndCrows extends SolutionOutline {

    public void runTest() {
        this.setClassName("BullsAndCrows");
        this.setDifficulty("Medium");

        String guess = "7810";
        String secret = "1807";
        this.setInput("Guess: " + guess + "\n\t" + "Answer: " + secret);
        String answer = this.solution(guess, secret);
        this.setOutput(answer);
        this.printResult();
    }

    // https://leetcode.com/problems/bulls-and-cows/description/
    public String solution(String guess, String secret) {

        // direct hits, letter 'A'
        int bulls = 0;
        // indirect hits, letter 'B'
        int cows = 0;

        HashMap<Character, Integer> map = new HashMap<>();

        for (char c: secret.toCharArray()) {
            if (map.putIfAbsent(c, 1) != null) {
                map.put(c, map.get(c) + 1);
            }
        }

        char c;
        for (int index = 0; index < guess.length(); index++) {
            c = guess.charAt(index);

            if (!map.containsKey(c)) {
                continue;
            }

            if (c == secret.charAt(index)) {

                if (map.get(c) <= 0) {
                    cows--;
                }

                bulls++;
            } else if (map.get(c) > 0) {
                cows++;
            }

            map.put(c, map.get(c) - 1);
        }

        return bulls + "A" + cows + "B";
    }
}

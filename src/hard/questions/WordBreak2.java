package hard.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak2 extends SolutionOutline {

    public void runTest() {
        this.setClassName("WordBreak2");
        this.setDifficulty("Hard");

        String test = "catsanddog";
        List<String> dict = buildDictionary();
        this.setInput(test + "\n\tDictionary: " + Arrays.toString(dict.toArray()));
        List<String> answer = this.solution(test, dict);
        this.setOutput(Arrays.toString(answer.toArray()));
        this.printResult();
    }

    public List<String> buildDictionary(){
        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");
        return dict;
    }

    // https://leetcode.com/problems/word-break-ii/description/
    public List<String> solution(String s, List<String> dictionary) {
        return traverse(s, dictionary);
    }

    public List<String> traverse(String s, List<String> dict) {
        String sub;

        if (s.equals("")) {
            List<String> p = new ArrayList<>();
            p.add("");
            return p;
        }

        List<String> localAnswer = new ArrayList<>();
        for (int x = 0; x < s.length(); x++) {
            sub = s.substring(0, x + 1);

            if (dict.contains(sub)) {
                List<String> partial =
                    traverse(s.substring(x + 1), dict);

                for (String elm : partial) {
                    localAnswer.add(sub + " " + elm);
                }
            }
        }

        return localAnswer;
    }
}

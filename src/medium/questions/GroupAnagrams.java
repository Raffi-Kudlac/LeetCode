package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams extends SolutionOutline {

    public void runTest() {
        this.setClassName("GroupAnagrams");
        this.setDifficulty("Medium");

        String[] test = {"eat", "tea", "tan", "ate", "nat", "bat"};
        this.setInput(Arrays.toString(test));
        List<List<String>> answer = this.solution(test);
        this.setOutput(TwoDStringListToString(answer));
        this.printResult();
    }

    // https://leetcode.com/problems/group-anagrams/description/
    public List<List<String>> solution(String[] strs) {

        HashMap<String, List<String>> map = new HashMap<>();
        List<List<String>> answer = new ArrayList<>();

        String key;
        ArrayList<String> entry;
        char[] c;
        for (String s : strs ) {
            c = s.toCharArray();
            Arrays.sort(c);
            key = new String(c);

            if (map.containsKey(key)) {
                map.get(key).add(s);
            } else {
                entry = new ArrayList<>();
                entry.add(s);
                map.put(key, entry);
                answer.add(entry);
            }
        }
        return answer;
    }
}

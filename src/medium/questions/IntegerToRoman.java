package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.List;

public class IntegerToRoman extends SolutionOutline {

    public void runTest() {
        this.setClassName("IntegerToRoman");
        this.setDifficulty("Medium");

        int test = 101;
        this.setInput(test);
        String answer = this.solution(test, buildBinding());
        this.setOutput(answer);
        this.printResult();
        //test();
    }

    public void test(){
        List<Binding> b = buildBinding();
        List<Integer> testNumbers = new ArrayList<>();
        testNumbers.add(9);
        testNumbers.add(99);
        testNumbers.add(999);
        testNumbers.add(8999);

        for (Integer i : testNumbers) {
            System.out.println(i + " -> " + solution(i, b));
        }

    }

    public List<Binding> buildBinding(){
        List<Binding> bind = new ArrayList<>();
        bind.add(new Binding("I", 1));
        bind.add(new Binding("IV", 4));
        bind.add(new Binding("V", 5));
        bind.add(new Binding("IX", 9));

        bind.add(new Binding("X", 10));
        bind.add(new Binding("XL", 40));
        bind.add(new Binding("L", 50));
        bind.add(new Binding("XC", 90));

        bind.add(new Binding("C", 100));
        bind.add(new Binding("CD", 400));
        bind.add(new Binding("D", 500));
        bind.add(new Binding("CM", 900));

        bind.add(new Binding("M", 1000));
        bind.add(new Binding("MK", 4000));
        bind.add(new Binding("K", 5000));
        return bind;
    }

    // https://leetcode.com/problems/integer-to-roman/description/
    public String solution(int n, List<Binding> binding) {
        return intToRoman(n, binding);
    }

    public String intToRoman(int n, List<Binding> binding) {
        int dec;
        String roman;
        for (int i = binding.size() - 1; i >= 0; i--) {
            dec = binding.get(i).dec;
            roman = binding.get(i).roman;
            if (dec <= n) {
                return roman + intToRoman(n - dec, binding);
            }
        }

        return "";
    }

    public class Binding {
        String roman;
        int dec;

        Binding(String r, int i) {
            roman = r;
            dec = i;
        }
    }
}

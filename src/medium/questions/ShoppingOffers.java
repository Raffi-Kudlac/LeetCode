package medium.questions;

import helper.SolutionOutline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ShoppingOffers extends SolutionOutline {


    public void runTest() {
        this.setClassName("ShoppingOffers");
        this.setDifficulty("Medium");

        List<Integer> price = buildPrice();
        List<List<Integer>> offer = buildSpecialOffers();
        List<Integer> needs = buildNeeds();

        this.setInput(buildString(price, offer, needs));
        int answer = this.solution(price, offer, needs);
        this.setOutput(answer);
        this.printResult();
    }

    public String buildString(List<Integer> price,
          List<List<Integer>> special, List<Integer> needs) {

        String sPrice = "Prices: " + Arrays.toString(price.toArray());
        String offers = "Special Offers: " + TwoDListToString(special);
        String sNeeds = "Needs: " + Arrays.toString(needs.toArray());

        return sPrice + "\n" + offers + "\n" + sNeeds + "\n";

    }

    public List<Integer> buildPrice(){
        List<Integer> price = new ArrayList<>();
        price.add(2);
        price.add(5);
        return price;
    }

    public List<List<Integer>> buildSpecialOffers() {
        List<List<Integer>> offers = new ArrayList<List<Integer>>();

        List<Integer> offer1 = new ArrayList<>();
        offer1.add(3); offer1.add(0); offer1.add(5);

        List<Integer> offer2 = new ArrayList<>();
        offer2.add(1); offer2.add(2); offer2.add(10);

        offers.add(offer1);
        offers.add(offer2);
        return offers;
    }

    public List<Integer> buildNeeds(){
        List<Integer> needs = new ArrayList<>();
        needs.add(3);
        needs.add(2);
        return needs;
    }

    // https://leetcode.com/problems/shopping-offers/description/
    public int solution(List<Integer> price,
           List<List<Integer>> special, List<Integer> needs) {

        HashMap<List<Integer>, Integer> map = new HashMap<>();
        return getLowestPrice(price, special, needs, map);
    }

    public int getLowestPrice(List<Integer> price,
        List<List<Integer>> special, List<Integer> needs,
        HashMap<List<Integer>, Integer> map) {

        if (map.containsKey(needs)) {
            return map.get(needs);
        }

        List<Integer> newNeeds;
        int min = Integer.MAX_VALUE, val;

        for (List<Integer> sp : special) {
            newNeeds = canUseSpecial(sp, needs);
            if (newNeeds != null) {
                val = getLowestPrice(price, special, newNeeds, map) +
                        sp.get(sp.size()-1);
                min = Math.min(min, val);
            }
        }

        int byFromPrice = 0;
        if (min == Integer.MAX_VALUE) {
            for (int index = 0; index < needs.size(); index++) {
                byFromPrice += needs.get(index) * price.get(index);
            }
            return byFromPrice;
        }

        map.put(needs, min);
        return min;
    }

    public List<Integer> canUseSpecial(List<Integer> special,
        List<Integer> needs) {
        List<Integer> newNeeds = new ArrayList<>();
        int diff;
        for (int index = 0; index < special.size() - 1; index++) {

            diff = needs.get(index) - special.get(index);
            if (diff < 0) {
                return null;
            } else {
                newNeeds.add(diff);
            }
        }
        return newNeeds;
    }
}

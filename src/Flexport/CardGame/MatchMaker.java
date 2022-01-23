package Flexport.CardGame;

import java.util.*;
import java.util.stream.Collectors;

public class MatchMaker {
    DiscountEngine discountEngine;

    public MatchMaker(DiscountEngine discountEngine) {
        this.discountEngine = discountEngine;
    }

    public boolean canPurchase(List<Token> requiredTokens, Person person) {
        Map<String, List<Token>> personTokenMap = getPersonTokenMap(person);
        Map<String, List<Token>> requiredTokenMap = groupTokens(requiredTokens);
        for (String color : personTokenMap.keySet()) {
            if (personTokenMap.get(color).size() >
                    requiredTokenMap.getOrDefault(color, new ArrayList<>()).size()) {
                return false;
            }
        }
        return true;
    }

    public boolean makePurchase(List<Token> requiredTokens, Person person) throws Exception {
        try {
            if (canPurchase(requiredTokens, person)) {
                Map<String, List<Token>> requiredTokenMap = groupTokens(requiredTokens);
                for (String color : requiredTokenMap.keySet()) {
                    person.deleteTokens(requiredTokenMap.get(color));
                }
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("failed: " + e);
            return false;
        }
    }

    private Map<String, List<Token>> getPersonTokenMap(Person person) {
        return groupTokens(person.getTokens());
    }

    private Map<String, List<Token>> groupTokens(List<Token> cards) {
        return cards.stream().collect(Collectors.groupingBy(card -> card.getColor()));
    }
}

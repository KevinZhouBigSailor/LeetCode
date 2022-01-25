package Flexport.CardGame;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class MatchMaker {
    DiscountEngine discountEngine;

    public MatchMaker(DiscountEngine discountEngine) {
        this.discountEngine = discountEngine;
    }

    public boolean canPurchase(Card card, Person person) {
        return canPurchase(card.getRequiredTokens(), person);
    }

    private boolean canPurchase(List<Token> requiredTokens, Person person) {
        Map<String, List<Token>> personTokenMap = getPersonTokenMap(person);
        Map<String, List<Token>> requiredTokenMap = groupTokens(requiredTokens);
        for (String color : requiredTokenMap.keySet()) {
            if (personTokenMap.getOrDefault(color, new ArrayList<>()).size() <
                    requiredTokenMap.get(color).size()) {
                return false;
            }
        }
        return true;
    }

    public boolean makePurchase(@NotNull Card card, Person person){
        return makePurchase(card.getRequiredTokens(), person);
    }

    private boolean makePurchase(List<Token> requiredTokens, Person person) {
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

package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public class BetGame {
    public static void main(String[] args) {
        DiscountManager discountManager = new DiscountManager();
        MatchMaker matchMaker = new MatchMaker(discountManager);
        Person person = new AbstractPerson();
        person.addTokens(new ArrayList<>(List.of(new RedToken())));
        System.out.println(matchMaker.canPurchase(new TrueCard(), person));
    }
}

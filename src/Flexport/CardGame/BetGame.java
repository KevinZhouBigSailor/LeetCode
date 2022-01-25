package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public class BetGame {
    public static void main(String[] args) {
        DiscountManager discountManager = new DiscountManager();
        MatchMaker matchMaker = new MatchMaker(discountManager);
        AbstractPerson person = new AbstractPerson();
        person.addTokens(new ArrayList<>(List.of(new RedToken(), new BlueToken())));
        Card card = new TrueCard(new ArrayList<>(List.of(new RedToken(), new BlueToken())));
        System.out.println(person.toString());
        System.out.println(matchMaker.canPurchase(card, person));
        System.out.println(matchMaker.makePurchase(card, person));
        System.out.println(person.toString());
    }
}

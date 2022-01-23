package Flexport.CardGame;

import java.util.List;

public class TrueCard implements Card {
    public TrueCard(List<Token> requiredTokens) {
        this.requiredTokens.addAll(requiredTokens);
    }

    public List<Token> getRequiredTokens() {
        return this.requiredTokens;
    }
}

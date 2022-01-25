package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public class TrueCard implements Card {
    public TrueCard(final List<Token> requiredTokens) {
        this.requiredTokens.addAll(requiredTokens);
    }

    public List<Token> getRequiredTokens() {
        return this.requiredTokens;
    }
}

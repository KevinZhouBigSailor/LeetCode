package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public final class TrueCard implements Card {
    public TrueCard() {
        List<Token> requiredTokens = new ArrayList<>();
        requiredTokens.add(new RedToken());
        requiredTokens.add(new BlueToken());
        this.requiredTokens.addAll(requiredTokens);
    }

    public List<Token> getRequiredTokens() {
        return this.requiredTokens;
    }
}

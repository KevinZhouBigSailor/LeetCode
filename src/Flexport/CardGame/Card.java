package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public interface Card {
    List<Token> requiredTokens = new ArrayList<>();

    public List<Token> getRequiredTokens();
}

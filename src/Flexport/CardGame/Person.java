package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;

public interface Person {
    List<Token> tokens = new ArrayList<Token>();
    // synchronized
    void addTokens(List<Token> tokens);

    List<Token> getTokens();

    void deleteTokens(List<Token> tokens);
}

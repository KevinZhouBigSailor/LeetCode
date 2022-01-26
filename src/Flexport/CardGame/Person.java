package Flexport.CardGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface Person {
    List<Token> tokens = new ArrayList<Token>();
    Map<Token, Integer> tokenMap = new ConcurrentHashMap<>();
    // synchronized
    void addTokens(List<Token> tokens);

    List<Token> getTokens();

    void deleteTokens(List<Token> tokens);
}

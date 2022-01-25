package Flexport.CardGame;

import java.util.*;

public class AbstractPerson implements Person {

    public AbstractPerson() {
    }

    @Override
    public void addTokens(List<Token> tokens) {
        synchronized (this) {
            this.tokens.addAll(tokens);
        }
    }

    @Override
    public List<Token> getTokens() {
        return this.tokens;
    }

    @Override
    public void deleteTokens(List<Token> tokens) {
        synchronized (this) {
            this.tokens.removeAll(tokens);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(getTokens().toArray());
    }
}

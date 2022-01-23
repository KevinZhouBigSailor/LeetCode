package Flexport.CardGame;

import java.util.Objects;

public abstract class AbstractToken implements Token {

    private String color;

    private int value;

    public AbstractToken(String color, int value) {
        this.color = color;
        this.value = value;
    }

    public String toString() {
        return getClass().getSimpleName() + " color:" + color + " value:" + value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractToken that = (AbstractToken) o;
        return value == that.value && color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, value);
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

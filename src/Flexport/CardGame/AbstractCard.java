package Flexport.CardGame;

public abstract class AbstractCard implements Card {

    private String color;

    private int value;

    public AbstractCard(String color, int value) {
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

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

package com.example.casinoroyaleblackjackgui;

public class Card {

    private Rank rank;
    private Suit suit;
    private int value;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }
    public Suit getSuit() {
        return suit;
    }
    public int getValue() {
        return rank.rankValue;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");
    }
}

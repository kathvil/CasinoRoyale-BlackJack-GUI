package com.example.casinoroyaleblackjackgui;

public class Card {

    private Rank rank;
    final private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return rank.rankValue;
    }

    @Override
    public String toString() {
        return ("["+rank+" of "+ suit + "] ("+this.getValue()+")");
    }
}

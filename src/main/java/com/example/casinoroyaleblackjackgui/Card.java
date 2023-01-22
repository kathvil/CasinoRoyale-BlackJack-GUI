package com.example.casinoroyaleblackjackgui;

public class Card {

    private Rank rank;
    final private Suit suit;
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

    public String imageFilePath() {
        return this.suit.suitName + "-" + this.rank.rankName + ".svg";
    }

    public String formatSuit(Suit suit) {
        switch(suit) {
            case CLUB: return "Clubs♣";
            case DIAMOND: return "Diamonds♦";
            case HEART: return "Hearts♥";
            default:
                return "Spades♠";
        }
    }

    @Override
    public String toString() {
        return ("["+rank+" of "+ formatSuit(suit) + "] ("+this.getValue()+")");
    }
}

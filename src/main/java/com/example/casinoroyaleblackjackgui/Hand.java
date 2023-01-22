package com.example.casinoroyaleblackjackgui;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;
    private int sum;

    public Hand(){
        hand = new ArrayList<>();
        sum = 0;
    }

    public int getSum() {
        return sum;
    }

    // returns True if Player achieves BlackJack with initial hand of 2 cards
    public boolean getBlackjack() {return getSum() == 21 && hand.size() == 2; }

    public boolean checkIfBusted() {return this.getSum() > 21; }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public void clearHand() {
        this.hand = new ArrayList<>();
        this.setSum(0);
    }

    public void changeAce() {
        for (Card c : hand) {
            if (c.getValue() == 11) {
                Card lowAce = new Card(Rank.LowACE, c.getSuit());
                hand.set(hand.indexOf(c), lowAce);
                break;
            }
        }
    }

    public boolean checkAce() {
        boolean checkForAce = false;
        for (Card c : hand) {
            if (c.getValue() == 11) {
                checkForAce = true;
                break;
            }
        }
        return checkForAce;
    }

    @Override
    public String toString() {
        String cardsInHand = "";
        int counter = hand.size();
        for (Card card : hand) {
            if (counter <= 1) {
                cardsInHand += card;
            } else {
                cardsInHand += card + " + ";
            }
            counter--;
        }
        return cardsInHand + " => ( " + getSum() + " )";
    }

    public void addCardToHand (Card newCard){
        this.hand.add(newCard);
    }
}


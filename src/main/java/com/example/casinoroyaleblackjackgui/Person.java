package com.example.casinoroyaleblackjackgui;

public abstract class Person {

    private Hand hand;
    private String name;

    public Person(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    // setter & getter for name
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    // setter & getter for hand
    public void setHand(Hand hand) {
        this.hand = hand;
    }
    public Hand getHand() {
        return hand;
    }


    // TODO
    public void hit(Card card) {



    }
}

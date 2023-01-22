package com.example.casinoroyaleblackjackgui;

public abstract class Person {

    private Hand hand;
    private String name;

    public Person(String name) {
        this.name = name;
        this.hand = new Hand();
    }

    // getter for name
    public String getName() {
        return name;
    }

    // getter for hand
    public Hand getHand() {
        return hand;
    }


}

package com.example.casinoroyaleblackjackgui;

public class Player extends Person {

    // variables for Player
    private double balance;
    private double bet;
    private boolean isStanding = false;
    private boolean isPlaying = true;


    // constructors
    public Player (String name, double balance) {
        super(name);
        this.balance = balance;
    }

    // setter & getter for balance
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    // setter & getter for bet
    public void setBet(double bet) {
        this.bet = bet;
    }
    public double getBet() {
        return bet;
    }


    // set & check for isStanding
    public void setStanding(boolean standing) {
        isStanding = standing;
    }
    public boolean getStanding() {
        return isStanding;
    }


    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }
    public boolean getPlaying() {
        return isPlaying;
    }

    @Override
    public String toString() {
       return (this.getName()+" "+this.getBalance()+"€");
    }

}
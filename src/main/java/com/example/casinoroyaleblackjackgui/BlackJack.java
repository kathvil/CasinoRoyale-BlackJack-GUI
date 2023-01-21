package com.example.casinoroyaleblackjackgui;

import java.text.DecimalFormat;
import java.util.*;

public class BlackJack {
    static int defaultSleep = 1500;

    private static ArrayList<Player> players = new ArrayList<>();

    public static void sleep(int time){
        try {
            //time in milliseconds
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void loading(){
        int defaultAnimationSpeed = 400;
        //set to false to disable animations
        if (true) {
            sleep(defaultAnimationSpeed);
            System.out.print(". ");
            sleep(defaultAnimationSpeed);
            System.out.print("\r. . ");
            sleep(defaultAnimationSpeed);
            System.out.print("\r. . .");
            sleep(defaultAnimationSpeed);
        } else {
            System.out.print(" ");
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);


        /*
        Initialization of Players from 13-60
         */
        System.out.print("Welcome to Black Jack!\n" +
                "How many players will join this game? Please type in a number from 1-10: ");
        boolean isValid = false;
        int playerCount = 0;
        while (!isValid) {
            if (input.hasNextInt()) {
                isValid = true;
                playerCount = input.nextInt();
                if (!(playerCount < 11 && playerCount > 0)) {
                    System.out.print("Please enter a valid number from 1-10: ");
                    isValid = false;
                }
            } else {
                input.next();
                System.out.print("Please enter a valid number from 1-10: ");
            }
        }

        Player[] player = new Player[playerCount];

        //Idee: Start balance für alle Spieler gleich?

        for (int i = 0; i < player.length; i++) {
            System.out.print("Please enter your name Player" + (i + 1) + ": ");
            String name = "";
            isValid = false;
            while (!isValid) {
                if (input.hasNextInt()) {
                    System.out.print("Please enter a name for Player " + (i + 1) + ": ");
                    input.next();
                } else if (input.hasNextDouble()) {
                    System.out.print("Please enter a name for Player " + (i + 1) + ": ");
                    input.next();
                } else if (input.hasNext()) {
                    isValid = true;
                    name = input.next();
                } else {
                    System.out.print("Please enter a name for Player " + (i + 1) + ": ");
                    input.next();
                }
            }
            System.out.print("Please type in your starting balance: ");

            isValid = false;
            double balance = 0;
            while (!isValid) {
                if (input.hasNextDouble()) {
                    isValid = true;
                    balance = Math.round(input.nextDouble() * 100.0) / 100.0;
                    if (balance <= 0) {
                        System.out.print("Please enter a valid number for your starting balance (10$ minimum): ");
                        isValid = false;
                    }
                } else {
                    System.out.print("Please enter a valid number for your starting balance (10$ minimum): ");
                    input.next();
                }
            }
            player[i] = new Player(name, balance);
        }

        Dealer dealer = new Dealer("Dealer");
        players.addAll(List.of(player));
        System.out.println("The game starts with following players: ");
        System.out.println();
        System.out.println(players);
        System.out.println();

        /*
        Creating the Deck
         */
        CardDeck deck = new CardDeck();
        deck.shuffle();

        /*
        Place bets
         */
        while (playerCount != 0) {
            for (Player p : players) {
                if (p.getPlaying()){
                    System.out.print(p.getName() + ", place your bet or type 0 to skip this round: ");
                    isValid = false;
                    double currentBet = 0;
                    while (!isValid) {
                        if (input.hasNextDouble()) {
                            currentBet = input.nextDouble();
                            if (currentBet < 0) {
                                System.out.print("Invalid amount, please try again: ");
                            } else if (currentBet == 0) {
                                p.setPlaying(false);
                                System.out.println(p.getName() + " skips this round");
                                isValid = true;
                            }
                            else {
                                // Sufficient Balance?
                                if (currentBet < p.getBalance() + 1) {
                                    p.setBet(currentBet);
                                    isValid = true;
                                } else {
                                    System.out.print("You are broke :) , please try again: ");
                                }
                            }
                        } else {
                            System.out.print("Please enter a valid number for your bet: ");
                            input.next();

                        }
                    }
                }
            }
            System.out.println();


        /*
        Erstes Kartenpaar wird ausgeteilt
         */
            for (int i = 0; i < 2; i++) {
                for (Player p : players) {
                    if (p.getPlaying()) {
                        deck.addCardFromDeck(p.getHand());
                    }
                }
                deck.addCardFromDeck(dealer.getHand());
            }
            String dealersFirst = dealer.getName() + " " + dealer.getHand();
            int indexOfMinus = dealersFirst.indexOf("-");
            System.out.println(dealersFirst.substring(0, indexOfMinus) + " - [Hidden] (?)"); // Zweite Karte verdeckt

            for (Player p : players) {
                if (p.getPlaying()) {
                    System.out.println(p.getName() + " " + p.getHand());
                }
            }
            System.out.println();

            // Spielrunden
            int counter = 0;
            for (Player p : players) {
                if (p.getPlaying()) {
                    boolean busted = false;
                    if (counter > 0) {
                        System.out.println(p.getName() + " " + p.getHand());
                    }
                    if (p.getHand().getBlackjack()) {
                        p.setStanding(true);
                        System.out.println(p.getName() + " scored a Blackjack");
                    } else {
                        System.out.print(p.getName() + ", type 'h' to hit or 's' to stand: ");
                    }
                    while (!p.getStanding()) {
                        if (input.hasNext()) {
                            String standOrHit = input.next();
                            switch (standOrHit) {
                                case "h":
                                    //fetch a new card
                                    if (deck.checkDeck()) {
                                        deck = new CardDeck();
                                        deck.shuffle();
                                    }
                                    deck.addCardFromDeck(p.getHand());
                                    loading();
                                    //print hand
                                    System.out.println("\r" + p.getName() + " " + p.getHand());
                                    if (p.getHand().getSum() == 21) {
                                        p.setStanding(true);
                                        break;
                                    }
                                    //change ace method here
                                    if (p.getHand().getSum() > 21) {
                                        System.out.println("Bust!");
                                        busted = true;
                                        p.setStanding(true);
                                        break;
                                    }
                                    //new choice
                                    System.out.print(p.getName() + ", type 'h' to hit or 's' to stand: ");
                                    break;
                                case "s":
                                    p.setStanding(true);
                                    break;
                                default:
                                    System.out.print("Only valid inputs are h / s : ");
                            }
                        } else {
                            System.out.print("Please enter a valid input: ");
                        }
                    }

                    if (!busted && !p.getHand().getBlackjack()) {
                        System.out.println(p.getName() + " stands with " + p.getHand().getSum());
                    }
                    counter++;
                    p.setStanding(false);
                    sleep(defaultSleep);
                    System.out.println();
                }/*else here*/}
                if (dealer.getHand().getBlackjack()) {
                    System.out.println("Dealer scored Blackjack!");
                }
                while (dealer.getHand().getSum() <= 17) {
                    if (deck.checkDeck()) {
                        deck = new CardDeck();
                        deck.shuffle();
                    }
                    System.out.println("Dealer hits...");
                    loading();
                    deck.addCardFromDeck(dealer.getHand());
                    System.out.println("\r" + dealer.getHand());
                }
                /* change ace for dealer here */
                if (dealer.getHand().getSum() > 21) {
                    System.out.println("Dealer busts!");
                }
                sleep(defaultSleep);
                // End of Round
                //System.out.println();
                System.out.println("Dealer = " + dealer.getHand() + " ( = " + dealer.getHand().getSum() + " )");
                System.out.println();

                for (Player p : players) {
                    if (p.getPlaying()){
                    System.out.println(p.getHand() + " ( = " + p.getHand().getSum() + " )");
                    if (p.getHand().checkIfBusted() || p.getHand().getSum() < dealer.getHand().getSum() && !p.getHand().getBlackjack() && !dealer.getHand().checkIfBusted()) {
                        System.out.println(p.getName() + " looses!");
                        p.setBalance(p.getBalance() - p.getBet());
                    } else if (!p.getHand().checkIfBusted() && p.getHand().getSum() > dealer.getHand().getSum() && !p.getHand().getBlackjack()) {
                        System.out.println(p.getName() + " wins!");
                        p.setBalance(p.getBalance() + p.getBet());
                    } else if (!p.getHand().checkIfBusted() && dealer.getHand().checkIfBusted()) {
                        System.out.println(p.getName() + " wins!");
                        p.setBalance(p.getBalance() + p.getBet());
                    } else if (p.getHand().getBlackjack()) {
                        System.out.println(p.getName() + " scored a Blackjack!");
                        p.setBalance(p.getBalance() + (p.getBet() * 1.5));
                    } else if (p.getHand().getSum() == dealer.getHand().getSum()) {
                        System.out.println(p.getName() + ": Push! Bet returned.");
                        // p.setBalance(p.getBalance() + p.getBet()); nicht nötig
                    }
                    sleep(defaultSleep);
                    System.out.println();
                }}
                System.out.println(players);
                for (Player p : players) {
                    p.getHand().clearHand();
                    if (p.getBalance() > 0){ p.setPlaying(true);
                }else{
                        playerCount --;
                        p.setPlaying(false);
                    }}
                dealer.getHand().clearHand();


                // TODO
        /*
        "Change Ace" method
        (function to Double bet if first two cards match rank, player then has two hands to play with)
        */
            }
        System.out.println();
        loading();
        System.out.println("\rThe house has won (obviously) because no players are left.");
        sleep(5000);
        }


}

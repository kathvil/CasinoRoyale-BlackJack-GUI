package com.example.casinoroyaleblackjackgui;

import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {
    static final int defaultSleep = 1500;

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
        Scanner inputBalance = new Scanner(System.in);

        /*
        Initialization of Players
         */

        // Set Number of players
        System.out.print("Welcome to Black Jack!\n" +
                "How many players will join this game? Please enter a number from 1-10: ");
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


        // Set Player name
        for (int i = 0; i < player.length; i++) {
            System.out.print("Please enter a name for Player " + (i + 1) + ": ");
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

                    // prevent input of identical names
                    for (Player p1 : players) {
                        if (p1.getName().equals(name)) {
                            System.out.println("This name is already taken!");
                            isValid = false;
                            System.out.print("Please enter a name for Player " + (i + 1) + ": ");
                            break;
                        }
                    }
                }
            }

            // Set Player balance
            System.out.print("Please type in your starting balance (10€ minimum): ");
            isValid = false;
            double balance = 0;
            while (!isValid) {
                if (inputBalance.hasNextDouble()) {
                    isValid = true;
                    balance = Math.round(inputBalance.nextDouble() * 100.0) / 100.0;
                    if (balance < 10) {
                        System.out.print("Please enter a valid number for your starting balance (10€ minimum): ");
                        isValid = false;
                    }
                } else {
                    System.out.print("Please enter a valid number for your starting balance (10€ minimum): ");
                    inputBalance.next();
                }
            }
            player[i] = new Player(name, balance);
            players.add(player[i]);
        }

        /*
        Display all players and their balances
         */
        Dealer dealer = new Dealer("Dealer");
        System.out.println("The game starts with following players: ");
        System.out.println();
        System.out.println(players);
        System.out.println();

        /*
        Generating the Card Deck
         */
        CardDeck deck = new CardDeck();
        deck.shuffle();


        while (playerCount != 0) {

            /*
            Place bets or skip round
            */
            for (Player p : players) {
                if (p.getPlaying()){
                    System.out.print(p.getName() + ", place your bet or type \"0\" to skip this round: ");
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
            Deal first pair of cards
            */
            for (int i = 0; i < 2; i++) {
                for (Player p : players) {
                    if (p.getPlaying()) {
                        if (deck.checkDeck()) {
                            deck = new CardDeck();
                            deck.shuffle();
                        }
                        deck.addCardFromDeck(p.getHand());
                    }
                }
                if (deck.checkDeck()) {
                    deck = new CardDeck();
                    deck.shuffle();
                }
                deck.addCardFromDeck(dealer.getHand());
            }

            // Hide dealer's second card
            String dealersFirst = dealer.getName() + " " + dealer.getHand();
            int indexOfMinus = dealersFirst.indexOf("+");
            System.out.println(dealersFirst.substring(0, indexOfMinus) + "+ [Hidden Card] (?)");

            // Display all hands
            for (Player p : players) {
                if (p.getPlaying()) {
                    System.out.println(p.getName() + " " + p.getHand());
                }
            }
            System.out.println();

            /*
            Hit or Stand
             */
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
                                    // Hit: player receives additional card
                                    if (deck.checkDeck()) {
                                        deck = new CardDeck();
                                        deck.shuffle();
                                    }
                                    deck.addCardFromDeck(p.getHand());
                                    loading();

                                    // Print player hand
                                    System.out.println("\r" + p.getName() + " " + p.getHand());
                                    if (p.getHand().getSum() == 21) {
                                        p.setStanding(true);
                                        break;
                                    }

                                    // Change Ace from 11 to 1
                                    if (p.getHand().getSum() > 21) {
                                        if (p.getHand().checkAce()) {
                                            while (p.getHand().getSum() > 21) {
                                                p.getHand().changeAce();
                                                p.getHand().setSum(p.getHand().getSum() - 10);
                                                System.out.println("Your Ace has been converted to LowAce =>  your new hand-value is "+ (p.getHand().getSum()));
                                                if (p.getHand().getSum() == 21) {
                                                    p.setStanding(true);
                                                }
                                            }
                                        } else {
                                            System.out.println("Bust!");
                                            busted = true;
                                            p.setStanding(true);
                                            break;
                                        }
                                    }
                                    // New choice
                                    if (p.getHand().getSum() < 21) {
                                        System.out.print(p.getName() + ", type 'h' to hit or 's' to stand: ");
                                        break;
                                    }

                                // Stand: player locks hand
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
                }
            }
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
            // Change Dealer's Ace from 11 to 1
            if (dealer.getHand().getSum() > 21) {
                if (dealer.getHand().checkAce()) {
                    while (dealer.getHand().getSum() > 21) {
                        dealer.getHand().changeAce();
                        dealer.getHand().setSum(dealer.getHand().getSum() - 10);
                        System.out.println("The Dealer's Ace has been converted to LowAce =>  new hand-value is "+ (dealer.getHand().getSum()));
                    }
                } else {
                    System.out.println("Dealer busts!");
                }
            }
            sleep(defaultSleep);

            // End of Round
            System.out.println();
            System.out.println("--- END OF ROUND ---");
            System.out.println();
            System.out.println("Dealer = " + dealer.getHand());
            System.out.println();

            /*
            Compare hands and declare winners & losers
            */
            for (Player p : players) {
                if (p.getPlaying()){
                    System.out.println(p.getHand());
                    if (p.getHand().checkIfBusted() || p.getHand().getSum() < dealer.getHand().getSum() && !p.getHand().getBlackjack() && !dealer.getHand().checkIfBusted()) {
                        System.out.println(p.getName() + " loses " + p.getBet() + "€");
                        p.setBalance(p.getBalance() - p.getBet());
                    } else if (!p.getHand().checkIfBusted() && p.getHand().getSum() > dealer.getHand().getSum() && !p.getHand().getBlackjack()) {
                        System.out.println(p.getName() + " wins " + p.getBet() + "€");
                        p.setBalance(p.getBalance() + p.getBet());
                    } else if (!p.getHand().checkIfBusted() && dealer.getHand().checkIfBusted()) {
                        System.out.println(p.getName() + " wins " + p.getBet() + "€");
                        p.setBalance(p.getBalance() + p.getBet());
                    } else if (p.getHand().getBlackjack()) {
                        System.out.println(p.getName() + " scored a Blackjack and wins " + (p.getBet()*1.5) + "€");
                        p.setBalance(p.getBalance() + (p.getBet() * 1.5));
                    } else if (p.getHand().getSum() == dealer.getHand().getSum()) {
                        System.out.println(p.getName() + ": Push! Bet returned.");
                    }
                    sleep(defaultSleep);
                    System.out.println();
                }
            }
            System.out.println(players);
            System.out.println();
            for (Player p : players) {
                p.getHand().clearHand();
                if (p.getBalance() > 0){
                    p.setPlaying(true);
                } else {
                    if (p.getPlaying()) {
                        playerCount--;
                        p.setPlaying(false);
                    }
                }
            }
            dealer.getHand().clearHand();
        }

        // All players are out of money
        System.out.println();
        loading();
        System.out.println("\rThe house has won (obviously) because no players are left.");
        sleep(5000);
    }
}

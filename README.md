# ♥ ♣️ CasinoRoyale - BlackJack ♦ ️♠️

Version of BackJack game over the console.

Project by Alexander Mosor, Christian Ninaus, Karl Szepannek, Emir Taric & Kathryn Villaruel.

### 💯 The objective of the game is to beat the dealer, which can be done in the following ways:

    • Get 21 points on the player's first two cards (called a blackjack), without a dealer blackjack; 🤑
    • Reach a final score higher than the dealer without exceeding 21; or 
    • Let the dealer draw additional cards until his or her hand exceeds 21.

## 🃏 GAME START

    • Set the number of players
    • Assign each player a name and a total balance

    • Each player & the dealer get 2 initial cards
    • At the beginning of each gameround each player has to place his desired bet
    • Placing an invalid bet (0 or <0) results in a stand for this gameround


## 👀 RULES OF THE GAME

The game is implemented with standard 1 deck of cards. 
It has implementation of 2 functions for player after receiving two initial cards: Hit or Stand.
The rules for each implementation is described below:

    • Hit: Take another card from deck.
    • Stand: Player takes no more cards and next Player in round gets his turn.

Following rules are implemented for the dealer in the game.

    • Dealer hits until his cards total 17 or more points.
    • Dealer never stands

Following rules are implemented for different scenarios during the game:

    • A blackjack beats any hand that is not a blackjack, even one with a value of 21.
    • In the case of a tied score, known as "push" or "tie", bets are normally returned without adjustment.
    • An outcome of blackjack vs. blackjack results in a push.


## 🏃 TO RUN THE GAME IN THE CONSOLE:

    • Run 'BlackJack.main()'

## 🏃 TO RUN THE GAME VIA JAVAFX:
    • Run BlackJackApplication
    • needs JDK 17

### OPEN TODOS / IMPLEMENTATIONS

    • "Change Ace" method // choose ACE value (1 or 11)
    • function to Double bet if first two cards match rank, player then has two hands to play with


### OPEN IDEAS ABOUT GAME LOGIC 🤔

    • Same Starting Balance for all Players
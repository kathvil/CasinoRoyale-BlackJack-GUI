package com.example.casinoroyaleblackjackgui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BlackJackController
{

    @FXML
    private Button startRoundBtn;

    @FXML
    private VBox verticalBox;

    @FXML
    private TextField playerName1;
    @FXML
    private TextField playerName2;
    @FXML
    private TextField player1Balance;
    @FXML
    private TextField player2Balance;

//    region player bet btns
    @FXML
    private Button player1bet10;
    @FXML
    private Button player1bet20;
    @FXML
    private Button player1bet30;
    @FXML
    private Button player1Skip;
    @FXML
    private Button player1Stand;
    @FXML
    private Button player1Hit;
    @FXML
    private Button player2bet10;
    @FXML
    private Button player2bet20;
    @FXML
    private Button player2bet30;
    @FXML
    private Button player2Skip;
//    endregion

    @FXML
    private Button player2Stand;
    @FXML
    private Button player2Hit;
    @FXML
    private Text currentPlayer1;
    @FXML
    private Text currentPlayer2;
    @FXML
    private Text currentPlayer1Bet;
    @FXML
    private Text currentPlayer2Bet;

    @FXML
    private Text startPlayer1Balance;
    @FXML
    private Text startPlayer2Balance;
    @FXML
    private Text currentPlayer1Balance;
    @FXML
    private Text currentPlayer2Balance;

    // region Player Cards
    @FXML
    private Text player1CardSum;
    @FXML
    private ImageView player1Card1;
    @FXML
    private ImageView player1Card2;
    @FXML
    private ImageView player1Card3;
    @FXML
    private ImageView player1Card4;
    @FXML
    private ImageView player1Card5;
    @FXML
    private ImageView player1Card6;

    @FXML
    private Text player2CardSum;
    @FXML
    private ImageView player2Card1;
    @FXML
    private ImageView player2Card2;
    @FXML
    private ImageView player2Card3;
    @FXML
    private ImageView player2Card4;
    @FXML
    private ImageView player2Card5;
    @FXML
    private ImageView player2Card6;
    // endregion


    @FXML
    private Text dealerCardSum;
    @FXML
    private ImageView dealerCard1;
    @FXML
    private ImageView dealerCard2;
    @FXML
    private ImageView dealerCard3;
    @FXML
    private ImageView dealerCard4;
    @FXML
    private ImageView dealerCard5;
    @FXML
    private ImageView dealerCard6;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static List<Player> players = new ArrayList<>();

    private static Dealer dealer = new Dealer("Dealer") ;

    private static CardDeck deck = new CardDeck();

    public BlackJackController()
    {

    }

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("black-jack-start.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToScene2(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("black-jack-2.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene3(ActionEvent event) throws IOException {

        try {
            double balancePlayer1 = Double.parseDouble(player1Balance.getText());
            double balancePlayer2 = Double.parseDouble(player2Balance.getText());

            if (playerName1.getLength() < 3 || playerName2.getLength() < 3) {
                //            TODO: Pop Up mit Fehlermeldung (ex. Name zu kurz)
                return;
            }

            players.add(new Player(playerName1.getText(), balancePlayer1));
            players.add(new Player(playerName2.getText(), balancePlayer2));



        } catch (Exception ex) {
//            TODO: Pop Up mit Fehlermeldung (ex. Gebe einen gültigen Wert ein)
            return;
        }

        Parent root = FXMLLoader.load(getClass().getResource("black-jack-3.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    // region player 1 bet
    public void setBet10Player1() {
        startPlayer1Balance.setVisible(false);
        currentPlayer1Bet.setText("10");
        changePlayer1ButtonVisibility(false);
        double currentBalance = players.get(0).getBalance() - 10;
        players.get(0).setBalance(currentBalance);
        players.get(0).setBet(10);
        currentPlayer1Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer1Balance.setText(stringBalance);
        currentPlayer1Balance.setVisible(true);
    }
    public void setBet20Player1() {
        startPlayer1Balance.setVisible(false);
        currentPlayer1Bet.setText("20");
        changePlayer1ButtonVisibility(false);
        double currentBalance = players.get(0).getBalance() - 20;
        players.get(0).setBalance(currentBalance);
        players.get(0).setBet(20);
        currentPlayer1Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer1Balance.setText(stringBalance);
        currentPlayer1Balance.setVisible(true);
    }
    public void setBet30Player1() {
        startPlayer1Balance.setVisible(false);
        currentPlayer1Bet.setText("30");
        changePlayer1ButtonVisibility(false);
        double currentBalance = players.get(0).getBalance() - 30;
        players.get(0).setBalance(currentBalance);
        players.get(0).setBet(30);
        currentPlayer1Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer1Balance.setText(stringBalance);
        currentPlayer1Balance.setVisible(true);    }

    // endregion

    public void setBet10Player2() {
        startPlayer2Balance.setVisible(false);
        currentPlayer2Bet.setText("10");
        changePlayer2ButtonVisibility(false);
        double currentBalance = players.get(1).getBalance() - 10;
        players.get(1).setBalance(currentBalance);
        players.get(1).setBet(10);
        currentPlayer2Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer2Balance.setText(stringBalance);
        currentPlayer2Balance.setVisible(true);
    }
    public void setBet20Player2() {
        startPlayer2Balance.setVisible(false);
        currentPlayer2Bet.setText("20");
        changePlayer2ButtonVisibility(false);
        double currentBalance = players.get(1).getBalance() - 20;
        players.get(1).setBalance(currentBalance);
        players.get(1).setBet(20);
        currentPlayer2Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer2Balance.setText(stringBalance);
        currentPlayer2Balance.setVisible(true);
    }
    public void setBet30Player2() {
        startPlayer2Balance.setVisible(false);
        currentPlayer2Bet.setText("30");
        changePlayer2ButtonVisibility(false);
        double currentBalance = players.get(1).getBalance() - 30;
        players.get(1).setBalance(currentBalance);
        players.get(1).setBet(30);
        currentPlayer2Balance.textProperty().unbind();
        String stringBalance = Double.toString(currentBalance);
        currentPlayer2Balance.setText(stringBalance);
        currentPlayer2Balance.setVisible(true);
    }

    public void skipRoundPlayer1(){
        players.get(0).setPlaying(false);
        changePlayer1ButtonVisibility(false);
        currentPlayer1Bet.setVisible(false);
        player1Stand.setVisible(false);
        player1Hit.setVisible(false);
    }
    public void skipRoundPlayer2(){
        players.get(1).setPlaying(false);
        changePlayer2ButtonVisibility(false);
        currentPlayer2Bet.setVisible(false);
        player2Stand.setVisible(false);
        player2Hit.setVisible(false);
    }


    public void startRound(ActionEvent event){

        this.startRoundBtn.setVisible(false);
        // hit stand buttons visible
        Player p1 = players.get(0);
        Player p2 = players.get(1);

        if (p1.getPlaying()) {
            player1Stand.setVisible(true);
            player1Hit.setVisible(true);
        }
        if (p2.getPlaying()) {
            player2Stand.setVisible(true);
            player2Hit.setVisible(true);
        }
        // If both players skip
        if (!p1.getPlaying() && !p2.getPlaying()) {
            changePlayer1ButtonVisibility(true);
            changePlayer2ButtonVisibility(true);
        }

        //evt. deck neu auffüllen / initalisieren
        deck.shuffle();

        deck.addCardFromDeck(dealer.getHand());
        deck.addCardFromDeck(dealer.getHand());

        dealerCard1.setImage(new Image("img/cards/" + dealer.getHand().getCardAtIndex(0).imageFilePath()));
        dealerCard2.setImage(new Image("img/cards/" + dealer.getHand().getCardAtIndex(1).imageFilePath()));
        dealerCardSum.setText(dealer.getHand().getSum() + "");

        if (p1.getPlaying()) {
            deck.addCardFromDeck(p1.getHand());
            deck.addCardFromDeck(p1.getHand());
            player1Card1.setImage(new Image("img/cards/" + p1.getHand().getCardAtIndex(0).imageFilePath()));
            player1Card2.setImage(new Image("img/cards/" + p1.getHand().getCardAtIndex(1).imageFilePath()));
            player1CardSum.setText(p1.getHand().getSum() + "");
        }

        if (p2.getPlaying()) {
            deck.addCardFromDeck(p2.getHand());
            deck.addCardFromDeck(p2.getHand());
            player2Card1.setImage(new Image("img/cards/" + p2.getHand().getCardAtIndex(0).imageFilePath()));
            player2Card2.setImage(new Image("img/cards/" + p2.getHand().getCardAtIndex(1).imageFilePath()));
            player2CardSum.setText(p2.getHand().getSum() + "");
        }
    }


    // TODO: Hit or Stand Methods
    public void setStandPlayer1() {
        currentPlayer1Bet.setVisible(true);
    }
    public void setStandPlayer2() {
    }


    public void hitFirstPlayer() {
    }
    public void hitSecondPlayer() {
    }



    public void changePlayer1ButtonVisibility(boolean value) {
        player1bet10.setVisible(value);
        player1bet20.setVisible(value);
        player1bet30.setVisible(value);
        player1Skip.setVisible(value);
        currentPlayer1Bet.setVisible(!value);
        // hit stand buttons visible
        player1Stand.setVisible(!value);
        player1Hit.setVisible(!value);
    }
    public void changePlayer2ButtonVisibility(boolean value) {
        player2bet10.setVisible(value);
        player2bet20.setVisible(value);
        player2bet30.setVisible(value);
        player2Skip.setVisible(value);
        currentPlayer2Bet.setVisible(!value);
        // hit stand buttons visible
        player2Stand.setVisible(!value);
        player2Hit.setVisible(!value);

    }

   public String getFirstPlayerName() {
        return players.get(0).getName();
   }
   public String getFirstPlayerBalance() {
        return "" + players.get(0).getBalance();
   }

    public String getSecondPlayerName() {
        return players.get(1).getName();
    }
    public String getSecondPlayerBalance() {
        return "" + players.get(1).getBalance();
    }
}


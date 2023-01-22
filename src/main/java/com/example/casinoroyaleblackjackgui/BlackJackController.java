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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BlackJackController
{
    @FXML
    private Label welcomeText;

    @FXML
    private TextField playerInput;

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
    @FXML
    private Button player1bet10;
    @FXML
    private Button player1bet20;
    @FXML
    private Button player1bet30;
    @FXML
    private Button player1Stand;
    @FXML

    private Button player2bet10;
    @FXML
    private Button player2bet20;
    @FXML
    private Button player2bet30;
    @FXML
    private Button player2Stand;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private static List<Player> players = new ArrayList<>();

    private static Dealer dealer = new Dealer("Dealer") ;

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

    public void startRound(ActionEvent event){
//        this.displayRoundStartDialogBox(this.players.get(0));
//        this.displayRoundStartDialogBox(this.players.get(1));
    }
//
//    public void displayRoundStartDialogBox(Player player) {
//        String playerName = player.getName();
//        TextInputDialog dialog = new TextInputDialog("");
//        dialog.getDialogPane().getScene().getWindow().setOnCloseRequest(event -> {
//            event.consume();
//        });
//
//        dialog.getDialogPane().lookupButton(ButtonType.OK).setOnMouseClicked(event -> {
//            if()
//        });

//        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);
//        dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);;
//        dialog.setTitle("Wetteinsatz");
//        dialog.setHeaderText( playerName + " gib deinen Wetteinsatz an:");
//        dialog.setContentText("Wetteinsatz:");
//
//// Traditional way to get the response value.
//        Optional<String> bet = dialog.showAndWait();
//        if (bet.isPresent()){
//            System.out.println(playerName + ", bet: " + bet.get());
//        }
//
//// The Java 8 way to get the response value (with lambda expression).
//        bet.ifPresent(name -> System.out.println("Your name: " + playerName));
//    }


    public String getInput() {
        return this.playerInput.getText();
    }
    public String getPName1() {return this.playerName1.getText();
    }
    public String getPName2() {return this.playerName2.getText();
    }

}


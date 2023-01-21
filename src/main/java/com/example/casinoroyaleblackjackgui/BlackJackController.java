package com.example.casinoroyaleblackjackgui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    protected void onStartClick() throws IOException
    {
        System.out.println(playerInput.getText());

////        System.out.println(verticalBox.getScene().getWindow());
//        Window w = verticalBox.getScene().getWindow();
//        Parent root = FXMLLoader.load(getClass().getResource("black-jack-2.fxml"));
//        Scene scene = new Scene(root);
//
//        scene.setRoot(root);
    }

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("black-jack.fxml"));
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
    public String getInput() {
        return this.playerInput.getText();
    }

}


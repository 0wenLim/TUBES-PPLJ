package com.example.tubesppljj;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuView {
    @FXML
    private Label welcomeText;
    @FXML
    private Label roomLabel;
    @FXML
    private TextField roomField;
    @FXML
    private Button newButton;
    @FXML
    private Button histButton;
    @FXML
    private Button joinButton;



    @FXML
    protected void onNewButtonClick(MouseEvent e) throws IOException {
        //kirim id token am id user

        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("waiting-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }
    @FXML
    protected void onHistoryButtonClick() {
        System.out.println("history");
        //

    }

    public void onJoinButtonClick() throws IOException {
        System.out.println("join");
        // id user

        //Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        Stage stage = (Stage) joinButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("join-view.fxml"));
        Scene scene = new Scene(fxmlLoader.<Parent>load(), 320, 240);
        stage.setScene(scene);
    }
}
package com.example.tubesppljj;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    @FXML
    private Label welcomeText;
    @FXML
    private Label usernameLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Button loginButton;

    @FXML
    protected void onUsernameFieldTextChange() {
//        System.out.println(usernameField.getText());
    }


    @FXML
    protected void onPasswordFieldChange() {
//        System.out.println(passwordField.getText());
    }

    @FXML
    protected void onLoginButtonClick(MouseEvent e) throws IOException {
        // kirim username & password ke server
        System.out.println("login clicked");
//
//        HttpClient httpClient = new HttpClient();
//        System.out.println("login clicked");
//        String idToken = httpClient.signIn(usernameField.getText(), passwordField.getText());
//        System.out.println(idToken);
//        // sign in fail
//        if (idToken == "") {
//            return;
//        }

        //insert jwt to database
        //Database database = new Database();
        //database.insertJwt(idToken);

        //next scene
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }
}
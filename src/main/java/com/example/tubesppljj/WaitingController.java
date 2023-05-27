package com.example.tubesppljj;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Arrays;

public class WaitingController{
    @FXML
    private Label waitingText;
    @FXML
    private Button cancelButton;
    public WaitingController(){
        Timeline timeline = new Timeline (new KeyFrame(Duration.seconds(1), ev ->  {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            System.out.println(cancelButton);
            System.out.println(cancelButton.getScene());
            System.out.println(cancelButton.getScene().getWindow());
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ingame-view.fxml"));
            Scene scene = null;
            try {
                System.out.println(fxmlLoader);
                scene = new Scene(fxmlLoader.load(), 320, 240);
                System.out.println(scene);
                stage.setScene(scene);
                System.out.println(stage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
    @FXML
    protected void onCancelButtonClick(MouseEvent e) throws IOException {
        // kirim username & password ke server
        System.out.println("login clicked");
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        // Stage stage = (Stage) loginButton.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("menu-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setScene(scene);
    }


}

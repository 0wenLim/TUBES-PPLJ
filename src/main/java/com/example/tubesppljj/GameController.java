package com.example.tubesppljj;

import com.example.tubesppljj.websocket.Client;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class GameController {
        private Client client;

        public GameController() {
                client = new Client();
                try {
                        client.connectBlocking();
                } catch (InterruptedException e) {
                        System.out.println("Interrupted Exception" + e);
                }
        }

        @FXML
        private TextField moveField;
        @FXML
        private Button buttonA1;
        @FXML
        private Button buttonA2;
        @FXML
        private Button buttonA3;
        @FXML
        private Button buttonB1;
        @FXML
        private Button buttonB2;
        @FXML
        private Button buttonB3;
        @FXML
        private Button buttonC1;
        @FXML
        private Button buttonC2;
        @FXML
        private Button buttonC3;


        @FXML
        protected void onA1ButtonClick() { client.send("000");}

        @FXML
        protected void onA2ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onA3ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onB1ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onB2ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onB3ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onC1ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onC2ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onC3ButtonClick() {
                client.send("0000000");
        }

        @FXML
        protected void onMoveFieldTextChange() {
                client.send(moveField.getText());
                System.out.println(moveField.getText());
        }

}

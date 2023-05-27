package com.example.tubesppljj.websocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.util.Map;

public class Client extends WebSocketClient {
    private static URI SERVER_URL = URI.create("" + "https://79ce-182-253-194-46.ngrok-free.app");

    public Client() {
        super(Client.SERVER_URL);
    }

    public Client(Draft draft) {
        super(Client.SERVER_URL, draft);
    }

    public Client(Map<String, String> httpHeaders) {
        super(Client.SERVER_URL, httpHeaders);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        System.out.println("Opened connection " + handshake);
    }

    @Override
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Closed connection " + code + " " + reason);
    }

    @Override
    public void onError(Exception ex) {
        System.out.println(ex);
    }

    /*
    public static void main(String[] args) throws InterruptedException {
        Client c = new Client();
        c.connectBlocking();

        c.send("HALO");
    }

     */
}

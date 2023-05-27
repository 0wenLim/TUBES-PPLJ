package com.example.tubesppljj.http;

public class LoginPayload {
    public String username;
    public String password;

    public LoginPayload(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

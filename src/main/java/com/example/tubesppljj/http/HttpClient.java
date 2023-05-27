package com.example.tubesppljj.http;

import com.example.tubesppljj.sqlite.Database;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.*;

import java.io.IOException;
import java.sql.SQLException;

public class HttpClient {
    public static final String BASE_URL = "https://79ce-182-253-194-46.ngrok-free.app";
    public static final MediaType JSONMedia = MediaType.parse("application/json");

    OkHttpClient client;
    public HttpClient() {
        client = new OkHttpClient();
    }

    public String signIn(String username, String password) throws IOException {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        System.out.println(username);
        String json = gson.toJson(new LoginPayload(username, password));
        RequestBody body = RequestBody.create(JSONMedia, json);
        Request request = new Request.Builder().post(body).url(BASE_URL + "/login").build();

        Response response = client.newCall(request).execute();
        LoginResponse resp = gson.fromJson(response.body().string(), LoginResponse.class);

        Database db = new Database();
        try {
            db.insertJwt(resp.id_token);
        } catch (SQLException e) {
            // do nothng???
        }
        return resp.id_token;
    }

    public void EXAMPLE_REMOVE_THIS_LATER() {
        // take idtoken/jwt from database
        Database db = new Database();
        Gson gson = new Gson();

        try {
            String idToken = db.getCurrentUser();
            Request request = new Request.Builder()
                    .addHeader("Authorization", idToken)
                    .url(BASE_URL + "/login")
                    .build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

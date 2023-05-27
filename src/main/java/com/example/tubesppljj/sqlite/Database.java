package com.example.tubesppljj.sqlite;

import java.sql.*;

public class Database {
    public Database() {
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:session");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public String getCurrentUser() throws SQLException {
        Connection connection = this.connect();

        if (connection == null) {
            return "";
        }
        Statement statement = connection.createStatement();

        statement.setQueryTimeout(30);  // set timeout to 30 sec.

        ResultSet result = statement.executeQuery("select * from user");
        // asumsinya, data yang terakhir masuk yang paling baru
        boolean next = result.next();
        String jwt = "";
        while (next) {
            jwt = result.getString("jwt");
            next = result.next();
        }

        connection.close();
        return jwt;
    }

    public void createUserTable() throws SQLException {
        Connection connection = this.connect();

        if (connection == null) {
            return;
        }
        Statement statement = connection.createStatement();
        statement.executeUpdate("create table if not exists user (jwt string)");
        connection.close();
    }


    public static void insertJwt(String jwt) throws SQLException {
        Database database = new Database();
        database.createUserTable();

        Connection connection = database.connect();

        if (connection == null) {
            return;
        }
        Statement statement = connection.createStatement();

        PreparedStatement pstmt = connection.prepareStatement("insert into user (jwt) values (?)");
        pstmt.setString(1, jwt);
        pstmt.executeUpdate();
        connection.close();
    }

    public static void main(String[] args) {
        Database db = new Database();
        try {
            db.createUserTable();
            db.insertJwt("haloooo");
            System.out.println(db.getCurrentUser());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

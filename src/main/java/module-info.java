module com.example.tubesppljj {
    requires javafx.controls;
    requires javafx.fxml;
    requires Java.WebSocket;
    requires java.sql;
    requires okhttp;
    requires com.google.gson;

    opens com.example.tubesppljj to javafx.fxml;
    exports com.example.tubesppljj;

}
package org.companion.myunicompanion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login(Scene1).fxml"));
        Scene scene = new Scene(fxmlLoader.load(),600,400);
        stage.setTitle("Login Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().setAll(new Image(Main.class.getResource("ringlogo-01(1)(1).png").toString()));
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
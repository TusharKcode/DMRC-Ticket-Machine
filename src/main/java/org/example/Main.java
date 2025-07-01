package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        welcomeScreen welcomeScreen = new welcomeScreen();
        Scene scene = welcomeScreen.createWelcomeScene(stage);

        stage.setTitle("DMRC Ticket Machine");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

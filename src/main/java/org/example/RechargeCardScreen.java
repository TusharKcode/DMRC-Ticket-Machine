package org.example;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RechargeCardScreen {
    public static void createInsertCardScreen(Stage primaryStage){
        Label instruction = new Label("Please insert or place your card on the machine to proceed");
        instruction.setStyle("-fx-font-size: 20px, -fx-text-fill: #333");

        VBox root = new VBox(30, instruction);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #e0e0e0 #f5f5f5)");

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);

        //--------------------------------------------->>>> Simulate Card detection after 3 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> {
            createCardDetailsScreen(primaryStage);
        });
        pause.play();
    }

    public static void createCardDetailsScreen(Stage primaryStage){

    }
}
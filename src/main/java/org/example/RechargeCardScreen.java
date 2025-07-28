package org.example;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
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
        //------------------------------------------------>>>> Dummy Card
        String cardNumber = "DMRC123456789";
        double currentBalance = 180.0;
        double maxBalance = 3000.0;

        Label title = new Label("Card Details");
        title.setStyle("-fx-font-size: 24px, -fx-font-weight: bold");

        Label cardlabel = new Label("Card Number: " + cardNumber);
        Label balanceLabel =new Label("Current Balance: " + currentBalance);
        Label maxLabel = new Label("Maximum Allowed Balance: " + maxBalance);

        VBox cardInfoBox = new VBox();
        cardInfoBox.setStyle("-fx-background-color: #ffffff, -fx-background-radius: 8px, -fx-border-color: #ccc, -fx-border-radius: 8px,");
        cardInfoBox.setPadding(new Insets(20));
        cardInfoBox.setAlignment(Pos.CENTER_LEFT);

        Button cashBtn = new Button("Cash Payment");
        Button onlineBtn = new Button("Online Payment");

        cashBtn.setStyle("-fx-font-size: 16px");
        cashBtn.setOnAction(e -> createCashPaymentScreen(primaryStage, cardNumber, currentBalance, maxBalance));

        onlineBtn.setStyle("-fx-font-size: 16px");
        onlineBtn.setOnAction(e -> createOnlinePaymentScreen(primaryStage, cardNumber, currentBalance, maxBalance));

        HBox buttonBox = new HBox(30, cashBtn, onlineBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(30, cardInfoBox, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f2f2f2");

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
    }
}
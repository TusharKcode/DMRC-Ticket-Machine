package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class RechargeCardScreen {
    public static void show(Stage stage){
        // Title
        Label title = new Label("Select Recharge Mode");
        title.setFont(new Font("Arial", 28));
        title.setTextFill(Color.DARKBLUE);
        title.setAlignment(Pos.CENTER);

        // Buttons
        Button cashBtn = new Button("Cash");
        cashBtn.setStyle("-fx-font-size: 18px");
        cashBtn.setPrefSize(150, 60);

        Button onlineBtn = new Button("Online");
        onlineBtn.setStyle("-fx-font-size: 18px");
        onlineBtn.setPrefSize(150, 60);

        // Buttons Action
        cashBtn.setOnAction(e -> CashRechargeScreen.show(stage));
        onlineBtn.setOnAction(e -> OnlineRechargeScreen.show(stage));

        HBox rechargeOptions = new HBox(30, cashBtn, onlineBtn);
        rechargeOptions.setAlignment(Pos.CENTER);

        // Card Box info
        Label cardInfo = new Label("Card Number: 1234567\nCurrent Balance: ₹250\nMax Balance: ₹2000");
        cardInfo.setFont(new Font("Arial", 16));
        cardInfo.setStyle("-fx-border-color: gray; -fx-border-width: 2; -fx-padding: 15;");

        VBox cardInfoBox = new VBox(cardInfo);
        cardInfoBox.setAlignment(Pos.CENTER);

        // Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        cancelBtn.setPrefWidth(120);
        cancelBtn.setFont(new Font("Arial", 16));
        welcomeScreen welcome = new welcomeScreen();
        cancelBtn.setOnAction(e -> {
            Scene welcomeScene = welcome.createWelcomeScene(stage);
            stage.setScene(welcomeScene);
        });

        VBox root = new VBox(30, title,cardInfoBox, rechargeOptions, cancelBtn);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f4f8ff;");

        VBox.setMargin(cancelBtn, new Insets(40,0,0,0));

        Scene scene = new Scene(root, 600, 500);
        stage.setScene(scene);
        stage.show();
    }

    public static class CashRechargeScreen{
        public static void show(Stage stage){
            System.out.println("Card recharge screen");
        }
    }
    public static class OnlineRechargeScreen{
        public static void show(Stage stage){
            System.out.println("Online recharge screen");
        }
    }
}
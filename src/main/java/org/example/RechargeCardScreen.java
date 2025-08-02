package org.example;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class RechargeCardScreen {
    public static void createInsertCardScreen(Stage primaryStage){
        Label instruction = new Label("Please insert or place your card on the machine to proceed");
        instruction.setStyle("-fx-font-size: 20px; -fx-text-fill: #333;");

        VBox root = new VBox(30, instruction);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(50));
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #e0e0e0 #f5f5f5);");

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
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label cardlabel = new Label("Card Number: " + cardNumber);
        Label balanceLabel =new Label("Current Balance: " + currentBalance);
        Label maxLabel = new Label("Maximum Allowed Balance: " + maxBalance);

        VBox cardInfoBox = new VBox();
        cardInfoBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8px; -fx-border-color: #ccc; -fx-border-radius: 8px;");
        cardInfoBox.setPadding(new Insets(20));
        cardInfoBox.setAlignment(Pos.CENTER_LEFT);

        Button cashBtn = new Button("Cash Payment");
        Button onlineBtn = new Button("Online Payment");

        cashBtn.setStyle("-fx-font-size: 16px;");
        cashBtn.setOnAction(e -> createCashPaymentScreen(primaryStage, cardNumber, currentBalance, maxBalance));

        onlineBtn.setStyle("-fx-font-size: 16px;");
        onlineBtn.setOnAction(e -> createOnlinePaymentScreen(primaryStage, cardNumber, currentBalance, maxBalance));

        HBox buttonBox = new HBox(30, cashBtn, onlineBtn);
        buttonBox.setAlignment(Pos.CENTER);

        VBox root = new VBox(30, cardInfoBox, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
        root.setStyle("-fx-background-color: #f2f2f2;");

        Scene scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
    }

    public static void createCashPaymentScreen(Stage primaryStage, String cardNumber, double currentBalance, double maxBalance){
        Label title = new Label("Cash Recharge");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label cardLabel = new Label("Card number: " + cardNumber);
        Label balanceLabel = new Label("Current balance: " + currentBalance);
        Label maxLabel = new Label("Maximum balance: " + maxBalance);

        VBox cardBox = new VBox(10, cardLabel, balanceLabel, maxLabel);
        cardBox.setAlignment(Pos.CENTER_LEFT);
        cardBox.setPadding(new Insets(20));
        cardBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8px; -fx-border-color: #ccc; -fx-border-radius: 8px;");

        Label chooseLabel = new Label("Select Recharge Amount");
        chooseLabel.setStyle("-fx-font-size: 16px;");

        HBox amountBtn = new HBox(15);
        amountBtn.setAlignment(Pos.CENTER);
        int[] amounts = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200, 1300, 1400, 1500, 1600, 1700, 1800, 1900, 2000};

        Label insertInstructions = new Label();
        Button completeBtn = new Button("Complete Payment");
        completeBtn.setVisible(false);

        for (int amount : amounts) {
            Button amtBtn = new Button("₹" + amount);
            amtBtn.setOnAction(e -> {
                insertInstructions.setText("Please insert ₹ " + amount + " in cash to continue...");
                completeBtn.setUserData(amount);
                completeBtn.setVisible(true);
            });
            amountBtn.getChildren().add(amtBtn);
        }
        insertInstructions.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

        completeBtn.setOnAction(e -> {
            int rechargeAmt = (int) completeBtn.getUserData();
            double newBalance = currentBalance + rechargeAmt;

            if (newBalance > maxBalance){
                showAlert("Limit Exceeded, You cannot exceed the ₹ " + maxBalance + " card balance.");
            } else {
                createReachargeSuccessScreen(primaryStage, cardNumber, currentBalance, rechargeAmt);
            }
        });

        VBox root = new VBox(25, title, cardBox, chooseLabel, amountBtn, insertInstructions, completeBtn);
        root.setStyle("-fx-background-color: #f2f2f2");
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 850);
        primaryStage.setScene(scene);
    }
    private static void showAlert(String title, String message){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void createOnlinePaymentScreen(Stage primaryStage, String cardNumber, double currentBalance, double maxBalance){
        Label title = new Label("Online Recharge");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label cardLabel = new Label("Card number: " + cardNumber);
        Label balanceLabel = new Label("Current balance: " + currentBalance);
        Label maxLabel = new Label("Maximum balance: " + maxBalance);

        VBox cardBox = new VBox(10, cardLabel, balanceLabel, maxLabel);
        cardBox.setAlignment(Pos.CENTER_LEFT);
        cardBox.setPadding(new Insets(20));
        cardBox.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 8px; -fx-border-color: #ccc; -fx-border-radius: 8px;");

        Label qrInstruction = new Label("Scan the QR code to complete the payment");
        qrInstruction.setStyle("-fx-font-size: 16px;");

        Image qrImage = new Image(RechargeCardScreen.class.getResource("/images/qr.png").toExternalForm());
        ImageView qrView = new ImageView(qrImage);
        qrView.setFitWidth(200);
        qrView.setPreserveRatio(true);

        Label amountLabel = new Label("Recharge Amount: ₹500");
        amountLabel.setStyle("-fx-font-size: 16px;");

        Button confirmBtn = new Button("Paid Successfully");
        confirmBtn.setOnAction(e -> {
            double rechargeAmount = 500;
            double newBalance = currentbalance + rechargeAmount;
            if (newBalance > maxBalance){
                showAlert("Limit Exceeded, You cannot exceed the ₹ " + maxBalance + " card balance.");
            } else {
                createReachargeSuccessScreen(primaryStage, cardNumber, currentBalance, rechargeAmount);
            }
        });

        VBox root = new VBox(25, title, cardBox, amountLabel, qrInstruction, qrView, confirmBtn);
        root.setStyle("-fx-background-color: #f2f2f2");
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 800, 850);
        primaryStage.setScene(scene);
    }
}
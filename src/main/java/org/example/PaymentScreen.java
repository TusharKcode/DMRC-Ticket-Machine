package org.example;

import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PaymentScreen {
    private final String originStation;
    private final String destinationStation;
    private final double fare;

    public PaymentScreen(String originStation, String destinationStation, double fare){
        this.originStation = originStation;
        this.destinationStation = destinationStation;
        this.fare = fare;
    }
    public Scene createPaymentScreen(Stage stage, Scene previousScene){
        Label headingLabel = new Label("Confirm Payment");
        headingLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #0d47a1");
        headingLabel.setAlignment(Pos.CENTER);

        Label formLabel = new Label("Form: " + originStation);
        Label toLabel = new Label("To: " + destinationStation);
        Label fareLabel = new Label("Fare: ₹" + (int) fare);

        formLabel.setStyle("-fx-font-size: 16px;");
        toLabel.setStyle("-fx-font-size: 16px;");
        fareLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        //<<<<<<-----------------------------------------------------------------Ticket Summary

        VBox summaryBox = new VBox(10, formLabel, toLabel, fareLabel);
        summaryBox.setPadding(new Insets(15));
        summaryBox.setStyle(
                        "-fx-background-color: white;" +
                        "-fx-border-color: #bdbdbd;" +
                        "-fx-border-radius: 10;" +
                        "-fx-background-radius: 10;" +
                        "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0, 0, 4);"
        );

        summaryBox.getChildren().addAll(formLabel, toLabel, fareLabel);

        //<<<<<<-----------------------------------------------------------------Buttons
        Button cashBtn = new Button("Cash");
        Button onlineBtn = new Button("Online");

        stylePrimaryButton(cashBtn);
        stylePrimaryButton(onlineBtn);
        //<<<<<---------------------------------------------------------------------Pay Button

        Button payBtn = new Button("Pay");
        payBtn.setStyle("-fx-background-color: #90caf9; -fx-text-fill: white; -fx-background-radius: 10;");
        payBtn.setDisable(true); //<<<<<---------After choosing payment option, this button will enable.

        //<<<<<---------------------------------------------------------------------Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-background-radius: 10;");

        //<<<<<---------------------------------------------------------------------Payment Mode Logic
        final String[] selectedMode = {null};

        cashBtn.setOnAction(e ->{
            selectedMode[0] = "Cash";
            payBtn.setDisable(false);
            payBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-background-radius: 10;");
        });
        onlineBtn.setOnAction(e ->{
            selectedMode[0] = "Online";
            payBtn.setDisable(false);
            payBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white; -fx-background-radius: 10;");
            showQRCodePopup();
        });

        //<<<<<---------------------------------------------------------------------Pay Button Logic
        payBtn.setOnAction(e -> {
           if(selectedMode[0] != null){
               showSuccessPopup();
           }
        });
        cancelBtn.setOnAction(e -> stage.setScene(previousScene));

        HBox paymentOptions = new HBox(15, cashBtn, onlineBtn);
        paymentOptions.setAlignment(Pos.CENTER);

        VBox centerBox = new VBox(20, summaryBox, paymentOptions);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));

        HBox bottomBtn = new HBox(15, payBtn, cancelBtn);
        bottomBtn.setAlignment(Pos.CENTER_RIGHT);
        bottomBtn.setPadding(new Insets(0,20,20,20));

        BorderPane root = new BorderPane();
        root.setTop(headingLabel);
        root.setBottom(bottomBtn);
        root.setCenter(centerBox);
        BorderPane.setAlignment(headingLabel, Pos.CENTER);

        root.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f2fd, #ffffff);");

        Scene scene = new Scene(root,500, 500);
        return scene;
    }
    private void stylePrimaryButton(Button button){
        button.setStyle("-fx-background-color: #1976d2; -fx-background-radius: 10; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-cursor: hand;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: #1565c0; -fx-background-radius: 10; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-cursor: hand;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #1976d2; -fx-background-radius: 10; -fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: white; -fx-cursor: hand;"));
    }

    private void showQRCodePopup(){
        Stage qrStage = new Stage();
        VBox box = new VBox(10);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));

        Label label = new Label("Scan to pay");
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        //<<<<<---------------------------------------------------------------------QR Placeholder
        Image qrImage = new Image("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=DMRC_Payment");
        ImageView qrView = new ImageView(qrImage);
        qrView.setFitWidth(150);
        qrView.setPreserveRatio(true);

        box.getChildren().addAll(label, qrView);

        Scene scene = new Scene(box, 250, 300);
        qrStage.setTitle("QR Code");
        qrStage.setScene(scene);
        qrStage.show();
    }

    private void showSuccessPopup(){
        Stage successStage = new Stage();
        VBox box = new VBox(15);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(20));

        Label successLabel = new Label("Payment Successful");
        successLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: green;");

        Label fareLabel = new Label("₹" + (int)fare + "Paid Successful");
        fareLabel.setStyle("-fx-font-size: 16px;");

        box.getChildren().addAll(successLabel, fareLabel);

        Scene scene = new Scene(box, 300, 150);
        successStage.setTitle("Success");
        successStage.setScene(scene);
        successStage.show();
    }
}
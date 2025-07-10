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
        Label headingLabel = new Label("Confirm to Payment");
        headingLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //<<<<<<-----------------------------------------------------------------Ticket Summary
        VBox ticketBox = new VBox(5);
        ticketBox.setPadding(new Insets(10));
        ticketBox.setStyle("-fx-border-color: gray; -fx-border-width: 1; -fx-background-color: #f9f9f9;");

        Label formLabel = new Label("Form: " + originStation);
        Label toLabel = new Label("To: " + destinationStation);
        Label fareLabel = new Label("Fare: ₹" + (int) fare);

        ticketBox.getChildren().addAll(formLabel, toLabel, fareLabel);

        //<<<<<<-----------------------------------------------------------------Payment options
        HBox paymentOptions = new HBox(20);
        paymentOptions.setAlignment(Pos.CENTER);

        Button cashBtn = new Button("Cash");
        Button onlineBtn = new Button("Online");

        //<<<<<---------------------------------------------------------------------Pay Button
        Button payBtn = new Button("Pay");
        payBtn.setPrefWidth(120);
        payBtn.setDisable(true); //<<<<<---------After choosing payment option, this button will enable.

        //<<<<<---------------------------------------------------------------------QR Placeholder
        ImageView qrView = new ImageView();
        qrView.setFitWidth(150);
        qrView.setFitHeight(150);
        qrView.setVisible(false);

        Image qrImage = new Image("https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=DMRC_Payment");
        qrView.setImage(qrImage);
        //<<<<<---------------------------------------------------------------------Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(120);
        cancelBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");

        //<<<<<---------------------------------------------------------------------Payment Mode Logic
        final String[] selectedMode = {null};

        cashBtn.setOnAction(e ->{
            selectedMode[0] = "Cash";
            payBtn.setDisable(false);
            payBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");
            qrView.setVisible(false);
        });
        onlineBtn.setOnAction(e ->{
            selectedMode[0] = "Online";
            payBtn.setDisable(false);
            payBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");
            qrView.setVisible(true);
        });

        //<<<<<---------------------------------------------------------------------Pay Button Logic
        payBtn.setOnAction(e -> {
           if(selectedMode[0] != null){
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Payment Successful");
               alert.setHeaderText(null);
               alert.setContentText(" ₹ " + (int) fare + " payment succeeded using " + selectedMode[0] + "mode.");
               alert.showAndWait();

               stage.setScene(previousScene);
           }
        });
        cancelBtn.setOnAction(e -> stage.setScene(previousScene));

        VBox mainBox = new VBox(20, headingLabel, ticketBox, paymentOptions, payBtn, qrView, cancelBtn);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(20));

        paymentOptions.getChildren().addAll(cashBtn, onlineBtn);

        Scene scene = new Scene(mainBox,600, 600);
        scene.getStylesheets().add("style.css");
        return scene;
    }
}
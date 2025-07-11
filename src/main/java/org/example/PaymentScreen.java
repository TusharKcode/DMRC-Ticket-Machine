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
        headingLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #0d47a1");

        //<<<<<<-----------------------------------------------------------------Ticket Summary
        VBox summaryBox = new VBox(10);
        summaryBox.setPadding(new Insets(15));
        summaryBox.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: #bdbdbd;" +
                "-fx-border-radius: 10;" +
                "-fx-background-radius: 10;" +
                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0, 0, 4);"
        );

        Label formLabel = new Label("Form: " + originStation);
        Label toLabel = new Label("To: " + destinationStation);
        Label fareLabel = new Label("Fare: ₹" + (int) fare);

        formLabel.setStyle("-fx-font-size: 16px;");
        toLabel.setStyle("-fx-font-size: 16px;");
        fareLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        summaryBox.getChildren().addAll(formLabel, toLabel, fareLabel);

        //<<<<<<-----------------------------------------------------------------Payment options
        Button cashBtn = new Button("Cash");
        Button onlineBtn = new Button("Online");

        //<<<<<---------------------------------------------------------------------Pay Button
        Button payBtn = new Button("Pay");
        payBtn.setPrefWidth(120);
        payBtn.setStyle("-fx-background-color: #90caf9; -fx-text-fill: white; -fx-background-radius: 10;");
        payBtn.setDisable(true); //<<<<<---------After choosing payment option, this button will enable.

        //<<<<<---------------------------------------------------------------------Cancel Button
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(120);
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
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
               alert.setTitle("Payment Successful");
               alert.setHeaderText(null);
               alert.setContentText(" ₹ " + (int) fare + " payment succeeded using " + selectedMode[0] + "mode.");
               alert.showAndWait();

               stage.setScene(previousScene);
           }
        });
        cancelBtn.setOnAction(e -> stage.setScene(previousScene));

        HBox paymentOptions = new HBox(15, cashBtn, onlineBtn);
        paymentOptions.setAlignment(Pos.CENTER);

        VBox mainBox = new VBox(20, headingLabel, summaryBox, paymentOptions, payBtn, cancelBtn);
        mainBox.setAlignment(Pos.TOP_CENTER);
        mainBox.setPadding(new Insets(30));

        paymentOptions.getChildren().addAll(cashBtn, onlineBtn);

        StackPane root = new StackPane(mainBox);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #e3f2fd, #ffffff);");

        Scene scene = new Scene(mainBox,500, 500);
        return scene;
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





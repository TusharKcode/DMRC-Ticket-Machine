package org.example;

import javafx.animation.PauseTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.util.HashMap;
import java.util.Map;

public class RechargeCardScreen {
    public static void show(Stage stage){
        showRechargeOptionsScreen(stage);
//        showCardDetectionScreen(stage);
    }
        // Step 1 -> Show "Please insert card" screen
        /*private static void showCardDetectionScreen(Stage stage){
            Label instruction = new Label("Please insert your card into the machine");
            instruction.setTextFill(Color.DARKBLUE);
            instruction.setAlignment(Pos.CENTER);
            instruction.setFont(new Font("Arial", 22));
            instruction.setWrapText(true);

            VBox root = new VBox(instruction);
            root.setAlignment(Pos.CENTER);
            root.setPadding(new Insets(50));
            root.setStyle("-fx-background-color: #eef4fa;");

            Scene detectionScene = new Scene(root,600,500);
            stage.setScene(detectionScene);
            stage.show();

            // Simulate card detection after 5 seconds
            PauseTransition waitForDetection = new PauseTransition(Duration.seconds(5));
            waitForDetection.setOnFinished(event -> {
                boolean cardDetected = true;  // simulate detection

                if(cardDetected){
                    showRechargeOptionsScreen(stage);
                }
            });
            waitForDetection.play();

            // Timeout if card not detected within 5 seconds
            PauseTransition timeOut = new PauseTransition(Duration.seconds(5));
            timeOut.setOnFinished(event -> {
                boolean cardDetected = false; // assumes not detected

                if(!cardDetected){
                    welcomeScreen welcome = new welcomeScreen();
                    Scene welcomeScene = welcome.createWelcomeScene(stage);
                    stage.setScene(welcomeScene);
                }
            });
            timeOut.play();
        }*/
    public static void showRechargeOptionsScreen(Stage stage){
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

        private static final Map<Integer, String[]> noteImg = new HashMap<>(){{
            put(100, new String[]{"/20rsNote.jpg","/50rsNote.jpg","/100rsNote.jpg"});
            put(200, new String[]{"/200rsNote.jpg","/100rsNote.jpg"});
            put(500, new String[]{"/200rsNote.jpg","/100rsNote.jpg"});
            put(1000, new String[]{"/200rsNote.jpg","/500rsNote.jpg"});
            put(1500, new String[]{"/500rsNote.jpg"});
            put(2000, new String[]{"/500rsNote.jpg"});
        }};

        public static void show(Stage stage){
            Label title = new Label("Select Amount to Recharge");
            title.setFont(new Font("Arial", 24));
            title.setTextFill(Color.DARKBLUE);

            Label selectAmountLabel = new Label("Amount: ₹0");
            selectAmountLabel.setTextFill(Color.FORESTGREEN);
            selectAmountLabel.setFont(new Font("Arial", 20));

            VBox noteImgBox = new VBox();
            noteImgBox.setAlignment(Pos.CENTER);
            noteImgBox.setSpacing(10);

            VBox cardInfoBox = new VBox(selectAmountLabel, noteImgBox);
            cardInfoBox.setStyle("-fx-border-color: gray; -fx-border-width: 2; -fx-padding: 15;");
            cardInfoBox.setAlignment(Pos.CENTER);

            // Create Amount buttons
            VBox grid = new VBox();
            grid.setAlignment(Pos.CENTER);

            int[] amounts = {100, 200, 300, 400, 500, 1000, 1500, 2000};
            for (int i = 0; i < amounts.length; i += 4) {
                HBox row = new HBox(20);
                row.setAlignment(Pos.CENTER);
                for(int j = i; j < i+4 && j < amounts.length; j++){
                    int amt = amounts[j];
                    Button btn = new Button("₹" + amt);
                    btn.setPrefSize(100, 50);
                    btn.setStyle("-fx-font-size: 16px;");
                    btn.setOnAction(e -> {
                        selectAmountLabel.setText("Amount: ₹" + amt);
                        noteImgBox.getChildren().clear();
                        if(noteImgBox.containsKey(amt)){
                            for(String imgFile : noteImg.get(amt)){
                                ImageView imgView = new ImageView(new Image(imgFile));
                                imgView.setFitHeight(40);
                                imgView.setPreserveRatio(true);
                                noteImgBox.getChildren().add(imgView);
                            }
                        }
                    });
                    row.getChildren().add(btn);
                }
                grid.getChildren().add(row);
            }
            // Cancel button
            Button cancelBtn = new Button("Cancel");
            cancelBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
            cancelBtn.setPrefWidth(120);
            cancelBtn.setFont(new Font("Arial", 16));
            cancelBtn.setOnAction(e -> showRechargeOptionsScreen(stage));

            VBox root = new VBox(25, title, grid, selectAmountLabel, cancelBtn);
            root.setAlignment(Pos.TOP_CENTER);
            root.setPadding(new Insets(30));
            root.setStyle("-fx-background-color: #f4f8ff;");
            VBox.setMargin(cancelBtn, new Insets(30,0,0,0));

            Scene scene = new Scene(root, 600, 550);
            stage.setScene(scene);
            stage.show();
        }
    }
    public static class OnlineRechargeScreen{
        public static void show(Stage stage){
            System.out.println("Online recharge screen");
        }
    }
}
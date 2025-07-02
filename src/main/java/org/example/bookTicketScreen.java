package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.List;

public class bookTicketScreen {

    public Scene createBookTicketScene(Stage stage, Scene welcomeScene, List<String> stationList) {
        Label bookTitle = new Label("DMRC Ticket Machine");
        bookTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        Label bookClock = new Label();
        bookClock.setStyle("-fx-font-size: 14px;");
        utils.updateClock(bookClock);

        Timeline bookClockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e2 ->
                utils.updateClock(bookClock)));
        bookClockTimeline.setCycleCount(Timeline.INDEFINITE);
        bookClockTimeline.play();

        HBox bookTitleBar = new HBox(20, bookTitle, bookClock);
        bookTitleBar.setPadding(new Insets(15,20,10,20));
        bookTitleBar.setAlignment(Pos.CENTER_LEFT);
        bookTitleBar.setStyle("-fx-background-color: white;");

        VBox header = new VBox(bookTitleBar);

        Label fromLabel = new Label("From Station:");
        ComboBox<String> fromComboBox = new ComboBox<>();
        fromComboBox.getItems().addAll(stationList);
        fromComboBox.setPromptText("Select From");

        VBox fromBox = new VBox(10, fromLabel, fromComboBox);
        fromBox.setAlignment(Pos.CENTER);
        fromBox.setPadding(new Insets(10));
        fromBox.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");
        fromBox.setPrefWidth(250);

        Label toLabel = new Label("To Station:");
        ComboBox<String> toComboBox = new ComboBox<>();
        toComboBox.getItems().addAll(stationList);
        toComboBox.setPromptText("Select To");

        VBox toBox = new VBox(10, toLabel, toComboBox);
        toBox.setAlignment(Pos.CENTER);
        toBox.setPadding(new Insets(10));
        toBox.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");
        toBox.setPrefWidth(250);

        HBox centerBox = new HBox(30, fromBox, toBox);
        centerBox.setAlignment(Pos.CENTER);
        centerBox.setPadding(new Insets(20));

        Label distanceLabel = new Label("Distance: 0 km");
        Label fareLabel = new Label("Fare: ₹0");
        fareLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

        HBox infoBox = new HBox(40, distanceLabel, fareLabel);
        infoBox.setAlignment(Pos.CENTER);
        infoBox.setPadding(new Insets(10));

        Button confirmBtn = new Button("Confirm");
        Button backBtn = new Button("Back");

        HBox buttonBox = new HBox(30, confirmBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(15));

        fromComboBox.setOnAction(ev -> updateFareAndDistance(fromComboBox, toComboBox, fareLabel, distanceLabel));
        toComboBox.setOnAction(ev -> updateFareAndDistance(fromComboBox, toComboBox, fareLabel, distanceLabel));
                                                                                //Confirm Button here
        confirmBtn.setOnAction(ev -> {
            String fromStation = fromComboBox.getValue();
            String toStation = toComboBox.getValue();

            if(fromStation != null && toStation !=  null && !fromStation.equals(toStation)){
                int fromIndex = fromComboBox.getItems().indexOf(fromStation);
                int toIndex = toComboBox.getItems().indexOf(toStation);
                int fare = Math.abs(fromIndex - toIndex) * 10 + 5;

                appData.ticketIssued++;                                             //Update App Data
                appData.totalBalance += fare;

                                                                                        //Add Log Entry
                String logEntry = "Ticket booked from " + fromStation + " to " + toStation + " | Fare: ₹" + fare;
                appData.logs.add(logEntry);
                System.out.println("Ticket Booked! Total tickets: " + appData.ticketIssued +
                        "Total Balance: ₹" + appData.totalBalance);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ticket Booked!");
                alert.setHeaderText(null);
                alert.setContentText("Ticket Booked Successfully!\n Fare: ₹" + fare);
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Invalid Selection");
                alert.setHeaderText(null);
                alert.setContentText("Please select valid From and To station");
                alert.showAndWait();
            }
        });

        backBtn.setOnAction(ev -> {
            boolean wasMaximized = stage.isMaximized();
            stage.setScene(welcomeScene);
            if (wasMaximized) {
                stage.setMaximized(false);
                stage.setMaximized(true);
            }
        });

        VBox mainLayout = new VBox(20, header, centerBox, infoBox, buttonBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(10));

        Scene bookScene = new Scene(mainLayout, 700, 500);

        URL cssURL = getClass().getResource("/style.css");
        if (cssURL != null) {
            bookScene.getStylesheets().add(cssURL.toExternalForm());
        }
        return bookScene;
    }

    private void updateFareAndDistance(ComboBox<String> from, ComboBox<String> to, Label fareLabel, Label distanceLabel) {
        String fromStation = from.getValue();
        String toStation = to.getValue();

        if (fromStation != null && toStation != null && !fromStation.equals(toStation)) {
            int fromIndex = from.getItems().indexOf(fromStation);
            int toIndex = to.getItems().indexOf(toStation);
            int distance = Math.abs(fromIndex - toIndex) * 2;
            int fare = Math.abs(fromIndex - toIndex) * 10 + 5;

            fareLabel.setText("Fare: ₹" + fare);
            distanceLabel.setText("Distance: " + distance + " km");
        } else {
            fareLabel.setText("Fare: ₹0");
            distanceLabel.setText("Distance: 0 km");
        }
    }
}

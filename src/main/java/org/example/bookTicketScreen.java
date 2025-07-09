package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import java.util.List;

public class bookTicketScreen {

            //-------------------------------------------------------------------->>>>>> Heading
    public Scene createBookTicketScene(Stage stage, Scene previousScene) {
        Label headingLabel = new Label("Select Destination Station");
        headingLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

        //-------------------------------------------------------------------->>>>>>Alphabet Area
        FlowPane alphabetPane = new FlowPane();
        alphabetPane.setVgap(5);
        alphabetPane.setHgap(5);
        alphabetPane.setAlignment(Pos.CENTER);
        alphabetPane.setPrefWrapLength(500);                //<<<<<<<-----Wrap on smaller screens

        //-------------------------------------------------------------------->>>>>> Station List
        VBox stationBox = new VBox();
        stationBox.setPadding(new Insets(5));
        stationBox.setSpacing(5);
        stationBox.setPrefWidth(250);
        stationBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-background-color: #f9f9f9;");

        Label stationPlaceholder = new Label("Select an Alphabet to see Stations");
        stationBox.getChildren().add(stationPlaceholder);

        ScrollPane stationScroll = new ScrollPane(stationBox);
        stationScroll.setPrefHeight(300);
        stationScroll.setFitToWidth(true);

        //-------------------------------------------------------------------->>>>>> Fare Box
        VBox fareBox = new VBox(10);
        fareBox.setPadding(new Insets(10));
        fareBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-background-color: #f0f8ff;");
        fareBox.setPrefWidth(200);

        Label fareLabel = new Label("Fare: ₹0");
        fareLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        Button continueBtn = new Button("Continue to Payment");
        continueBtn.setPrefWidth(160);
        continueBtn.setDisable(true);

        fareBox.getChildren().addAll(fareLabel, continueBtn);

        final String[] selectedStation = {null};            //<<<<------- To hold selected station

        //------------------------------------------------------------------>>>>>>>>Load Stations
        StationDAO dao = new StationDAO();
        List<Station> stationList = dao.getAllStations();

        double fareBase = 10.0;
        double perKmRate = 2.0;

        for (char c = 'A'; c <= 'Z' ; c++) {                //<<<<--------- Alphabet Buttons
            char letter = c;                                //<<<<--------- Local final copy
            Button letterBtn = new Button(String.valueOf(c));

            letterBtn.setOnAction(e -> {
                stationBox.getChildren().clear();

                List<Station> filteredStations = stationList.stream()
                        .filter(s -> s.getName().toUpperCase().startsWith(String.valueOf(letter)))
                        .toList();

                if(filteredStations.isEmpty()){
                    Label noStations = new Label("No Stations of this Letter: " + letter);
                    noStations.setStyle("-fx-text-fill: red;");
                    stationBox.getChildren().add(noStations);
                } else {
                    for(Station s : filteredStations){
                        HBox stationItem = new HBox(10);
                        stationItem.setAlignment(Pos.CENTER_LEFT);
                        stationItem.setStyle("-fx-background-color: #ffffff;" +
                                "-fx-border-radius: 8;" +
                                "-fx-background-radius: 8;" +
                                "-fx-border-color: #e0e0e0;" +
                                "-fx-border-width: 1;" +
                                "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.05), 5, 0, 0, 2);");
                        stationItem.setPadding(new Insets(8));

                        Circle colorCircle = new Circle(8);
                        // Check and set safe color
                        String colorString = s.getLine();
                        if (colorString == null || colorString.isEmpty()) {
                            colorString = "#808080"; // default gray
                        }
                        try {
                            colorCircle.setFill(Color.web(colorString, 1.0));
                        } catch (IllegalArgumentException ex) {
                            // fallback if invalid color value
                            colorCircle.setFill(Color.GRAY);
                        }

                        Label stationLabel = new Label(s.getName());
                        stationLabel.setStyle("-fx-font-size: 14px; -fx-font-weight: normal;");

                        stationItem.getChildren().addAll(colorCircle, stationLabel);
                        stationItem.setOnMouseEntered(ev ->
                            stationItem.setStyle(
                                    "-fx-background-color: #e3f2fd;" + // light blue on hover
                                            "-fx-border-radius: 8;" +
                                            "-fx-background-radius: 8;" +
                                            "-fx-border-color: #64b5f6;" +
                                            "-fx-border-width: 1;" +
                                            "-fx-effect: dropshadow(three-pass-box, " +
                                            "rgba(0,0,0,0.1), 5, 0, 0, 2);"
                            ));

                        stationItem.setOnMouseExited(ev -> stationItem.setStyle(
                                    "-fx-background-color: #ffffff;" +
                                            "-fx-border-radius: 8;" +
                                            "-fx-background-radius: 8;" +
                                            "-fx-border-color: #e0e0e0;" +
                                            "-fx-border-width: 1;" +
                                            "-fx-effect: dropshadow(three-pass-box, " +
                                            "rgba(0,0,0,0.05), 5, 0, 0, 2);"
                        ));

                        stationItem.setOnMouseClicked(ev -> {
                            selectedStation[0] = s.getName();

                            double distance = s.getDistanceFromOrigin();
                            double calculatedFare = fareBase + (distance * perKmRate);
                            int  roundedfare = (int) Math.round(calculatedFare);

                            fareLabel.setText("Fare: ₹" + roundedfare);
                            continueBtn.setDisable(false);
                            continueBtn.setStyle("-fx-background-color: #4caf50; -fx-text-fill: white;");
                            System.out.println("Selected Station: " + s.getName() + ", Distance: " + distance + "km, Fare: ₹" + roundedfare);
                        });
                        stationBox.getChildren().add(stationItem);
                    }
                }
            });
            letterBtn.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white;");
            alphabetPane.getChildren().add(letterBtn);
        }
        //-------------------------------------------------------------->>>>>Continue Button action
        continueBtn.setOnAction(e -> {
            if(selectedStation[0] != null){
                System.out.println("Proceeding with: " + selectedStation[0]);
            }
        });

        HBox lowerBoxes = new HBox(20, stationScroll, fareBox);
        lowerBoxes.setAlignment(Pos.CENTER);
        HBox.setHgrow(stationScroll, Priority.ALWAYS);
        HBox.setHgrow(fareBox, Priority.NEVER);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(120);
        cancelBtn.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white;");
        cancelBtn.setOnAction(e -> stage.setScene(previousScene));

        VBox mainLayout = new VBox(20, headingLabel, alphabetPane, lowerBoxes, cancelBtn);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));

        VBox.setVgrow(lowerBoxes, Priority.ALWAYS);

        Scene scene = new Scene(mainLayout, 900, 500);
        scene.getStylesheets().add("style.css");    //<<<<<<<---------------CSS File Linked here
        return scene;
    }
}

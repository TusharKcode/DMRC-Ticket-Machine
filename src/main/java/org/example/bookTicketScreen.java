package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;

public class bookTicketScreen {

    //-------------------------------------------------------------------->>>>>> Heading
    public Scene createBookTicketScene(Stage stage, Scene previousScene, List<String> stationList) {

        Label headingLabel = new Label("Select Destination Station");
        headingLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        //-------------------------------------------------------------------->>>>>>Alphabet Area
        FlowPane alphabetPane = new FlowPane();
        alphabetPane.setVgap(5);
        alphabetPane.setHgap(5);
        alphabetPane.setAlignment(Pos.CENTER);

        //-------------------------------------------------------------------->>>>>> Station List
        VBox stationBox = new VBox();
        stationBox.setPadding(new Insets(10));
        stationBox.setSpacing(5);
        stationBox.setPrefWidth(250);
        stationBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-background-color: #f9f9f9;");

        Label stationPlaceholder = new Label("Select an Alphabet to see Stations");
        stationBox.getChildren().add(stationPlaceholder);

        //-------------------------------------------------------------------->>>>>> Fare Box
        VBox fareBox = new VBox(10);
        fareBox.setPadding(new Insets(10));
        fareBox.setStyle("-fx-border-color: gray; -fx-border-width: 1px; -fx-background-color: #f0f8ff;");
        fareBox.setPrefWidth(200);

        Label fareLabel = new Label("Fare: ₹0");
        Button continueBtn = new Button("Continue to Payment");
        fareBox.getChildren().addAll(fareLabel, continueBtn);

        final String[] selectedStation = {null};            //<<<<------- Store selected station

        for (char c = 'A'; c <= 'Z' ; c++) {                //<<<<--------- Alphabet Buttons
            char letter = c;                                //<<<<--------- Local final copy
            Button letterBtn = new Button(String.valueOf(c));
            letterBtn.setPrefWidth(40);

            letterBtn.setOnAction(e -> {
                stationBox.getChildren().clear();

                List<String> filteredStations = stationList.stream()
                        .filter(name -> name.toUpperCase().startsWith(String.valueOf(letter)))
                        .toList();

                if(filteredStations.isEmpty()){
                    Label noStations = new Label("No Stations of this Letter: " + letter);
                    noStations.setStyle("-fx-text-fill: red;");
                    stationBox.getChildren().add(noStations);
                } else {
                    for(String station : filteredStations){
                        Button stationBtn = new Button(station);
                        stationBtn.setPrefWidth(200);
                        stationBtn.setOnAction(ev -> {
                            selectedStation[0] = station;
                            fareLabel.setText("Fare: ₹40");
                            continueBtn.setDisable(false);
                            System.out.println("Selected Station: " + station);
                        });
                        stationBox.getChildren().add(stationBtn);
                    }
                }
            });
            alphabetPane.getChildren().add(letterBtn);
        }

        //-------------------------------------------------------------->>>>>Continue Button action
        continueBtn.setOnAction(e -> {
            if(selectedStation[0] != null){
                System.out.println("Proceeding with: " + selectedStation[0]);
            }
        });

        HBox lowerBoxes = new HBox(20, stationBox, fareBox);
        lowerBoxes.setAlignment(Pos.CENTER);

        Button cancelBtn = new Button("Cancel");
        cancelBtn.setPrefWidth(120);
        cancelBtn.setOnAction(e -> stage.setScene(previousScene));

        VBox mainLayout = new VBox(20, headingLabel, alphabetPane, lowerBoxes, cancelBtn);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, 800, 500);
    }
}

package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.List;


public class bookTicketScreen {

    private final FlowPane stationBox = new FlowPane();

    public Scene createBookTicketScene(Stage stage, Scene previousScene, List<String> stationList) {

        Label titleLabel = new Label("Select Destination Station");
        titleLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");

        HBox alphabetBox = new HBox(5);
        alphabetBox.setAlignment(Pos.CENTER);

        for (char c = 'A'; c <= 'Z' ; c++) {
            char letter = c;                                //<<<<--------- Local final copy
            Button letterBtn = new Button(String.valueOf(c));
            letterBtn.setOnAction(e-> updateStationButtons(stationList, String.valueOf(letter)));
            letterBtn.setPrefWidth(30);
            alphabetBox.getChildren().add(letterBtn);
            }

        stationBox.setPadding(new Insets(10));
        stationBox.setHgap(10);
        stationBox.setVgap(10);
        stationBox.setPrefWrapLength(400);
        stationBox.setAlignment(Pos.CENTER);

        updateStationButtons(stationList, "A");

        Button backBtn = new Button("Back");
        backBtn.setPrefWidth(120);
        backBtn.setOnAction(e -> {
            boolean wasMaximized = stage.isMaximized();
            stage.setScene(previousScene);
            if(wasMaximized){
                stage.setMaximized(false);
                stage.setMaximized(true);
            }
        });

        VBox mainLayout = new VBox(titleLabel, alphabetBox, stationBox, backBtn);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setPadding(new Insets(20));

        return new Scene(mainLayout, 700, 500);
    }

    private void updateStationButtons(List<String> stationList, String letter){
        stationBox.getChildren().clear();

        List<String> filteredStations = stationList.stream()
                .filter(name -> name.toUpperCase().startsWith(letter.toUpperCase()))
                .toList();

        if(filteredStations.isEmpty()){
            Label noStations = new Label("No Stations of this Letter: " + letter);
            noStations.setStyle("-fx-text-fill: red;");
            stationBox.getChildren().add(noStations);
        } else {
            for(String station : filteredStations){
                Button stationBtn = new Button(station);
                stationBtn.setPrefWidth(150);
                stationBtn.setOnAction(e -> {
                    System.out.println("Selected Station: " + station);
                });
                stationBox.getChildren().add(stationBtn);
            }
        }
    }
}

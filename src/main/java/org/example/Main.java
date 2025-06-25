package org.example;

import javafx.animation.Animation;
import javafx.application.Application;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.util.Duration;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application{

    @Override
    public void start(Stage stage){
                                                                        //Load logo image
        Image logoImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/subway.png")));
        ImageView logoView = new ImageView(logoImg);
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(40);
        logoView.setCache(true);
        logoView.setSmooth(true);
        logoView.setId("subway");
                                                                        //Title Label (left-side)
        Label title = new Label("Welcome to the DMRC Machine");
        title.getStyleClass().add("title");
                                                                        //Combine Logo and title
        HBox titleLeft = new HBox(10, logoView, title);
        titleLeft.setAlignment(Pos.CENTER_LEFT);
                                                                //Station and Terminal ID (right-side)
        Properties props = new Properties();
        try(InputStream input = getClass().getResourceAsStream("/config.properties")){
            if(input != null){
                props.load(input);
            } else {
                System.err.println("config.properties not found!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
                                                                        //Multi-Language Support
        ComboBox<String> languageSelector = new ComboBox<>();
        languageSelector.getItems().addAll("English", "हिन्दी");
        languageSelector.setValue("English");                           //Default value
        languageSelector.setStyle("-fx-font-size: 13px;");

        Map<String, String> enLabels = Map.of(
                "book", "Book Ticket",
                "recharge", "Recharge Your Card",
                "history", "View History",
                "exit", "Exit"
        );
        Map<String, String> hiLabels = Map.of(
                "book", "बुक टिकट",
                "recharge", "अपने कार्ड को रिचार्ज करें",
                "history", "टिकट इतिहास",
                "exit", "बाहर जाएं"
        );
        String stationName = props.getProperty("station.name", "Unknown Station");
        String terminalID = props.getProperty("terminal.id", "Unknown Terminal");
        Label stationLabel = new Label("Station Name: " + stationName);
        Label terminalLabel = new Label("Terminal ID: " + terminalID);

        VBox rightBox = new VBox(5, stationLabel, terminalLabel);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        stationLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #444;");
        terminalLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #777;");
                                                                //Spacer (pushes right box to the right)
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
                                                                //Title bar combining both left and right
        HBox titleBar = new HBox(20, titleLeft, title, spacer, languageSelector, rightBox);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPadding(new Insets(15,20,10,20));
        titleBar.setSpacing(20);
        titleBar.setPrefWidth(200);
        titleBar.getStyleClass().add("title-bar");
        titleBar.setStyle("-fx-background-color: white;");
                                                                        //Clock
        Label clockLabel = new Label();
        clockLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555555; -fx-padding: 10px;");
        updateClock(clockLabel);
        clockLabel.setId("clock-label");
                                                                        //Live update every second
        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1),
                e -> updateClock(clockLabel)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();
                                                                        //Clock Container
        HBox clockContainer = new HBox(clockLabel);
        clockContainer.setAlignment(Pos.CENTER);
        clockContainer.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cccccc; " +
                "-fx-border-width: 1px 0 0 0;");
        clockContainer.setPadding(new Insets(10));
                                                                        //Final top section
        VBox topLayout = new VBox(titleBar, clockContainer);
        topLayout.setStyle("-fx-background-color: white;");

                                                                        //Buttons
        Button bookTicketbtn = new Button("Book Ticket");
        Button rechargeCardbtn = new Button("Recharge Your Card");
        Button historybtn = new Button("View History");
        Button exitbtn = new Button("Exit");
                                                                        //Buttons of same size
        double btnWidth = 220;
        bookTicketbtn.setMinWidth(btnWidth);
        rechargeCardbtn.setMinWidth(btnWidth);
        historybtn.setMinWidth(btnWidth);
        exitbtn.setMinWidth(btnWidth);
                                                                        //Style Buttons
        bookTicketbtn.getStyleClass().add("button");
        rechargeCardbtn.getStyleClass().add("button");
        historybtn.getStyleClass().add("button");
        exitbtn.getStyleClass().addAll("button", "exit");
                                                                        //Buttons Action Placeholder
        bookTicketbtn.setOnAction(e -> System.out.println("Book Ticket Clicked"));
        rechargeCardbtn.setOnAction(e -> System.out.println("Recharge Card Clicked"));
        historybtn.setOnAction(e -> System.out.println("View History"));
        exitbtn.setOnAction(e -> stage.close());

        languageSelector.setOnAction(e -> {
            String lang = languageSelector.getValue();
            Map<String, String> labels = lang.equals("हिन्दी") ? hiLabels : enLabels;

            bookTicketbtn.setText(labels.get("book"));
            rechargeCardbtn.setText(labels.get("recharge"));
            historybtn.setText(labels.get("history"));
            exitbtn.setText(labels.get("exit"));
        });
                                                                        //Create Button Rows
        HBox row1 = new HBox(40, bookTicketbtn, rechargeCardbtn);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(40, historybtn, exitbtn);
        row2.setAlignment(Pos.CENTER);
                                                                        //Combine Button rows
        VBox buttonLayout = new VBox(50, row1, row2);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(40, 0, 40, 0));

                                                                        //Metro Line Illustration
        String[] stations = {"Rajiv Chowk", "Central Secretariat", "Kashmere Gate", "Lajpat Nagar", "Huda City Centre"};
        HBox metroLine = new HBox(15);
        metroLine.setPadding(new Insets(20,20,20,20));
        metroLine.setAlignment(Pos.CENTER);

        Image metroImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/train.png")));
        ImageView metroIcon = new ImageView(metroImg);
        metroIcon.setFitHeight(30);
        metroIcon.setFitWidth(30);

        Timeline metroMove = new Timeline(
                new KeyFrame(Duration.seconds(0), e -> metroIcon.setTranslateX(-300)),
                new KeyFrame(Duration.seconds(5), e -> metroIcon.setTranslateX(300))
        );
        metroMove.setCycleCount(Animation.INDEFINITE);
        metroMove.setAutoReverse(true);
        metroMove.play();

        for(String station : stations){
            VBox stationBox = new VBox(5);
            stationBox.setAlignment(Pos.CENTER);

            Region dot = new Region();
            dot.setStyle("-fx-background-color: " + (station.equals(stationName) ? "#D32F2F" : "#333333")
                    + "; -fx-border-radius: 50%; -fx-background-radius: 50%;");
            dot.setPrefSize(12,12);

            Label stationLbl = new Label(station);
            stationLbl.setStyle("-fx-font-size: 10px; -fx-text-fill: #444444;");

            stationBox.getChildren().addAll(dot, stationLbl);
            metroLine.getChildren().add(stationBox);
        }

        VBox animatedTrainBox = new VBox(metroIcon);
        animatedTrainBox.setAlignment(Pos.CENTER);
        metroLine.getChildren().add(2, animatedTrainBox);

        VBox bottomSection = new VBox(clockContainer, metroLine);
                                                                        //Main Layout
        BorderPane root = new BorderPane();
        root.setCenter(buttonLayout);
        root.setStyle("-fx-background-color: #F1F8FF;");
        root.setTop(topLayout);
        root.setBottom(bottomSection);
                                                                        //Scene and Stage
        Scene scene = new Scene(root, 700, 400);

        URL cssURL = getClass().getResource("/style.css");        // Add leading slash to look in 'resources' root
        if (cssURL != null) {
            scene.getStylesheets().add(cssURL.toExternalForm());
        } else {
            System.err.println("style.css not found!");
        }

        stage.setTitle("DMRC Ticket Machine");
        stage.setScene(scene);
        stage.show();
    }

    private void updateClock(Label clockLabel){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm:ss a");
        clockLabel.setText("  " + now.format(formatter));
    }

    public static void main(String[] args) {
        launch(args);
    }
}


package org.example;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class welcomeScreen {

    private final adminLogin adminLogin = new adminLogin();
    private final bookTicketScreen bookTicketScreen = new bookTicketScreen();

    private final List<String> stationList = Arrays.asList("Dwarka", "Dwarka Mod", "Nawada", "Uttam Nagar East", "Uttam Nagar West",
            "Janakpuri West", "Janakpuri East", "Rajouri Garden", "Karol Bagh", "Rajiv Chowk");

    public Scene createWelcomeScene(Stage stage) {

        // Load logo
        Image logoImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/subway.png")));
        ImageView logoView = new ImageView(logoImg);
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(24);

        Label title = new Label("Welcome to the DMRC Machine");
        title.getStyleClass().add("title");

        HBox titleLeft = new HBox(10, logoView, title);
        titleLeft.setAlignment(Pos.CENTER_LEFT);

        // Load properties
        Properties props = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/config.properties")) {
            if (input != null) {
                props.load(input);
            } else {
                System.err.println("config.properties not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Language support
        ComboBox<String> languageSelector = new ComboBox<>();
        languageSelector.getItems().addAll("English", "हिन्दी");
        languageSelector.setValue("English");

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

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        HBox titleBar = new HBox(20, titleLeft, spacer, languageSelector, rightBox);
        titleBar.setAlignment(Pos.CENTER_LEFT);
        titleBar.setPadding(new Insets(15, 20, 10, 20));
        titleBar.setStyle("-fx-background-color: white;");

        // Clock
        Label clockLabel = new Label();
        clockLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #555;");
        utils.updateClock(clockLabel);

        Timeline clockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> utils.updateClock(clockLabel)));
        clockTimeline.setCycleCount(Animation.INDEFINITE);
        clockTimeline.play();

        HBox clockContainer = new HBox(clockLabel);
        clockContainer.setAlignment(Pos.CENTER);
        clockContainer.setStyle("-fx-background-color: white; -fx-border-color: #cccccc; -fx-border-width: 1px 0 0 0;");
        clockContainer.setPadding(new Insets(10));

        VBox topLayout = new VBox(titleBar, clockContainer);

        // Buttons
        Button bookTicketBtn = new Button("Book Ticket");
        Button rechargeBtn = new Button("Recharge Your Card");
        Button historyBtn = new Button("View History");
        Button exitBtn = new Button("Exit");

        double btnWidth = 220;
        bookTicketBtn.setMinWidth(btnWidth);
        rechargeBtn.setMinWidth(btnWidth);
        historyBtn.setMinWidth(btnWidth);
        exitBtn.setMinWidth(btnWidth);

        bookTicketBtn.getStyleClass().add("button");
        rechargeBtn.getStyleClass().add("button");
        historyBtn.getStyleClass().add("button");
        exitBtn.getStyleClass().addAll("button", "exit");

        HBox row1 = new HBox(40, bookTicketBtn, rechargeBtn);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(40, historyBtn, exitBtn);
        row2.setAlignment(Pos.CENTER);

        VBox buttonLayout = new VBox(50, row1, row2);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(40, 0, 40, 0));

        // Button animations
        buttonLayout.setTranslateY(100);
        buttonLayout.setOpacity(0);

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), buttonLayout);
        slideIn.setFromY(100);
        slideIn.setToY(0);
        slideIn.setDelay(Duration.millis(200));

        FadeTransition fadeButtons = new FadeTransition(Duration.seconds(1), buttonLayout);
        fadeButtons.setFromValue(0);
        fadeButtons.setToValue(1);
        fadeButtons.setDelay(Duration.millis(200));

        ParallelTransition intro = new ParallelTransition(slideIn, fadeButtons);
        intro.play();

        // Fade logo/title
        FadeTransition fadeLogo = new FadeTransition(Duration.seconds(1), logoView);
        fadeLogo.setFromValue(0);
        fadeLogo.setToValue(1);
        fadeLogo.setDelay(Duration.millis(100));

        FadeTransition fadeTitle = new FadeTransition(Duration.seconds(1), title);
        fadeTitle.setFromValue(0);
        fadeTitle.setToValue(1);
        fadeTitle.setDelay(Duration.millis(300));

        new ParallelTransition(fadeLogo, fadeTitle).play();

        // Ticker
        Label tickerLabel = new Label("Blue Line Delay | Card Recharge ₹10 Cashback Today | Happy Metro Week! | ब्लू लाइन में देरी | कार्ड रिचार्ज पर ₹10 कैशबैक आज | हैप्पी मेट्रो सप्ताह!");
        tickerLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #222;");

        HBox tickerContainer = new HBox(tickerLabel);
        tickerContainer.setStyle("-fx-background-color: #FFF9C4; -fx-padding: 5 10 5 10;");
        tickerContainer.setAlignment(Pos.CENTER_LEFT);
        tickerContainer.setPrefHeight(30);

        tickerContainer.widthProperty().addListener((obs, oldVal, newVal) -> {
            double containerWidth = newVal.doubleValue();
            double labelWidth = tickerLabel.getWidth();

            tickerLabel.setTranslateX(containerWidth);

            Rectangle clip = new Rectangle(containerWidth, 30);
            tickerContainer.setClip(clip);

            TranslateTransition tickerScroll = new TranslateTransition(Duration.seconds(15), tickerLabel);
            tickerScroll.setFromX(containerWidth);
            tickerScroll.setToX(-labelWidth);
            tickerScroll.setInterpolator(Interpolator.LINEAR);
            tickerScroll.setCycleCount(Animation.INDEFINITE);
            tickerScroll.play();
        });

        BorderPane root = new BorderPane();
        root.setTop(topLayout);
        root.setCenter(buttonLayout);
        root.setBottom(tickerContainer);
        root.setStyle("-fx-background-color: #F1F8FF;");

        // Language change
        languageSelector.setOnAction(e -> {
            String lang = languageSelector.getValue();
            Map<String, String> labels = lang.equals("हिन्दी") ? hiLabels : enLabels;
            bookTicketBtn.setText(labels.get("book"));
            rechargeBtn.setText(labels.get("recharge"));
            historyBtn.setText(labels.get("history"));
            exitBtn.setText(labels.get("exit"));
        });

        // Button actions
        rechargeBtn.setOnAction(e -> System.out.println("Recharge Clicked"));
        historyBtn.setOnAction(e -> System.out.println("History Clicked"));
        exitBtn.setOnAction(e -> stage.close());

        Scene[] welcomeScene = new Scene[1];
        welcomeScene[0] = new Scene(new StackPane(root), 700, 400);

        // Book ticket action
        bookTicketBtn.setOnAction(e -> {
            Scene bookScene = bookTicketScreen.createBookTicketScene(stage, welcomeScene[0], stationList);
            stage.setScene(bookScene);
        });

        // Admin access hidden button
        Button hiddenAdminBtn = new Button();
        hiddenAdminBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        hiddenAdminBtn.setPrefSize(20, 20);
        hiddenAdminBtn.setOnAction(e -> adminLogin.openAdminLogin());

        HBox hiddenCorner = new HBox(hiddenAdminBtn);
        hiddenCorner.setAlignment(Pos.BOTTOM_RIGHT);
        hiddenCorner.setPadding(new Insets(0, 10, 10, 0));
        hiddenCorner.setMouseTransparent(true);

        StackPane stack = new StackPane(root, hiddenCorner);
        welcomeScene[0].setRoot(stack);

        // Keyboard shortcut
        welcomeScene[0].addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.isControlDown() && event.isAltDown() && event.getCode() == KeyCode.A) {
                adminLogin.openAdminLogin();
            }
        });

        URL cssURL = getClass().getResource("/style.css");
        if (cssURL != null) {
            welcomeScene[0].getStylesheets().add(cssURL.toExternalForm());
        }

        return welcomeScene[0];
    }
}

package org.example;

import javafx.animation.*;
import javafx.application.Application;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import javafx.scene.*;
import javafx.geometry.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.net.URL;

public class Main extends Application{

    @Override
    public void start(Stage stage){
                                                                        //Load logo image
        Image logoImg = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/subway.png")));
        ImageView logoView = new ImageView(logoImg);
        logoView.setPreserveRatio(true);
        logoView.setFitHeight(24);
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
                                                                        //Quoting or Tip of the day
        String[] metroTips = {
                "Always stand behind the yellow line.",
                "Keep your metro card ready while exiting.",
                "Let passengers exit before boarding.",
                "Travel smart, travel safe!",
                "Follow platform announcements carefully.",
                "Avoid talking loudly or blocking doors.",
                "Queue up, don't rush during boarding.",
                "Carry a valid token/card during travel.",
                "Help senior citizens and people with disabilities.",
        };
        Label tipLabel = new Label("Tip of the Day: " + metroTips[0]);
        tipLabel.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #444; -fx-padding: 10 0 10 0;");
        tipLabel.setWrapText(true);
        tipLabel.setMaxWidth(600);
        tipLabel.setAlignment(Pos.CENTER);

        HBox tipBox = new HBox(tipLabel);
        tipBox.setAlignment(Pos.CENTER);
        tipBox.setPadding(new Insets(8));
        tipBox.setStyle("-fx-background-color: #E3F2FD; -fx-border-color: #1565C0; -fx-border-width: 0 0 2px 0;");

        Timeline tipTimeline = new Timeline(new KeyFrame(Duration.seconds(5), e -> {
            int idx = new Random().nextInt(metroTips.length);
            FadeTransition fadeOut = new FadeTransition(Duration.millis(500), tipLabel);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(ev -> {
                tipLabel.setText(metroTips[idx]);
                FadeTransition fadeIn = new FadeTransition(Duration.millis(500), tipLabel);
                fadeIn.setFromValue(0);
                fadeIn.setToValue(1);
                fadeIn.play();
            });
            fadeOut.play();
        }));
        tipTimeline.setCycleCount(Animation.INDEFINITE);
        tipTimeline.play();
        VBox bottomSection = new VBox(tipBox);
        bottomSection.setSpacing(5);
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
                                                                                    //Book Ticket
        List<String> stationList = Arrays.asList("Dwarka", "Dwarka Mod", "Nawada", "Uttam Nagar East", "Uttam Nagar West",
                "Janakpuri West","Janakpuri East", "Rajouri Garden", "Karol Bagh", "Rajiv Chowk");
                                                                        //Buttons Action Placeholder
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

                                                        // Animation for button layout: slide-in from below + fade-in
        buttonLayout.setTranslateY(100);  // Start 100px below
        buttonLayout.setOpacity(0);       // Start invisible

        TranslateTransition slideIn = new TranslateTransition(Duration.seconds(1), buttonLayout);
        slideIn.setFromY(100);
        slideIn.setToY(0);
        slideIn.setDelay(Duration.millis(200));
        slideIn.setInterpolator(Interpolator.EASE_BOTH);

        FadeTransition fadeButtons = new FadeTransition(Duration.seconds(1), buttonLayout);
        fadeButtons.setFromValue(0);
        fadeButtons.setToValue(1);
        fadeButtons.setDelay(Duration.millis(200));

        ParallelTransition intro = new ParallelTransition(slideIn, fadeButtons);
        intro.play();
                                                                     //Animation title and logo fade-in
        FadeTransition fadeLogo = new FadeTransition(Duration.seconds(1), logoView);
        fadeLogo.setFromValue(0);
        fadeLogo.setToValue(1);
        fadeLogo.setDelay(Duration.millis(100));

        FadeTransition fadeTitle = new FadeTransition(Duration.seconds(1), title);
        fadeTitle.setFromValue(0);
        fadeTitle.setToValue(1);
        fadeTitle.setDelay(Duration.millis(300));

        ParallelTransition fadeIntro = new ParallelTransition(fadeLogo, fadeTitle);
        fadeIntro.play();
                                                                        //Ticker (Anouncement bar)
        Label tickerLabel = new Label("Blue Line Delay | Card Recharge ₹10 Cashback Today | Happy Metro Week!" +
                " | ब्लू लाइन में देरी | कार्ड रिचार्ज पर ₹10 कैशबैक आज | हैप्पी मेट्रो सप्ताह!");
        tickerLabel.setStyle("-fx-font-size: 13px; -fx-text-fill: #222;");
        tickerLabel.setMinWidth(Region.USE_PREF_SIZE);
        tickerLabel.setId("ticker-label");

        HBox tickerContainer = new HBox(tickerLabel);
        tickerContainer.setStyle("-fx-background-color: #FFF9C4; -fx-padding: 5 10 5 10;");
        tickerContainer.setAlignment(Pos.CENTER_LEFT);
        tickerContainer.setPrefHeight(30);
        tickerContainer.setId("ticker-container");
                                                                            //Run animation AFTER layout pass (scene is ready)
        tickerContainer.widthProperty().addListener((obs,
                                                     oldVal, newVal) -> {
            double containerWidth = newVal.doubleValue();
            double labelWidth = tickerLabel.getWidth();
                                                                            // Reset position for repeated animation
            tickerLabel.setTranslateX(containerWidth);
                                                                            // Set clipping to only show within ticker box
            Rectangle clip = new Rectangle(containerWidth, 30);
            clip.heightProperty().bind(tickerContainer.heightProperty());
            clip.widthProperty().bind(tickerContainer.widthProperty());
            tickerContainer.setClip(clip);
                                                                            // Remove old animations if any
            tickerLabel.getTransforms().clear();
                                                                            //Animate text leftwards
            TranslateTransition tickerScroll = new TranslateTransition(Duration.seconds(15), tickerLabel);
            tickerScroll.setFromX(containerWidth);
            tickerScroll.setToX(-labelWidth);
            tickerScroll.setInterpolator(Interpolator.LINEAR);
            tickerScroll.setCycleCount(Animation.INDEFINITE);
            tickerScroll.play();
        });
                                                                                //Main Layout
        BorderPane root = new BorderPane();
        root.setCenter(buttonLayout);
        root.setStyle("-fx-background-color: #F1F8FF;");
        root.setTop(topLayout);
        root.setBottom(tipBox);
                                                                        //Admin Part only
        Button hiddenAdminBtn = new Button();
        hiddenAdminBtn.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
        hiddenAdminBtn.setPrefSize(20, 20);
        hiddenAdminBtn.setOnAction(e -> openAdminLogin());

        HBox hiddenCorner = new HBox(hiddenAdminBtn);
        hiddenCorner.setAlignment(Pos.BOTTOM_RIGHT);
        hiddenCorner.setPadding(new Insets(0,10,10,0));
        hiddenCorner.setMouseTransparent(true);
                                                                        //Scene and Stage
        StackPane stack = new StackPane(root, hiddenCorner);
        Scene welcomeScene = new Scene(stack, 700, 400);

        welcomeScene.addEventFilter(KeyEvent.KEY_PRESSED, event ->{
            if(event.isControlDown() && event.isAltDown() && event.getCode() == KeyCode.A){
                openAdminLogin();
            }
        });
        //-------------------------------------------------------------------------Book ticket button
        bookTicketbtn.setOnAction(e -> {
            // -------- Header re-use ----------
            Label bookTitle = new Label("DMRC Ticket Machine");
            bookTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            Label bookClock = new Label();
            bookClock.setStyle("-fx-font-size: 14px;");
            updateClock(bookClock);

            Timeline bookClockTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e2 ->
                    updateClock(bookClock)));
            bookClockTimeline.setCycleCount(Animation.INDEFINITE);
            bookClockTimeline.play();

            HBox bookTitleBar = new HBox(20, bookTitle, bookClock);
            bookTitleBar.setPadding(new Insets(15,20,10,20));
            bookTitleBar.setAlignment(Pos.CENTER_LEFT);
            bookTitleBar.setStyle("-fx-background-color: white;");

            VBox header = new VBox(bookTitleBar);

            // -------- Left (From Station) ----------
            Label fromLabel = new Label("From Station:");
            ComboBox<String> fromComboBox = new ComboBox<>();
            fromComboBox.getItems().addAll(stationList);
            fromComboBox.setPromptText("Select From");

            VBox fromBox = new VBox(10, fromLabel, fromComboBox);
            fromBox.setAlignment(Pos.CENTER);
            fromBox.setPadding(new Insets(10));
            fromBox.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");
            fromBox.setPrefWidth(250);

            // -------- Right (To Station) ----------
            Label toLabel = new Label("To Station:");
            ComboBox<String> toComboBox = new ComboBox<>();
            toComboBox.getItems().addAll(stationList);
            toComboBox.setPromptText("Select To");

            VBox toBox = new VBox(10, toLabel, toComboBox);
            toBox.setAlignment(Pos.CENTER);
            toBox.setPadding(new Insets(10));
            toBox.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: #CCCCCC; -fx-border-radius: 5; -fx-background-radius: 5;");
            toBox.setPrefWidth(250);

            // -------- Center layout with From & To ----------
            HBox centerBox = new HBox(30, fromBox, toBox);
            centerBox.setAlignment(Pos.CENTER);
            centerBox.setPadding(new Insets(20));

            // -------- Distance & Fare --------
            Label distanceLabel = new Label("Distance: 0 km");
            Label fareLabel = new Label("Fare: ₹0");
            fareLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: green;");

            HBox infoBox = new HBox(40, distanceLabel, fareLabel);
            infoBox.setAlignment(Pos.CENTER);
            infoBox.setPadding(new Insets(10));

            // -------- Buttons --------
            Button confirmBtn = new Button("Confirm");
            Button backBtn = new Button("Back");

            HBox buttonBox = new HBox(30, confirmBtn, backBtn);
            buttonBox.setAlignment(Pos.CENTER);
            buttonBox.setPadding(new Insets(15));

            // -------- Update fare & distance logic --------
            fromComboBox.setOnAction(ev -> updateFareAndDistance(fromComboBox, toComboBox, fareLabel, distanceLabel));
            toComboBox.setOnAction(ev -> updateFareAndDistance(fromComboBox, toComboBox, fareLabel, distanceLabel));

            // -------- Back button action --------
            backBtn.setOnAction(ev -> stage.setScene(welcomeScene));

            // -------- Main vertical layout --------
            VBox mainLayout = new VBox(20, header, centerBox, infoBox, buttonBox);
            mainLayout.setAlignment(Pos.TOP_CENTER);
            mainLayout.setPadding(new Insets(10));

            Scene bookTicketScene = new Scene(mainLayout, 700, 500);
            URL cssURL = getClass().getResource("/style.css");
            if (cssURL != null) {
                bookTicketScene.getStylesheets().add(cssURL.toExternalForm());
            }

            stage.setScene(bookTicketScene);
        });

        URL cssURL = getClass().getResource("/style.css");        // Add leading slash to look in 'resources' root
        if (cssURL != null) {
            welcomeScene.getStylesheets().add(cssURL.toExternalForm());
        } else {
            System.err.println("style.css not found!");
        }
        stage.setTitle("DMRC Ticket Machine");
        stage.setScene(welcomeScene);
        stage.show();
    }
    private void updateClock(Label clockLabel){
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm:ss a");
        clockLabel.setText("  " + now.format(formatter));
    }
    private void openAdminLogin(){
        System.out.println("Adin Panel Triggered");
        Stage loginStage = new Stage();
        loginStage.setTitle("Admin Login");

        Label userLabel = new Label("Username: ");
        TextField userNamefield = new TextField();
        userNamefield.setPromptText("Enter Admin Username: ");

        Label passLabel = new Label("Password: ");
        PasswordField passwordfield = new PasswordField();
        passwordfield.setPromptText("Enter Password: ");

        Button loginBtn = new Button("Login");
        Label statusLabel = new Label();

        loginBtn.setOnAction(e -> {
            String user = userNamefield.getText();
            String pass = passwordfield.getText();

            if(user.equals("admin") && pass.equals("dmrc123")){
                statusLabel.setText("Access Granted...");
                loginStage.close();
                System.out.println("Opening Admin Panel....");
            } else{
                statusLabel.setText("Invalid Credentials...");
            }
        });
        VBox loginLayout = new VBox(10, userLabel, userNamefield, passLabel, passwordfield, loginBtn, statusLabel);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(loginLayout, 300, 250);
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL); // Tie it to main window
        loginStage.show();
    }
    private void updateFareAndDistance(ComboBox<String> from, ComboBox<String> to, Label fareLabel, Label distanceLabel) {
        String fromStation = from.getValue();
        String toStation = to.getValue();

        if (fromStation != null && toStation != null && !fromStation.equals(toStation)) {
            int fromIndex = from.getItems().indexOf(fromStation);
            int toIndex = to.getItems().indexOf(toStation);
            int distance = Math.abs(fromIndex - toIndex) * 2; // Example: 2 km per station gap
            int fare = Math.abs(fromIndex - toIndex) * 10 + 5;

            fareLabel.setText("Fare: ₹" + fare);
            distanceLabel.setText("Distance: " + distance + " km");
        } else {
            fareLabel.setText("Fare: ₹0");
            distanceLabel.setText("Distance: 0 km");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


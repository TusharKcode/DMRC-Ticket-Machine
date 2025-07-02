package org.example;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class adminLogin {

    public void openAdminLogin() {
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

            if (user.equals("admin") && pass.equals("dmrc123")) {
                statusLabel.setText("Access Granted...");
                loginStage.close();
                System.out.println("Opening Admin Panel....");
                openAdminPanel();
            } else {
                statusLabel.setText("Invalid Credentials...");
            }
        });

        VBox loginLayout = new VBox(10, userLabel, userNamefield, passLabel, passwordfield, loginBtn, statusLabel);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setAlignment(Pos.CENTER);

        Scene loginScene = new Scene(loginLayout, 300, 250);
        loginStage.setScene(loginScene);
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();
    }
    private VBox createPanelLayout(Label ticketCountLabel, Label balanceLabel) {
        Label welcomeLabel = new Label("Welcome, Admin!");
        Label statusLabel = new Label("Machine Status: Working");

        ticketCountLabel.setText("Tickets Issued: " + appData.ticketIssued);
        balanceLabel.setText("Total Balance: ₹" + appData.totalBalance);

        welcomeLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        statusLabel.setStyle("-fx-font-size: 16px;");
        ticketCountLabel.setStyle("-fx-font-size: 16px;");
        balanceLabel.setStyle("-fx-font-size: 16px;");

        Button resetMachineBtn = new Button("Reset Machine");
        Button logsBtn = new Button("View Logs");
        Button logoutBtn = new Button("Logout");

        String buttonStyle = "-fx-background-color: #3366cc; -fx-text-fill: white; -fx-font-size: 14px; " +
                "-fx-background-radius: 8;";
        resetMachineBtn.setStyle(buttonStyle);
        logsBtn.setStyle(buttonStyle);
        logoutBtn.setStyle(buttonStyle);

        resetMachineBtn.setOnMouseEntered(e -> resetMachineBtn.setStyle(buttonStyle + "-fx-opacity: 0.85;"));
        resetMachineBtn.setOnMouseExited(e -> resetMachineBtn.setStyle(buttonStyle));

        logsBtn.setOnMouseEntered(e -> logsBtn.setStyle(buttonStyle + "-fx-opacity: 0.85;"));
        logsBtn.setOnMouseExited(e -> logsBtn.setStyle(buttonStyle));

        logoutBtn.setOnMouseEntered(e -> logoutBtn.setStyle(buttonStyle + "-fx-opacity: 0.85;"));
        logoutBtn.setOnMouseExited(e -> logoutBtn.setStyle(buttonStyle));

        resetMachineBtn.setOnAction(e -> {
            appData.ticketIssued = 0;
            appData.totalBalance = 0;
            appData.logs.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Machine Reset");
            alert.setHeaderText(null);
            alert.setContentText("Machine data has been reset");
            alert.showAndWait();

            System.out.println("Machine data reset: tickets, balance and logs cleared");
        });

        logsBtn.setOnAction(e -> showLogs());
        logoutBtn.setOnAction(e -> logoutBtn.getScene().getWindow().hide());

        VBox panelLayout = new VBox(15, welcomeLabel, statusLabel, ticketCountLabel, balanceLabel,
                resetMachineBtn, logsBtn, logoutBtn);
        panelLayout.setPadding(new Insets(20));
        panelLayout.setAlignment(Pos.CENTER);
        panelLayout.setStyle("-fx-background-color: white; -fx-background-radius: 12; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.2), 10, 0, 0, 4);");

        VBox rootLayout = new VBox(panelLayout);
        rootLayout.setAlignment(Pos.CENTER);
        rootLayout.setPadding(new Insets(30));
        rootLayout.setStyle("fx-background-color: linear-gradient(to bottom right, #f0f4ff, #dbe5ff);");

        return rootLayout;
    }
    public void openAdminPanel() {
        Stage adminStage = new Stage();
        adminStage.setTitle("DMRC Admin Panel");

        Label ticketCountLabel = new Label();
        Label balancelabel = new Label();

        VBox panelLayout = createPanelLayout(ticketCountLabel, balancelabel);

        Scene panelScene = new Scene(panelLayout, 400, 400);
        adminStage.setScene(panelScene);
        adminStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            ticketCountLabel.setText("Ticket Issued: " + appData.ticketIssued);
            balancelabel.setText("Total balance: ₹" + appData.totalBalance);
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
                                                            //Stops Timeline when Admin Panel is close
        adminStage.setOnCloseRequest(e -> timeline.stop());
    }
    private void showLogs(){
        Stage logStage = new Stage();
        logStage.setTitle("Transaction Logs");

        ListView<String> logListView = new ListView<>();
        logListView.getItems().addAll(appData.logs);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> logStage.close());

        VBox layout = new VBox(10, logListView, closeBtn);
        layout.setPadding(new Insets(15));
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout, 400, 400);
        logStage.setScene(scene);
        logStage.show();
    }
}

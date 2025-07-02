package org.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private VBox createPanelLayout() {
        Label welcomeLabel = new Label("Welcome, Admin!");
        Label statusLabel = new Label("Machine Status: Working");
        Label ticketCountLabel = new Label("Tickets Issued: 0" + appData.ticketIssued);
        Label balanceLabel = new Label("Total Balance: ₹0" + appData.totalBalance);

        Button resetMachineBtn = new Button("Reset Machine");
        Button logsBtn = new Button("View Logs");
        Button logoutBtn = new Button("Logout");

        resetMachineBtn.setOnAction(e -> System.out.println("Machine reset action triggered"));
        logsBtn.setOnAction(e -> System.out.println("Logs button clicked!"));
        logoutBtn.setOnAction(e -> logoutBtn.getScene().getWindow().hide());

        VBox panelLayout = new VBox(15, welcomeLabel, statusLabel, ticketCountLabel, balanceLabel,
                resetMachineBtn, logsBtn, logoutBtn);
        panelLayout.setPadding(new Insets(20));
        panelLayout.setAlignment(Pos.CENTER);

        return panelLayout;
    }
    public void openAdminPanel() {
        Stage adminStage = new Stage();
        adminStage.setTitle("DMRC Admin Panel");

        VBox panelLayout = createPanelLayout();

        Scene panelScene = new Scene(panelLayout, 400, 400);
        adminStage.setScene(panelScene);
        adminStage.show();
    }

}

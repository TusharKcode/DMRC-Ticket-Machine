package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application{

    @Override
    public void start(Stage stage){
                                                                        //Title Label
        Label title = new Label("Welcome to the DMRC Machine");
        title.getStyleClass().add("title");

                                                                        //Title container with white background color
        HBox titleBar = new HBox(title);
        titleBar.setAlignment(Pos.CENTER);
        titleBar.setPadding(new Insets(20));
        titleBar.setStyle("-fx-background-color: yellow;");

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

                                                                        //Button Styling
        String btnStyle = "-fx-background-color: #1565C0; -fx-text-fill: white; -fx-font-size: 16px; " +
                "-fx-padding: 10 15 10 15;";
        bookTicketbtn.setStyle(btnStyle);
        rechargeCardbtn.setStyle(btnStyle);
        exitbtn.setStyle("-fx-background-color: #B71C1C; -fx-text-fill: white; " +
                "-fx-font-size: 16px; -fx-padding: 10 15 10 15;");

                                                                        //Create Rows
        HBox row1 = new HBox(40, bookTicketbtn, rechargeCardbtn);
        row1.setAlignment(Pos.CENTER);
        HBox row2 = new HBox(40, historybtn, exitbtn);
        row2.setAlignment(Pos.CENTER);

                                                                        //Combine rows
        VBox buttonLayout = new VBox(50, row1, row2);
        buttonLayout.setAlignment(Pos.CENTER);
        buttonLayout.setPadding(new Insets(40, 0, 40, 0));

                                                                        //Main Layout
        BorderPane root = new BorderPane();
        root.setTop(titleBar);
        root.setCenter(buttonLayout);
        root.setStyle("-fx-background-color: #F1F8FF;");

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

    public static void main(String[] args) {
        launch(args);
    }
}


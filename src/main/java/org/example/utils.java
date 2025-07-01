package org.example;

import javafx.scene.control.Label;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class utils {
    public static void updateClock(Label clockLabel) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy | hh:mm:ss a");
        clockLabel.setText("  " + now.format(formatter));
    }
}

package kz.append.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class LoadingController {

    @FXML
    private Label greetingLabel;

    @FXML
    private ProgressIndicator loadingIndicator;

    public void initialize() {

        loadingIndicator.setVisible(true);

        Platform.runLater(() -> {
            try {

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            loadingIndicator.setVisible(false);
            greetingLabel.setText("Привет!");
        });
    }
}

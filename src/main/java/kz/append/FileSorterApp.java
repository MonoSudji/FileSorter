package kz.append;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FileSorterApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/layout.fxml"));
        Parent loadingRoot = FXMLLoader.load(getClass().getResource("/loading.fxml"));

        Scene scene = new Scene(root, 600, 400);
        scene.getStylesheets().add("styles.css");
        scene.getStylesheets().add("hello.css");

        primaryStage.setTitle("File Sorter");
        Stage loadingStage = new Stage();
        loadingStage.initModality(Modality.APPLICATION_MODAL);
        loadingStage.setScene(new Scene(loadingRoot));
        loadingStage.show();

        Platform.runLater(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            loadingStage.close();
            primaryStage.setScene(scene);
            primaryStage.show();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}

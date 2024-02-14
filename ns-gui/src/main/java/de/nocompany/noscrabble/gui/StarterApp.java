package de.nocompany.noscrabble.gui;

import de.nocompany.noscrabble.Starter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StarterApp extends Application {
    BorderPane root = new BorderPane();
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("noScrabble");
        showStartseiteHintergrund();
        primaryStage.setScene(new Scene(root, 1024, 1024));
        showSpielerEingabe();
    }

    private void showStartseiteHintergrund() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/startseiteHintergrund.fxml"));
            Parent hintergrundContent = fxmlLoader.load();
            root.setCenter(hintergrundContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSpielerEingabe() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/spielerEingabe.fxml"));
            Parent eingabeContent = fxmlLoader.load();
            //Stage stage = new Stage();
            root.setCenter(eingabeContent);
            //stage.setTitle("noScrabble");
            //stage.setScene(new Scene(root, 1024, 1024));
            //stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

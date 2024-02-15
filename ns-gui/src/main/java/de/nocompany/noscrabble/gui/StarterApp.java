package de.nocompany.noscrabble.gui;

import de.nocompany.noscrabble.Starter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class StarterApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Starter s = new Starter();
        Label l = new Label("Hallo noScabble! " + s.getVersion());
        HBox root = new HBox();
        root.getChildren().add(l);
        Scene scene = new Scene(root, 300, 400);
        //primaryStage.setScene(scene);
        //sprimaryStage.show();
        showDragDropDemo();
    }

    private void showDragDropDemo() {
        try {
            // Erstelle das Fenster für die Drag-and-Drop-Demo
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Spielfeld.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Drag-and-Drop-Demo");
            stage.setScene(new Scene(root, 1300, 1024)); // Größe des Fensters entsprechend anpassen
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

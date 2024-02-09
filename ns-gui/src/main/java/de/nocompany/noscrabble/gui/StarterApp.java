package de.nocompany.noscrabble.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class StarterApp extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label l = new Label("Hallo noScabble!");
        HBox root = new HBox();
        root.getChildren().add(l);
        Scene scene = new Scene(root, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

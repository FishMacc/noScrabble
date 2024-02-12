package de.nocompany.noscrabble.gui;

import de.nocompany.noscrabble.Starter;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class StarterApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/hauptfenster.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();

        Starter s = new Starter();
        Scene scene = new Scene(root, 900, 900);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

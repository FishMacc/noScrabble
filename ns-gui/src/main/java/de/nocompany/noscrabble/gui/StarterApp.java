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
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Starter s = new Starter();
        Label l = new Label("Hallo noScrabble! " + s.getVersion());
        HBox root = new HBox();
        root.getChildren().add(l);
        //Scene scene = new Scene(root, 300, 400);
        //primaryStage.setScene(scene);
        //primaryStage.show();
        showStartseiteHintergrund();
        showSpielerEingabe();
    }

    private void showStartseiteHintergrund() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/startseiteHintergrund.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("noScrabble");
            stage.setScene(new Scene(root, 1024, 1024));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSpielerEingabe() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/spielerEingabe.fxml"));
            Parent eingabeContent = fxmlLoader.load();
            //Stage stage = new Stage();
            if (root instanceof HBox) {
                ((HBox) root).setCenter(eingabeContent);
            }
            //stage.setTitle("noScrabble");
            //stage.setScene(new Scene(root, 1024, 1024));
            //stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

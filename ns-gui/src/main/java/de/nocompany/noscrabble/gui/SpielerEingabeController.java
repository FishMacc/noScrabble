package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpielerEingabeController {

    @FXML
    private Button bSpielStarten;
    @FXML
    private Label lVorhandeneSpieler;
    @FXML
    private Label lSpieler1;
    @FXML
    private Label lSpieler2;
    @FXML
    private Label lSpieler3;
    @FXML
    private Label lSpieler4;
    @FXML
    private TextField tfSpielername;
    @FXML
    private Button bSpielerHinzufuegen;


    @FXML
    private List<String> spielerListe = new ArrayList<>();

    @FXML
    private int spielerZaehler = 0;


    @FXML
    void initialize() {

        bSpielerHinzufuegen.setOnAction(e -> doAddSpieler());
        bSpielStarten.setOnAction(e -> doSpielStarten());
    }


    public void doAddSpieler() {
        switch (spielerZaehler) {
            case 0:
                lSpieler1.setText(tfSpielername.getText());
                break;
            case 1:
                lSpieler2.setText(tfSpielername.getText());
                break;
            case 2:
                lSpieler3.setText(tfSpielername.getText());
                break;
            case 3:
                lSpieler4.setText(tfSpielername.getText());
                break;
            default:
                System.out.println("Bitte geben Sie einen gültigen Spielernamen an");
                break;
        }
        spielerZaehler++;

        tfSpielername.setText("");
        tfSpielername.requestFocus();

        // Verhindere das Hinzufügen von mehr als 4 Spielern
        if (spielerZaehler >= 4) {
            bSpielerHinzufuegen.setDisable(true);
        }
    }

    public void doSpielStarten() {
        if (!lSpieler1.getText().isEmpty()) {
            spielerListe.add(lSpieler1.getText());
        }
        if (!lSpieler2.getText().isEmpty()) {
            spielerListe.add(lSpieler2.getText());
        }
        if (!lSpieler3.getText().isEmpty()) {
            spielerListe.add(lSpieler3.getText());
        }
        if (!lSpieler4.getText().isEmpty()) {
            spielerListe.add(lSpieler4.getText());
        }
        //todo aus der spielfeldController ein neues spiel erstellen
        //Spiel = new neuesSpiel(spielerListe);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Dummy.fxml"));
            Parent dummyContent = fxmlLoader.load();
            DummyController controller = fxmlLoader.getController();
            controller.getListe(spielerListe);
            Stage stage = new Stage();
            stage.setScene(new Scene(dummyContent));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

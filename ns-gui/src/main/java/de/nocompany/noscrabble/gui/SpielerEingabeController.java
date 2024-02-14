package de.nocompany.noscrabble.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SpielerEingabeController {

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
    private List<String> spielerListe = new ArrayList<>();


    @FXML
    void initialize() {

        spielerListe.add(lSpieler1.getText());
        spielerListe.add(lSpieler2.getText());
        spielerListe.add(lSpieler3.getText());
        spielerListe.add(lSpieler4.getText());
        Button bSpielerHinzufuegen = new Button();
        bSpielerHinzufuegen.setOnAction(e -> doAddSpieler());

    }


    public void doAddSpieler() {
        for (String spielerName : spielerListe) {
            if (spielerName.isEmpty()) {
                spielerName = tfSpielername.getText();
                break;
            }
        }


    }

    public void doSpielStarten(ActionEvent actionEvent) {
    }
}

package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class SpielerEingabeController {

    public Button bSpielStarten;
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

    private int spielerZaehler = 0;

    @FXML
    void initialize() {

        bSpielerHinzufuegen.setOnAction(e -> doAddSpieler());
        bSpielStarten.setOnAction(e ->doSpielStarten());
    }


    public void doAddSpieler() {
        if (spielerZaehler == 0) {
            lSpieler1.setText(tfSpielername.getText());
        } else if (spielerZaehler == 1) {
            lSpieler2.setText(tfSpielername.getText());
        } else if (spielerZaehler == 2) {
            lSpieler3.setText(tfSpielername.getText());
        } else if (spielerZaehler == 3) {
            lSpieler4.setText(tfSpielername.getText());
        }
        spielerZaehler++;

        tfSpielername.setText("");
        tfSpielername.requestFocus();

        // Verhindere das Hinzufügen von mehr als 4 Spielern
        if (spielerZaehler >= 4) {
            bSpielerHinzufuegen.setDisable(true);
        }
    }

    public void doSpielStarten () {
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
        //todo aus der serviceimpl ein neues spiel erstellen
        //Spiel = new neuesSpiel(spielerListe);
        System.out.println("Starte Programm");
    }

    }

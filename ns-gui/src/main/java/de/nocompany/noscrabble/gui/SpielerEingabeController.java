package de.nocompany.noscrabble.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SpielerEingabeController {
    @FXML
    public Label lStartbild;
    public Label lVorhandeneSpieler;
    public Label lSpieler1;
    public Label lSpieler2;
    public Label lSpieler3;
    public Label lSpieler4;
    public TextField tfSpielername;
    public Button bSpielerHinzufuegen;
    public Button bSpielStarten;

    /*@FXML
    private Node getImage(){
        Label lStartbild = new Label("Mit Startbild");
        URL startBild = getClass().getClassLoader().getResource("images/Startbild.jpg");

        lStartbild.setGraphic(new ImageView(startBild));
        lStartbild.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        lStartbild.setTooltip(new Tooltip("Startbild"));
        }
        
     */


    public void doAddSpieler(ActionEvent actionEvent) {
    }

    public void doSpielStarten(ActionEvent actionEvent) {
    }
}

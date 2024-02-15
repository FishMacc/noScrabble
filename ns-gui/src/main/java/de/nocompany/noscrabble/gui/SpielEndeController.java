package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SpielEndeController {
    @FXML
    private Label lGlueckwunsch1;
    @FXML
    private Label lSpielerGewinner;
    @FXML
    private Label lGlueckwunsch2;


    public void setlSpielerGewinner(Label lSpielerGewinner) {
        this.lSpielerGewinner = lSpielerGewinner;
    }
}

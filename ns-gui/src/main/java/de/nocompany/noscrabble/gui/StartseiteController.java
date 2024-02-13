package de.nocompany.noscrabble.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.URL;

public class StartseiteController {
    @FXML
    public Label lStartbild;

    @FXML
    private Node getImage(){
        Label lStartbild = new Label("Mit Startbild");
        URL startBild = getClass().getClassLoader().getResource("images/Startbild.jpg");

        lStartbild.setGraphic(new ImageView(startBild));
        lStartbild.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        lStartbild.setTooltip(new Tooltip("Startbild"));
        }



    public void doAddSpieler(ActionEvent actionEvent) {
    }

    public void doSpielStarten(ActionEvent actionEvent) {
    }
}

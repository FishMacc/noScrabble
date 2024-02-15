package de.nocompany.noscrabble.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SpielfeldController {

    private List<Pane> draggableObjects = new ArrayList<>(); // Liste der draggable Objekte
    @FXML
    private Button auswertenButton;
    @FXML
    private Button neuButton;
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Pane stein1;
    @FXML
    private Pane stein2;
    @FXML
    private Pane stein3;
    @FXML
    private Pane stein4;
    @FXML
    private Pane stein5;
    @FXML
    private Pane stein6;
    @FXML
    private Pane stein7;

    @FXML
    private void initialize() {
        setupDraggableObjects();
    }

    private int konvertiereZuSpielKoordinate(double pixelKoordinate) {
        int größeMitLinien = 50 + 1;
        double gerundeteKoordinate = Math.round(pixelKoordinate / größeMitLinien);
        int spielKoordinate = (int) gerundeteKoordinate - 1;
        return spielKoordinate;
    }

    private void druckeSpielKoordinaten(Pane draggableObject) {
        double xPosition = draggableObject.getLayoutX();
        double yPosition = draggableObject.getLayoutY();
        int spielX = konvertiereZuSpielKoordinate(xPosition);
        int spielY = konvertiereZuSpielKoordinate(yPosition);
        System.out.println("Position von " + draggableObject + "- X: " + spielX + ", Y: " + spielY);
    }

    private boolean istZelleBesetzt(double x, double y, Pane currentStone) {
        int größeMitLinien = 50 + 1;
        double newX = Math.round(x / größeMitLinien) * größeMitLinien;
        double newY = Math.round(y / größeMitLinien) * größeMitLinien;

        for (Pane draggableObject : draggableObjects) {
            if (draggableObject != currentStone) {
                double objX = draggableObject.getLayoutX();
                double objY = draggableObject.getLayoutY();
                double otherX = Math.round(objX / größeMitLinien) * größeMitLinien;
                double otherY = Math.round(objY / größeMitLinien) * größeMitLinien;

                if (newX == otherX && newY == otherY) {
                    return true;
                }
            }
        }
        return false;
    }

    private void setzeSteinPositionZurück(Pane draggableObject, double originalX, double originalY) {
        draggableObject.setLayoutX(originalX);
        draggableObject.setLayoutY(originalY);
    }

    private void setupDraggableObjects() {
        draggableObjects.add(stein1);
        draggableObjects.add(stein2);
        draggableObjects.add(stein3);
        draggableObjects.add(stein4);
        draggableObjects.add(stein5);
        draggableObjects.add(stein6);
        draggableObjects.add(stein7);

        for (Pane draggableObject : draggableObjects) {
            setupDraggableObject(draggableObject);
        }
    }

    private void setupDraggableObject(Pane draggableObject) {
        draggableObject.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - draggableObject.getLayoutX();
            yOffset = event.getSceneY() - draggableObject.getLayoutY();
        });

        draggableObject.setOnMouseDragged(event -> {
            double newX = event.getSceneX() - xOffset;
            double newY = event.getSceneY() - yOffset;

            if (istInnerhalbGrenzen(newX, newY) && !istZelleBesetzt(newX, newY, draggableObject)) {
                draggableObject.setLayoutX(newX);
                draggableObject.setLayoutY(newY);
            }
        });

        draggableObject.setOnMouseClicked(mouseClickEvent -> {
            if (mouseClickEvent.getButton() == MouseButton.SECONDARY) {
                if (draggableObject.getChildren().size() >= 2 &&
                        draggableObject.getChildren().get(1) instanceof Label &&
                        draggableObject.getChildren().get(2) instanceof Label) {

                    Label letterLabel = (Label) draggableObject.getChildren().get(1);
                    Label numberLabel = (Label) draggableObject.getChildren().get(2);

                    if (letterLabel.getTextFill() != Color.BLUE) {
                        letterLabel.setTextFill(Color.BLUE);
                        numberLabel.setTextFill(Color.BLUE);
                    } else {
                        letterLabel.setTextFill(Color.BLACK);
                        numberLabel.setTextFill(Color.BLACK);
                    }
                }
            }
        });

        draggableObject.setOnMouseReleased(event -> {
            int größeMitLinien = 50 + 1;
            double newX = Math.round((draggableObject.getLayoutX()) / größeMitLinien) * größeMitLinien;
            double newY = Math.round((draggableObject.getLayoutY()) / größeMitLinien) * größeMitLinien;

            if (istInnerhalbGrenzen(newX, newY)) {
                if (!istZelleBesetzt(newX, newY, draggableObject)) {
                    draggableObject.setLayoutX(newX + 2);
                    draggableObject.setLayoutY(newY - 18);
                    druckeSpielKoordinaten(draggableObject);
                } else {
                    setzeSteinPositionZurück(draggableObject, event.getSceneX() - xOffset, event.getSceneY() - yOffset);
                }
            } else {
                setzeSteinPositionZurück(draggableObject, event.getSceneX() - xOffset, event.getSceneY() - yOffset);
            }
        });
    }

    private boolean istInnerhalbGrenzen(double x, double y) {
        int spielX = konvertiereZuSpielKoordinate(x);
        int spielY = konvertiereZuSpielKoordinate(y);
        return (spielX >= 1 && spielX <= 15 && spielY >= 1 && spielY <= 15) ||
                (spielX >= 5 && spielX <= 11 && spielY == 17) ||
                (spielX >= 5 && spielX <= 11 && spielY == 16);
    }

    public void auswertenButton() {
        auswertenButton.setStyle("-fx-text-fill: green; -fx-background-color: transparent;");
        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(event -> {
            auswertenButton.setStyle("-fx-text-fill: -fx-text-base-color; -fx-background-color: transparent;");
        });
        pause.play();
    }

    public void neuButton() {
        neuButton.setStyle("-fx-text-fill: green; -fx-background-color: transparent;");
        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(event -> {
            neuButton.setStyle("-fx-text-fill: -fx-text-base-color; -fx-background-color: transparent;");
        });
        pause.play();
    }
}

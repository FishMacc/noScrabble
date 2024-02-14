package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class DragDropDemoController {

    @FXML
    private Pane drawingPane;
    @FXML
    private Pane spielersteine;
    private List<Pane> draggableObjects = new ArrayList<>(); // Liste der draggable Objekte


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
        drawMatrix();
        setupDraggableObjects();
    }

    private int convertToGameCoordinate(double pixelCoordinate) {
        int sizeWithLines = 50 + 1; // Größe jedes Feldes plus Dicke der Linien

        // Runde die Pixelkoordinate zur nächsten Zelle
        double roundedCoordinate = Math.round(pixelCoordinate / sizeWithLines);

        // Subtrahiere 1, um von 1-basierten zu 0-basierten Koordinaten zu wechseln
        int gameCoordinate = (int) roundedCoordinate - 1;

        return gameCoordinate;
    }

    private void printGameCoordinates(Pane draggableObject) {
        double xPosition = draggableObject.getLayoutX();
        double yPosition = draggableObject.getLayoutY();

        int gameX = convertToGameCoordinate(xPosition);
        int gameY = convertToGameCoordinate(yPosition);

        System.out.println("Position von " + draggableObject + "- X: " + gameX + ", Y: " + gameY);
    }


    private void drawMatrix() {
        int size = 50; // Größe jedes Feldes
        int thickness = 1; // Dicke der Linien
        int cells = 15; // Anzahl der Zellen in jeder Richtung

        // Berechne die Gesamtgröße des Gitters
        int totalSize = cells * size + (cells - 1) * thickness;

        // Stellen Sie sicher, dass das drawingPane groß genug ist
        drawingPane.setPrefSize(totalSize, totalSize);

        // Zeichne die horizontalen und vertikalen Linien
        for (int i = 0; i <= cells; i++) {
            // Horizontale Linie
            Line horizontalLine = new Line(0, i * (size + thickness), totalSize, i * (size + thickness));
            horizontalLine.setStrokeWidth(thickness);

            // Vertikale Linie
            Line verticalLine = new Line(i * (size + thickness), 0, i * (size + thickness), totalSize);
            verticalLine.setStrokeWidth(thickness);

            drawingPane.getChildren().addAll(horizontalLine, verticalLine);
        }
    }


    private void setupDraggableObjects() {
        // Fügen Sie Ihre draggable Objekte zur Liste hinzu
        draggableObjects.add(stein1);
        draggableObjects.add(stein2);
        draggableObjects.add(stein3);
        draggableObjects.add(stein4);
        draggableObjects.add(stein5);
        draggableObjects.add(stein6);
        draggableObjects.add(stein7);

        // Wenden Sie die Methode für jedes Objekt in der Liste an
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

            // Überprüfe, ob sich der Spielstein innerhalb der zulässigen Bereiche befindet
            if (isWithinBounds(newX, newY)) {
                draggableObject.setLayoutX(newX);
                draggableObject.setLayoutY(newY);
            }
        });

        draggableObject.setOnMouseReleased(event -> {
            // Größe jedes Feldes plus Dicke der Linien
            int sizeWithLines = 50 + 1; // Hier nehmen wir an, dass die Dicke der Linie in die Größe der Zelle einbezogen wird
            // Berechne die Mitte der nächsten Zelle
            double newX = Math.round((draggableObject.getLayoutX()) / sizeWithLines) * sizeWithLines;
            double newY = Math.round((draggableObject.getLayoutY()) / sizeWithLines) * sizeWithLines;
            // Aktualisiere die Position des Objekts, um es in die Mitte der nächsten Zelle zu setzen
            //draggableObject.setLayoutX(newX + 2); // +1 für die Linienverschiebung
            //draggableObject.setLayoutY(newY + -18); // +1 für die Linienverschiebung


            if (isWithinBounds(newX, newY)) {
                // Aktualisiere die Position des Objekts, um es in die Mitte der nächsten Zelle zu setzen
                draggableObject.setLayoutX(newX + 2); // +1 für die Linienverschiebung
                draggableObject.setLayoutY(newY - 18); // +1 für die Linienverschiebung

                printGameCoordinates(draggableObject);
            } else {
                // Setze den Stein zurück auf die ursprüngliche Position
                draggableObject.setLayoutX(event.getSceneX() - xOffset);
                draggableObject.setLayoutY(event.getSceneY() - yOffset);
            }
        });


    }

    private boolean isWithinBounds(double x, double y) {
        int gameX = convertToGameCoordinate(x);
        int gameY = convertToGameCoordinate(y);

        // Überprüfe, ob sich die Position innerhalb der zulässigen Bereiche befindet
        return (gameX >= 1 && gameX <= 15 && gameY >= 1 && gameY <= 15) || (gameX >= 5 && gameX <= 11 && gameY == 17 || (gameX >= 5 && gameX <= 11 && gameY == 16));
    }
}
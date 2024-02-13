package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class DragDropDemoController {

    @FXML
    private Pane drawingPane; // Das Pane, auf dem die Matrix gezeichnet wird
    @FXML
    private Rectangle draggableRectangle; // Das bewegliche Rechteck

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private void initialize() {
        drawMatrix();
        setupDraggableRectangle();
    }

    private void drawMatrix() {
        int size = 46; // Größe jedes Feldes
        int thickness = 2; // Dicke der Linien
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

    private void setupDraggableRectangle() {
        draggableRectangle.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - draggableRectangle.getX();
            yOffset = event.getSceneY() - draggableRectangle.getY();
        });

        draggableRectangle.setOnMouseDragged(event -> {
            draggableRectangle.setX(event.getSceneX() - xOffset);
            draggableRectangle.setY(event.getSceneY() - yOffset);
        });

        draggableRectangle.setOnMouseReleased(event -> {
            // Größe jedes Feldes plus Dicke der Linien
            int sizeWithLines = 46 + 2; // Hier nehmen wir an, dass die Dicke der Linie in die Größe der Zelle einbezogen wird
            // Berechne die Mitte der nächsten Zelle
            double newX = Math.round((draggableRectangle.getX()) / sizeWithLines) * sizeWithLines;
            double newY = Math.round((draggableRectangle.getY()) / sizeWithLines) * sizeWithLines;
            // Aktualisiere die Position des Rechtecks, um es in die Mitte der nächsten Zelle zu setzen
            draggableRectangle.setX(newX + 4); // +1 für die Linienverschiebung
            draggableRectangle.setY(newY + 4); // +1 für die Linienverschiebung
        });
    }

}

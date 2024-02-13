package de.nocompany.noscrabble.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;


import java.util.ArrayList;
import java.util.List;

public class DragDropDemoController {

    @FXML
    private Pane drawingPane; // Das Pane, auf dem die Matrix gezeichnet wird
    private List<Pane> draggableObjects = new ArrayList<>(); // Liste der draggable Objekte

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private Pane draggableA;
    @FXML
    private Pane draggableB;
    @FXML
    private Pane draggableC;

    @FXML
    private void initialize() {
        drawMatrix();
        setupDraggableObjects();
    }

    private void drawMatrix() {
        // ... (unverändert)
    }

    private void setupDraggableObjects() {
        // Fügen Sie Ihre draggable Objekte zur Liste hinzu
        draggableObjects.add(draggableA);
        draggableObjects.add(draggableB);
        draggableObjects.add(draggableC);

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
            draggableObject.setLayoutX(event.getSceneX() - xOffset);
            draggableObject.setLayoutY(event.getSceneY() - yOffset);
        });

        draggableObject.setOnMouseReleased(event -> {
            // Größe jedes Feldes plus Dicke der Linien
            int sizeWithLines = 50 + 1; // Hier nehmen wir an, dass die Dicke der Linie in die Größe der Zelle einbezogen wird
            // Berechne die Mitte der nächsten Zelle
            double newX = Math.round((draggableObject.getLayoutX()) / sizeWithLines) * sizeWithLines;
            double newY = Math.round((draggableObject.getLayoutY()) / sizeWithLines) * sizeWithLines;
            // Aktualisiere die Position des Objekts, um es in die Mitte der nächsten Zelle zu setzen
            draggableObject.setLayoutX(newX + 3); // +1 für die Linienverschiebung
            draggableObject.setLayoutY(newY + -5); // +1 für die Linienverschiebung
        });
    }
}
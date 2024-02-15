package de.nocompany.noscrabble.gui;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class SpielfeldController {

    @FXML
    private Pane drawingPane;
    @FXML
    private Pane spielersteine;
    private List<Pane> draggableObjects = new ArrayList<>(); // Liste der draggable Objekte
    @FXML
    private Button auswertenButton;
    @FXML
    private Button neuButton;
    @FXML
    private Text auswertungsText;

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

    private boolean isCellOccupied(double x, double y, Pane currentStone) {
        // Größe jedes Feldes plus Dicke der Linien
        int sizeWithLines = 50 + 1;

        // Berechne die Mitte der nächsten Zelle
        double newX = Math.round(x / sizeWithLines) * sizeWithLines;
        double newY = Math.round(y / sizeWithLines) * sizeWithLines;

        for (Pane draggableObject : draggableObjects) {
            if (draggableObject != currentStone) {
                double objX = draggableObject.getLayoutX();
                double objY = draggableObject.getLayoutY();

                // Berechne die Mitte des anderen Steins
                double otherX = Math.round(objX / sizeWithLines) * sizeWithLines;
                double otherY = Math.round(objY / sizeWithLines) * sizeWithLines;

                // Überprüfe, ob sich die Mittelpunkte der Steine auf demselben Feld befinden
                if (newX == otherX && newY == otherY) {
                    return true;
                }
            }
        }
        return false;
    }

    private void resetStonePosition(Pane draggableObject, double originalX, double originalY) {
        draggableObject.setLayoutX(originalX);
        draggableObject.setLayoutY(originalY);
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
            if (isWithinBounds(newX, newY) && !isCellOccupied(newX, newY, draggableObject)) {
                draggableObject.setLayoutX(newX);
                draggableObject.setLayoutY(newY);
            }
        });

        draggableObject.setOnMouseReleased(event -> {
            int sizeWithLines = 50 + 1;

            // Berechne die Mitte der nächsten Zelle
            double newX = Math.round((draggableObject.getLayoutX()) / sizeWithLines) * sizeWithLines;
            double newY = Math.round((draggableObject.getLayoutY()) / sizeWithLines) * sizeWithLines;

            // Überprüfe, ob sich der Spielstein innerhalb der zulässigen Bereiche befindet
            if (isWithinBounds(newX, newY)) {
                // Überprüfe, ob die beabsichtigte Position frei ist
                if (!isCellOccupied(newX, newY, draggableObject)) {
                    // Aktualisiere die Position des Objekts, um es in die Mitte der nächsten Zelle zu setzen
                    draggableObject.setLayoutX(newX + 2); // +1 für die Linienverschiebung
                    draggableObject.setLayoutY(newY - 18); // +1 für die Linienverschiebung

                    printGameCoordinates(draggableObject);
                } else {
                    // Setze den Stein zurück auf die ursprüngliche Position, wenn die Position besetzt ist
                    resetStonePosition(draggableObject, event.getSceneX() - xOffset, event.getSceneY() - yOffset);
                }
            } else {
                // Setze den Stein zurück auf die ursprüngliche Position, wenn die Position außerhalb der zulässigen Bereiche liegt
                resetStonePosition(draggableObject, event.getSceneX() - xOffset, event.getSceneY() - yOffset);
            }
        });
    }

    private boolean isWithinBounds(double x, double y) {
        int gameX = convertToGameCoordinate(x);
        int gameY = convertToGameCoordinate(y);

        // Überprüfe, ob sich die Position innerhalb der zulässigen Bereiche befindet
        return (gameX >= 1 && gameX <= 15 && gameY >= 1 && gameY <= 15) || (gameX >= 5 && gameX <= 11 && gameY == 17 || (gameX >= 5 && gameX <= 11 && gameY == 16));
    }

    public void auswertenButton() {
        // Ändere die Textfarbe des Buttons kurzzeitig
        auswertenButton.setStyle("-fx-text-fill: green; -fx-background-color: transparent;");

        // ...

        // Verzögere die Farbänderung für einen kurzen Moment
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Anpassen Sie die Dauer nach Bedarf
        pause.setOnFinished(event -> {
            // Setze die Textfarbe des Buttons zurück
            auswertenButton.setStyle("-fx-text-fill: -fx-text-base-color; -fx-background-color: transparent;");
        });
        pause.play();
    }

    public void neuButton() {
        // Ändere die Farbe des Buttons kurzzeitig
        neuButton.setStyle("-fx-text-fill: green; -fx-background-color: transparent;");

        // Führe hier den gewünschten Code für die Auswertung durch

        // Verzögere die Farbänderung für einen kurzen Moment
        PauseTransition pause = new PauseTransition(Duration.millis(200)); // Anpassen Sie die Dauer nach Bedarf
        pause.setOnFinished(event -> {
            // Setze die Farbe des Buttons zurück
            neuButton.setStyle("-fx-text-fill: -fx-text-base-color; -fx-background-color: transparent;");
        });
        pause.play();
    }
}
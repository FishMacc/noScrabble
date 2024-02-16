package de.nocompany.noscrabble.gui;

import de.nocompany.noscrabble.service.serviceSpiel.ServiceSpielImpl;
import de.nocompany.noscrabble.service.serviceSpiel.ServiceSpielInterface;
import de.nocompany.noscrabble.utils.Spielstein;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.IOException;


import java.util.ArrayList;
import java.util.List;

public class SpielfeldController {

    private final ServiceSpielInterface spielService = new ServiceSpielImpl();
    public Pane drawingPane;


    private List<Spielstein> draggableObjects = new ArrayList<>(); // Liste der draggable Objekte

    @FXML
    private Button auswertenButton;
    @FXML
    private Button neuButton;
    private double xOffset = 0;
    private double yOffset = 0;

    private BorderPane root;



    @FXML
    private void initialize() {
    }


    public void erzeugeNeuesSpiel(List<String> spielerListe) {
        System.out.println("Empfange Spieler: "+spielerListe);
        spielService.neuesSpiel(spielerListe);

    }



    public void erstelleSpielstein(char buchstabe, double xPosition, double yPosition) {

        Pane steinPane = new Pane();
        steinPane.setPrefSize(45.0, 45.0);

        ImageView imageView = new ImageView(new Image(getClass().getResource("../img/Stein.png").toExternalForm()));
        imageView.setFitHeight(45.0);
        imageView.setFitWidth(45.0);
        imageView.setPickOnBounds(true);
        imageView.setPreserveRatio(true);

        steinPane.setLayoutX(xPosition);
        steinPane.setLayoutY(yPosition);

        Label buchstabenLabel = new Label(String.valueOf(buchstabe));
        buchstabenLabel.setFont(new Font("System Bold", 27.0));
        buchstabenLabel.setLayoutX(6.0);
        buchstabenLabel.setLayoutY(0);
        buchstabenLabel.setPrefSize(21.0, 45.0);
        buchstabenLabel.setTextAlignment(TextAlignment.CENTER);

        Label punkteLabel = new Label("1"); // Beispielwert, anpassen nach Bedarf
        punkteLabel.setLayoutX(31.0);
        punkteLabel.setLayoutY(23.0);
        punkteLabel.setPrefSize(13.0, 17.0);

        steinPane.getChildren().addAll(imageView, buchstabenLabel, punkteLabel);

        Spielstein spielstein = new Spielstein(steinPane, false, buchstabe);

        draggableObjects.add(spielstein);

        // Hier müssen Sie die Steine zum UI hinzufügen, z.B. zu einem Pane, das das Spielfeld darstellt
        // Beispiel: root.getChildren().add(steinPane);
        // Stellen Sie sicher, dass Sie das richtige Container-Element für Ihr Layout haben
        drawingPane.getChildren().add(steinPane);
        setupDraggableObject(steinPane); // Passen Sie diese Methode entsprechend an, um mit dem Spielstein-Objekt zu arbeiten
    }


    public void starteNeueRunde() {
        // Hier kannst du Logik hinzufügen, um festzulegen, welche Buchstaben generiert werden sollen
        //TODO hohle buchstabenvom aktiven spieler
        char[] buchstaben = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};// Beispielbuchstaben
        double[] xPositionen = new double[]{308.0, 359.0, 410.0, 461.0, 512.0, 563.0, 614.0}; // Beispiel X-Positionen
        double[] yPosition = new double[]{900.0, 900.0, 900.0, 900.0, 900.0, 900.0, 900.0}; // Y-Positionen sind gleich, da alle auf derselben Höhe

        for (int i = 0; i < buchstaben.length; i++) {
            erstelleSpielstein(buchstaben[i], xPositionen[i], yPosition[i]);
        }

        // Weitere Logik zur Einrichtung der neuen Runde
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

    private boolean istZelleBesetzt(double x, double y, Pane currentStonePane) {
        int größeMitLinien = 50 + 1;
        double newX = Math.round(x / größeMitLinien) * größeMitLinien;
        double newY = Math.round(y / größeMitLinien) * größeMitLinien;

        for (Spielstein draggableObject : draggableObjects) {
            Pane draggableObjectPane = draggableObject.getPane(); // Angenommen, Spielstein hat eine Methode getPane(), die das Pane zurückgibt
            if (draggableObjectPane != currentStonePane) {
                double objX = draggableObjectPane.getLayoutX();
                double objY = draggableObjectPane.getLayoutY();
                double otherX = Math.round(objX / größeMitLinien) * größeMitLinien;
                double otherY = Math.round(objY / größeMitLinien) * größeMitLinien;

                if (newX == otherX && newY == otherY) {
                    return false;
                }
            }
        }
        return true;
    }


    private void setzeSteinPositionZurück(Pane draggableObject, double originalX, double originalY) {
        draggableObject.setLayoutX(originalX);
        draggableObject.setLayoutY(originalY);
    }



    private void setupDraggableObject(Pane draggableObject) {
        draggableObject.setOnMousePressed(event -> {
            xOffset = event.getSceneX() - draggableObject.getLayoutX();
            yOffset = event.getSceneY() - draggableObject.getLayoutY();
        });

        draggableObject.setOnMouseDragged(event -> {
            double newX = event.getSceneX() - xOffset;
            double newY = event.getSceneY() - yOffset;

            if (istInnerhalbGrenzen(newX, newY) && istZelleBesetzt(newX, newY, draggableObject)) {
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
                if (istZelleBesetzt(newX, newY, draggableObject)) {
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
        //TODO spielService.pruefeWoerter()
    }

    public void neuButton() {
        neuButton.setStyle("-fx-text-fill: green; -fx-background-color: transparent;");
        PauseTransition pause = new PauseTransition(Duration.millis(200));
        pause.setOnFinished(event -> {
            neuButton.setStyle("-fx-text-fill: -fx-text-base-color; -fx-background-color: transparent;");
        });
        pause.play();
        //TODO spielService.ersetzeBuchstaben();
        spielService.passeRunde();
    }

    public void spielEnde(String gewinner) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/spielEnde.fxml"));
            Parent endeContent = fxmlLoader.load();
            SpielEndeController controller = fxmlLoader.getController();
            controller.setlSpielerGewinner(gewinner);
            root.setCenter(endeContent);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    public void setRoot(BorderPane root) {
        this.root = root;
    }
}



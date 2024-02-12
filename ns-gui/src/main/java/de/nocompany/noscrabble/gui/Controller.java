package de.nocompany.noscrabble.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Controller {

    @FXML
    private Label yourLabel;
    private Label draggedLabel;

    @FXML
    private GridPane gridPane;


    @FXML
    public void initialize() {
        yourLabel.setOnDragDetected(event -> {
            if (event.isPrimaryButtonDown()) {
                draggedLabel = yourLabel;
                Dragboard dragboard = yourLabel.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(yourLabel.getText());
                dragboard.setContent(content);
                event.consume();
            }
        });

        gridPane.setOnDragOver(event -> {
            if (event.getGestureSource() != gridPane && event.getDragboard().hasString()) {
                event.acceptTransferModes(TransferMode.MOVE);
            }
            event.consume();
        });

        gridPane.setOnDragDropped(event -> {
            Dragboard db = event.getDragboard();
            boolean success = false;
            if (db.hasString() && draggedLabel != null) {
                int colIndex = (int) ((event.getX() - gridPane.getLayoutX()) / 100);
                int rowIndex = (int) ((event.getY() - gridPane.getLayoutY()) / 30);

                Label newLabel = new Label(db.getString());
                gridPane.add(newLabel, colIndex, rowIndex);

                HBox parentBox = (HBox) draggedLabel.getParent();
                parentBox.getChildren().remove(draggedLabel);


                success = true;
            }
            event.setDropCompleted(success);
            event.consume();
        });
    }
}
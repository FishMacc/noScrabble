package de.nocompany.noscrabble.utils;

import javafx.scene.layout.Pane;

public class Spielstein {
    private Pane pane;
    private boolean freeze;
    private char buchstabe;

    public Spielstein(Pane pane, boolean freeze, char buchstabe) {
        this.pane = pane;
        this.freeze = freeze;
        this.buchstabe = buchstabe;
    }

    // Getter und Setter hier
}

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

    // Getter für Pane
    public Pane getPane() {
        return pane;
    }

    // Getter und Setter für freeze
    public boolean isFreeze() {
        return freeze;
    }

    public void setFreeze(boolean freeze) {
        this.freeze = freeze;
    }

    // Getter und Setter für buchstabe
    public char getBuchstabe() {
        return buchstabe;
    }

    public void setBuchstabe(char buchstabe) {
        this.buchstabe = buchstabe;
    }
}

package de.nocompany.noscrabble.model.spieler;

import java.util.ArrayList;
import java.util.List;

public class Spieler{
    private String name;
    private List<String> buchstabenRack = new ArrayList<>();
    private int punkte = 0;
    private boolean turn = false;
    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void addBuchstaben(List<String> neueBuchstaben) {
        buchstabenRack.addAll(neueBuchstaben);
    }

    public void removeBuchstaben(List<String> verwendeteBuchstaben) {
        buchstabenRack.removeAll(verwendeteBuchstaben);
    }

    public void updatePunkte(int addpunkte) {
        this.punkte+=addpunkte;
    }

    public int getPunkte() {
        return this.punkte;
    }

    public List<String> getBuchstabenRack() {
        return this.buchstabenRack;
    }

    public void setTurn(boolean isTurn) {
        turn=isTurn;
    }

    public boolean isTurn() {
        return turn;
    }
}

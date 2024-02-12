package de.nocompany.noscrabble.model.spieler;

import java.util.ArrayList;
import java.util.List;

public class SpielerImpl implements Spieler{
    private String name;
    private List<String> buchstabenRack = new ArrayList<>();
    private int punkte = 0;
    private boolean turn = false;
    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addBuchstaben(List<String> neueBuchstaben) {
        buchstabenRack.addAll(neueBuchstaben);
    }

    @Override
    public void removeBuchstaben(List<String> verwendeteBuchstaben) {
        buchstabenRack.removeAll(verwendeteBuchstaben);
    }

    @Override
    public void updatePunkte(int addpunkte) {
        this.punkte+=addpunkte;
    }

    @Override
    public int getPunkte() {
        return this.punkte;
    }

    @Override
    public List<String> getBuchstabenRack() {
        return this.buchstabenRack;
    }

    @Override
    public void setTurn(boolean isTurn) {
        turn=isTurn;
    }

    @Override
    public boolean isTurn() {
        return turn;
    }
}

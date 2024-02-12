package de.nocompany.noscrabble.model.spieler;

import de.nocompany.noscrabble.model.spielsteinpool.SpielsteinPool;

import java.util.ArrayList;
import java.util.List;

public class Spieler{
    private String name;
    private List<Character> buchstabenRack = new ArrayList<>();
    private int punkte = 0;
    private boolean turn = false;
    private SpielsteinPool pool;
    Spieler(SpielsteinPool pool){
        this.pool = pool;
        getBuchstaben();
    }
    public void setName(String name) {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void addBuchstaben(List<Character> neueBuchstaben) {
        buchstabenRack.addAll(neueBuchstaben);
    }

    public void removeBuchstaben(List<Character> verwendeteBuchstaben) {
        buchstabenRack.removeAll(verwendeteBuchstaben);
    }

    public void updatePunkte(int addpunkte) {
        this.punkte+=addpunkte;
    }

    public int getPunkte() {
        return this.punkte;
    }

    public List<Character> getBuchstabenRack() {
        return this.buchstabenRack;
    }

    public void setTurn(boolean isTurn) {
        turn=isTurn;
    }

    public boolean isTurn() {
        return turn;
    }

    public void getBuchstaben() {
        int steine = 7 - buchstabenRack.size();
        for (int i = 0; i < steine; i++) {
            buchstabenRack.add(pool.getBuchstabe());
        }
    }

    public void givebackBuchstabe(int index){
        pool.addBuchstabe(buchstabenRack.get(index));
        buchstabenRack.remove(index);
    }
}

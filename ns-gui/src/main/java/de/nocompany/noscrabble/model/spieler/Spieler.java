package de.nocompany.noscrabble.model.spieler;

import java.util.ArrayList;
import java.util.List;

public interface Spieler {
    void setName(String name);

    String getName();

    void addBuchstaben(List<String> neueBuchstaben);

    void removeBuchstaben(List<String> verwendeteBuchstaben);

    void updatePunkte(int addpunkte);

    int getPunkte();

    List<String> getBuchstabenRack();

    void setTurn(boolean isTurn);

    boolean isTurn();
}

package de.nocompany.noscrabble.model.leaderboard;

import de.nocompany.noscrabble.model.spieler.Spieler;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {
    public List<Spieler> spielerListe = new ArrayList<>();

    public Leaderboard(List<Spieler> spielerListe) {
        this.spielerListe = spielerListe;
        sortiereSpieler();
    }

    public void sortiereSpieler() {
        spielerListe.sort((s1, s2) -> Integer.compare(s2.getPunkte(), s1.getPunkte()));
    }

    public Spieler whosTurn() {
        for (Spieler elem : spielerListe) {
            if (elem.isTurn()) {
                return elem;
            }
        }
        return null;
    }

    public List<String> leaderboardList() {
        List<String> temp = new ArrayList<>();
        for (Spieler elem : spielerListe) {
            temp.add(elem.getName() + "\t" + elem.getPunkte());
        }
        return temp;
    }

}

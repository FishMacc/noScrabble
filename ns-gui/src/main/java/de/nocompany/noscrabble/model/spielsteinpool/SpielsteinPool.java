package de.nocompany.noscrabble.model.spielsteinpool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SpielsteinPool {
    private List<Character> buchstabenListe;
    private Random random = new Random();

    public SpielsteinPool() {
        buchstabenListe = new ArrayList<>();
        initialisiereBuchstabenListe();
    }

    private void initialisiereBuchstabenListe() {
        Map<Character, Integer> buchstabenHaeufigkeiten = new HashMap<>();
        // 1 Punkt Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('E', 15);
        buchstabenHaeufigkeiten.put('N', 9);
        buchstabenHaeufigkeiten.put('S', 7);
        buchstabenHaeufigkeiten.put('I', 6);
        buchstabenHaeufigkeiten.put('R', 6);
        buchstabenHaeufigkeiten.put('T', 6);
        buchstabenHaeufigkeiten.put('U', 6);
        buchstabenHaeufigkeiten.put('A', 5);
        buchstabenHaeufigkeiten.put('D', 4);

        // 2 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('H', 4);
        buchstabenHaeufigkeiten.put('G', 3);
        buchstabenHaeufigkeiten.put('L', 3);
        buchstabenHaeufigkeiten.put('O', 3);

        // 3 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('M', 4);
        buchstabenHaeufigkeiten.put('B', 2);
        buchstabenHaeufigkeiten.put('W', 1);
        buchstabenHaeufigkeiten.put('Z', 1);

        // 4 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('C', 2);
        buchstabenHaeufigkeiten.put('F', 2);
        buchstabenHaeufigkeiten.put('K', 2);
        buchstabenHaeufigkeiten.put('P', 1);

        // 6 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('Ä', 1);
        buchstabenHaeufigkeiten.put('J', 1);
        buchstabenHaeufigkeiten.put('Ü', 1);
        buchstabenHaeufigkeiten.put('V', 1);

        // 8 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('Ö', 1);
        buchstabenHaeufigkeiten.put('X', 1);

        // 10 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('Q', 1);
        buchstabenHaeufigkeiten.put('Y', 1);

        for (Map.Entry<Character, Integer> eintrag : buchstabenHaeufigkeiten.entrySet()) {
            char buchstabe = eintrag.getKey();
            int haeufigkeit = eintrag.getValue();
            for (int i = 0; i < haeufigkeit; i++) {
                buchstabenListe.add(buchstabe);
            }
        }
    }
    public char getBuchstabe() {
        if (buchstabenListe.isEmpty()) {
            throw new IllegalStateException("Der Buchstabenpool ist leer.");
        }
        int index = random.nextInt(buchstabenListe.size());
        return buchstabenListe.remove(index);
    }

    public void addBuchstabe(char buchstabe){
        buchstabenListe.add(buchstabe);
    }
}

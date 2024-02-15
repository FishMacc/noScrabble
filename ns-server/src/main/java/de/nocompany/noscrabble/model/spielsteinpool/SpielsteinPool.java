package de.nocompany.noscrabble.model.spielsteinpool;

import java.util.*;

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
        buchstabenHaeufigkeiten.put('E', 16);
        buchstabenHaeufigkeiten.put('N', 10);
        buchstabenHaeufigkeiten.put('S', 8);
        buchstabenHaeufigkeiten.put('I', 9);
        buchstabenHaeufigkeiten.put('R', 7);
        buchstabenHaeufigkeiten.put('U', 6);
        buchstabenHaeufigkeiten.put('A', 6);
        buchstabenHaeufigkeiten.put('D', 6);

        // 2 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('T', 5);
        buchstabenHaeufigkeiten.put('H', 5);
        buchstabenHaeufigkeiten.put('G', 3);
        buchstabenHaeufigkeiten.put('C', 4);
        buchstabenHaeufigkeiten.put('L', 4);
        buchstabenHaeufigkeiten.put('O', 4);
        buchstabenHaeufigkeiten.put('W', 2);

        // 3 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('M', 4);
        buchstabenHaeufigkeiten.put('B', 2);
        buchstabenHaeufigkeiten.put('Z', 2);
        buchstabenHaeufigkeiten.put('F', 3);
        buchstabenHaeufigkeiten.put('K', 2);

        // 4 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('P', 1);
        buchstabenHaeufigkeiten.put('V', 1);

        // 5 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('\u00DC', 1);

        // 6 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('\u00C4', 1);
        buchstabenHaeufigkeiten.put('J', 1);

        // 8 Punkte Buchstaben und ihre Häufigkeiten
        buchstabenHaeufigkeiten.put('\u00D6', 1);
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

    public void addBuchstabe(char buchstabe) {
        buchstabenListe.add(buchstabe);
    }

    public int getListenLaenge() {
        return buchstabenListe.size();
    }
}

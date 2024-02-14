package de.nocompany.noscrabble.model.punktemultiplikator;

import java.util.HashMap;
import java.util.Map;

import static de.nocompany.noscrabble.model.punktemultiplikator.Multiplikator.*;

public class Punktemultiplikator {
    private static final Multiplikator[][] punkteMultiplikatoren = {
            // A     B     C     D     E     F     G     H     I     J     K     L     M     N     O
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, TW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW},
            {NORMAL, DW, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, DW, NORMAL},
            {NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL},
            {DL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, DL},
            {NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL},
            {NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL},
            {NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL},
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW},
            {NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL},
            {NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL},
            {NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL},
            {DL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, DL},
            {NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL},
            {NORMAL, DW, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, DW, NORMAL},
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, TW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW}
    };

    public int calcLetterPunkte(Character letter) {
        Map<Character, Integer> buchstabenPunkte = new HashMap<>();
        buchstabenPunkte.put('E', 1);
        buchstabenPunkte.put('N', 1);
        buchstabenPunkte.put('S', 1);
        buchstabenPunkte.put('I', 1);
        buchstabenPunkte.put('R', 1);
        buchstabenPunkte.put('T', 1);
        buchstabenPunkte.put('U', 1);
        buchstabenPunkte.put('A', 1);
        buchstabenPunkte.put('D', 1);
        buchstabenPunkte.put('H', 2);
        buchstabenPunkte.put('G', 2);
        buchstabenPunkte.put('L', 2);
        buchstabenPunkte.put('O', 2);
        buchstabenPunkte.put('M', 3);
        buchstabenPunkte.put('B', 3);
        buchstabenPunkte.put('W', 3);
        buchstabenPunkte.put('Z', 3);
        buchstabenPunkte.put('C', 4);
        buchstabenPunkte.put('F', 4);
        buchstabenPunkte.put('K', 4);
        buchstabenPunkte.put('P', 4);
        buchstabenPunkte.put('\u00C4', 6);  // Unicode-Escape für 'Ä'
        buchstabenPunkte.put('J', 6);
        buchstabenPunkte.put('\u00DC', 6);  // Unicode-Escape für 'Ü'
        buchstabenPunkte.put('V', 6);
        buchstabenPunkte.put('\u00D6', 8);  // Unicode-Escape für 'Ö'
        buchstabenPunkte.put('X', 8);
        buchstabenPunkte.put('Q', 10);
        buchstabenPunkte.put('Y', 10);

        return buchstabenPunkte.getOrDefault(letter, 0);
    }

    public int calcWortPunkte(String wort, int startx, int starty, boolean horizontal) {
        wort = wort.toUpperCase();
        int gesamtPunkte = 0;
        int wortMultiplikator = 1;

        for (int i = 0; i < wort.length(); i++) {
            int x = horizontal ? startx + i : startx;
            int y = horizontal ? starty : starty + i;

            char letter = wort.charAt(i);
            int buchstabenPunkte = calcLetterPunkte(letter);

            Multiplikator multiplikator = punkteMultiplikatoren[y][x];
            punkteMultiplikatoren[y][x] = NORMAL;

            if (multiplikator == Multiplikator.DW || multiplikator == TW) {
                wortMultiplikator *= multiplikator.getWert();
                gesamtPunkte += buchstabenPunkte;
            } else {
                gesamtPunkte += buchstabenPunkte * multiplikator.getWert();
            }
        }
        gesamtPunkte *= wortMultiplikator;
        return gesamtPunkte;
    }
}

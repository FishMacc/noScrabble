package de.nocompany.noscrabble.model.punktemultiplikator;

import java.util.HashMap;
import java.util.Map;

public class punktemultiplikator {
    private static final int NORMAL = 1;
    private static final int DW = 20; // Doppelter Wortwert
    private static final int TW = 30; // Dreifacher Wortwert
    private static final int DL = 2; // Doppelter Buchstabenwert
    private static final int TL = 3; // Dreifacher Buchstabenwert
    private static final int[][] punkteMultiplikatoren = {
            // A     B     C     D     E     F     G     H     I     J     K     L     M     N     O
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, TW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW},
            {NORMAL, DW, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, DW, NORMAL},
            {NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL},
            {DL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, DL},
            {NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL},
            {NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL},
            {NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL},
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW},
            {NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL},
            {NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL},
            {NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, NORMAL},
            {DL, NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL, DL},
            {NORMAL, NORMAL, DW, NORMAL, NORMAL, NORMAL, DL, NORMAL, DL, NORMAL, NORMAL, NORMAL, DW, NORMAL, NORMAL},
            {NORMAL, DW, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, TL, NORMAL, NORMAL, NORMAL, DW, NORMAL},
            {TW, NORMAL, NORMAL, DL, NORMAL, NORMAL, NORMAL, TW, NORMAL, NORMAL, NORMAL, DL, NORMAL, NORMAL, TW}
    };

    private static int calcLetterPunkte(Character letter) {
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
        buchstabenPunkte.put('Ä', 6);
        buchstabenPunkte.put('J', 6);
        buchstabenPunkte.put('Ü', 6);
        buchstabenPunkte.put('V', 6);
        buchstabenPunkte.put('Ö', 8);
        buchstabenPunkte.put('X', 8);
        buchstabenPunkte.put('Q', 10);
        buchstabenPunkte.put('Y', 10);
        return buchstabenPunkte.getOrDefault(letter, 0);
    }

    private  int calcMultiplikator(int x, int y) {
        if (x < 0 || x >= punkteMultiplikatoren[0].length || y < 0 || y >= punkteMultiplikatoren.length) {
            throw new IllegalArgumentException("Ungültige Koordinaten!");
        }
        return punkteMultiplikatoren[y][x];
    }


    public  int calcGesPunkte(int x, int y, Character letter) {
        int buchstabenPunkte = calcLetterPunkte(letter);
        int multiplikator = calcMultiplikator(x, y);

        return buchstabenPunkte;
    }
    public  int calcWortPunkte(String wort, int startx, int starty, boolean horizontal) {
        wort = wort.toUpperCase();
        int gesamtPunkte = 0;
        int wortMultiplikator = 1;

        for (int i = 0; i < wort.length(); i++) {
            int x = horizontal ? startx + i : startx;
            int y = horizontal ? starty : starty + i;

            char letter = wort.charAt(i);
            int buchstabenPunkte = calcGesPunkte(x, y, letter);

            int multiplikator = calcMultiplikator(x, y);
            punkteMultiplikatoren[y][x]=NORMAL;

            if (multiplikator == DW || multiplikator == TW) {
                wortMultiplikator *= multiplikator/10;
                gesamtPunkte += buchstabenPunkte;
            } else {
                gesamtPunkte += buchstabenPunkte * multiplikator;
            }
        }
        gesamtPunkte *= wortMultiplikator;
        return gesamtPunkte;
    }
}

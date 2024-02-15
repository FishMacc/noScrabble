package de.nocompany.noscrabble.service.serviceWoerter;


import de.nocompany.noscrabble.dao.daoWoerter.WoerterDaoImpl;
import de.nocompany.noscrabble.model.punktemultiplikator.Punktemultiplikator;

import java.util.ArrayList;
import java.util.List;

public class ServiceWoerterImpl implements ServiceWoerterInterface {
    public int punkte;
    private WoerterDaoImpl dao = new WoerterDaoImpl();
    private Punktemultiplikator punktemultiplikator = new Punktemultiplikator();

    public int getPunkte() {
        return punkte;
    }

    @Override
    public boolean calcNewWordPoints(Character[][] altBrett, Character[][] neuBrett) {
        int gesamtPunkte = 0;
        List<String> foundWords = new ArrayList<>();

        for (int y = 0; y < neuBrett.length; y++) {
            for (int x = 0; x < neuBrett[y].length; x++) {
                if (!neuBrett[y][x].equals(altBrett[y][x]) && neuBrett[y][x] != ' ') {
                    String[] potenzielleNeueWorte = {
                            extrahiereWort(neuBrett, x, y, true),  // Horizontal
                            extrahiereWort(neuBrett, x, y, false)  // Vertikal
                    };

                    for (String wort : potenzielleNeueWorte) {
                        if (isValidNewWord(wort, altBrett, x, y, foundWords.contains(wort))) {
                            foundWords.add(wort);
                            gesamtPunkte += punktemultiplikator.calcWortPunkte(wort, x, y, wort.equals(potenzielleNeueWorte[0]));
                        }
                    }
                }
            }
        }
        System.out.println(foundWords);
        punkte = gesamtPunkte;
        for (String elem : foundWords) {
            if (!dao.checkWord(elem)) {
                return false;
            }
        }
        return true;
    }

    public String extrahiereWort(Character[][] brett, int startX, int startY, boolean horizontal) {
        String wort = "";
        int x = startX;
        int y = startY;

        if (horizontal) {
            while (x > 0 && brett[y][x - 1] != ' ') {
                x--;
            }
            while (x < brett[y].length && brett[y][x] != ' ') {
                wort += brett[y][x];
                x++;
            }
        } else {
            while (y > 0 && brett[y - 1][x] != ' ') {
                y--;
            }
            while (y < brett.length && brett[y][x] != ' ') {
                wort += brett[y][x];
                y++;
            }
        }
        return wort;
    }

    public boolean isValidNewWord(String wort, Character[][] altBrett, int x, int y, boolean alreadyFound) {
        if (wort.isEmpty() || alreadyFound || wort.length() == 1) return false;

        for (int i = 0; i < wort.length(); i++) {
            int prufX = x + i;
            int prufY = y;
            if (prufX >= 0 && prufX < altBrett[0].length && prufY >= 0 && prufY < altBrett.length) {
                if (!altBrett[prufY][prufX].equals(wort.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }
}
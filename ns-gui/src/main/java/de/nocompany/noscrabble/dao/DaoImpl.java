package de.nocompany.noscrabble.dao;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class DaoImpl implements Dao {
    private static final String DATEINAME = "spielbrett.txt";

    public static boolean checkWord(String wort) {
        HashSet<String> dictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("ns-gui/src/main/resources/dictionary/deu_woerter_raw.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
        return dictionary.contains(wort.toLowerCase());
    }

    @Override
    public void saveSpielbrett(Character[][] spielbrett) {
        try (PrintWriter out = new PrintWriter(DATEINAME)) {
            for (Character[] zeile : spielbrett) {
                for (Character zelle : zeile) {
                    out.print(zelle != null ? zelle : ' ');
                }
                out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Character[][] getSpielbrett() {
        List<Character[]> spielbrettListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATEINAME))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                Character[] zeilenArray = new Character[zeile.length()];
                for (int i = 0; i < zeile.length(); i++) {
                    char c = zeile.charAt(i);
                    zeilenArray[i] = c != ' ' ? c : ' ';
                }
                spielbrettListe.add(zeilenArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spielbrettListe.toArray(new Character[spielbrettListe.size()][]);
    }
}

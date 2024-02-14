package de.nocompany.noscrabble.dao.daoWoerter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class WoerterDaoImpl implements WoerterDaoInterface {
    HashSet<String> dictionary;

    public WoerterDaoImpl() {
        dictionary = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader("ns-gui/src/main/resources/dictionary/deu_woerter_raw.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + e.getMessage());
        }
    }

    public boolean checkWord(String wort) {
        return dictionary.contains(wort.toLowerCase());
    }
}

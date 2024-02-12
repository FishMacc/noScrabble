package de.nocompany.noscrabble.utils;

import java.io.*;
import java.util.HashSet;

public class SpellChecker {

    private static HashSet<String> dictionary = new HashSet<>();

    static {
        loadDictionary("dictionary/deu_woerter_raw.txt");
    }

    private static void loadDictionary(String resourcePath) {
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
             BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"))) {
            String line;
            while ((line = br.readLine()) != null) {
                dictionary.add(line.trim().toLowerCase());
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Ressourcendatei: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Ressourcendatei '" + resourcePath + "' konnte nicht gefunden werden.");
        }
    }

    public static boolean checkWord(String word) {
        return dictionary.contains(word.toLowerCase());
    }
}

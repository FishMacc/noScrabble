package de.nocompany.noscrabble.dao;

public interface Dao {
    static boolean checkWord(String wort) {
        return false;
    }

    void saveSpielbrett(Character[][] field);

    Character[][] getSpielbrett();
}

package de.nocompany.noscrabble.service.serviceWoerter;

public interface ServiceWoerterInterface {
    boolean calcNewWordPoints(Character[][] altBrett, Character[][] neuBrett);

    String extrahiereWort(Character[][] brett, int startX, int startY, boolean horizontal);

    boolean isValidNewWord(String wort, Character[][] altBrett, int x, int y, boolean alreadyFound);

}

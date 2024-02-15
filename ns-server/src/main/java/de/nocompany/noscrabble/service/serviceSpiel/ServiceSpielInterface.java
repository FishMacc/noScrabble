package de.nocompany.noscrabble.service.serviceSpiel;

import java.util.List;

public interface ServiceSpielInterface {

    void neuesSpiel(List<String> spielerListe);

    boolean pruefeWoerter(Character[][] spielbrett);

    void passeRunde();

    void ersetzeBuchstaben(List<Character> zuWechseln);

    void beendeSpiel();

}

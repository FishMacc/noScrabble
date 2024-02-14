package de.nocompany.noscrabble.service.serviceSpiel;

import java.util.List;

public interface ServiceSpielInterface {

    void neuesSpiel(List<String> spielerListe);

    void pruefeWoerter(Character[][] spielbrett);

    void passeRunde();

    void ersetzeBuchstaben();

    void beendeSpiel();

}

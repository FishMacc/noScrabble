package de.nocompany.noscrabble.service.serviceSpiel;

import de.nocompany.noscrabble.model.leaderboard.Leaderboard;
import de.nocompany.noscrabble.model.spieler.Spieler;
import de.nocompany.noscrabble.model.spielsteinpool.SpielsteinPool;
import de.nocompany.noscrabble.service.serviceSpielbrett.ServiceSpielbrettImpl;

import java.util.ArrayList;
import java.util.List;

public class ServiceSpielImpl implements ServiceSpielInterface {
    private SpielsteinPool spielsteinPool;
    private final ArrayList<Spieler> spielerListe;
    private Leaderboard leaderBoard;

    private ServiceSpielbrettImpl serviceSpielbrett;

    public ServiceSpielImpl() {
        this.spielerListe = new ArrayList<Spieler>();
    }
    public Spieler aktiverSpieler;

    @Override
    public void neuesSpiel(List<String> spielerNamen) {
        //SpielsteinPool wird erstellt
        spielsteinPool = new SpielsteinPool();
        //Spieler werden erstellt
        for (String name : spielerNamen) {
            Spieler spieler = new Spieler(spielsteinPool);
            spieler.setName(name);
            spielerListe.add(spieler);
        }
        //Startspieler setzen
        spielerListe.getFirst().setTurn(true);
        //erstelle Leaderboard
        leaderBoard = new Leaderboard(spielerListe);
        //erstelle Spielfeld
        serviceSpielbrett = new ServiceSpielbrettImpl();
    }


    @Override
    public void pruefeWoerter() {
        //TODO
    }

    @Override
    public void passeRunde() {
    //TODO
    }

    @Override
    public void ersetzeBuchstaben(List<Character> zuWechseln) {
        aktiverSpieler.removeBuchstaben(zuWechseln);
        aktiverSpieler.fillRack();
    }

    @Override
    public void beendeSpiel() {
    //TODO
        //für das ende des spieles -> führt zum Endbildschirm
    }
}

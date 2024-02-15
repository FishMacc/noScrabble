package de.nocompany.noscrabble.service.serviceSpiel;

import de.nocompany.noscrabble.model.leaderboard.Leaderboard;
import de.nocompany.noscrabble.model.spieler.Spieler;
import de.nocompany.noscrabble.model.spielsteinpool.SpielsteinPool;
import de.nocompany.noscrabble.service.serviceSpielbrett.ServiceSpielbrettImpl;
import de.nocompany.noscrabble.service.serviceWoerter.ServiceWoerterImpl;

import java.util.ArrayList;
import java.util.List;

public class ServiceSpielImpl implements ServiceSpielInterface {
    private SpielsteinPool spielsteinPool;
    private final ArrayList<Spieler> spielerListe;
    private Leaderboard leaderBoard;

    private ServiceSpielbrettImpl serviceSpielbrett;
    private ServiceWoerterImpl serviceWoerter;

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
        aktiverSpieler = spielerListe.getFirst();
        //erstelle Leaderboard
        leaderBoard = new Leaderboard(spielerListe);
        //erstelle Spielfeld
        serviceSpielbrett = new ServiceSpielbrettImpl();
        serviceWoerter = new ServiceWoerterImpl();

    }


    @Override
    public boolean pruefeWoerter(Character[][] spielbrett) {
        return serviceWoerter.calcNewWordPoints(serviceSpielbrett.getSpielbrett(), spielbrett);
    }

    public int berrechnePunkte() {
        return serviceWoerter.getPunkte();
    }

    @Override
    public void passeRunde() {
        int aktiverIndex = spielerListe.indexOf(aktiverSpieler);
        aktiverSpieler.setTurn(false);
        int naechsterIndex = (aktiverIndex + 1) % spielerListe.size();
        aktiverSpieler = spielerListe.get(naechsterIndex);
        aktiverSpieler.setTurn(true);
    }

    @Override
    public void ersetzeBuchstaben(List<Character> zuWechseln) {
        aktiverSpieler.removeBuchstaben(zuWechseln);
        aktiverSpieler.fillRack();
    }

    @Override
    public void beendeSpiel() {
        leaderBoard.sortiereSpieler();
        System.out.println("Spiel beendet. Endstand:");
        System.out.println(leaderBoard.leaderboardList());
    }
    }
}

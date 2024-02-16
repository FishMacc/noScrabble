package de.nocompany.noscrabble.service.serviceSpielbrett;

import de.nocompany.noscrabble.dao.daoSpielbrett.SpielbrettDaoImpl;
import de.nocompany.noscrabble.dao.daoSpielbrett.SpielbrettDaoInterface;

public class ServiceSpielbrettImpl implements ServiceSpielbrettInterface {
    SpielbrettDaoInterface dao = new SpielbrettDaoImpl();
    char[][] spielfeld = new char[15][15];

    public ServiceSpielbrettImpl() {
        //Estelle Spielfeld
        for (int i = 0; i < spielfeld.length; i++) {
            for (int j = 0; j < spielfeld[i].length; j++) {
                spielfeld[i][j] = ' '; // Füllen jedes Elements mit einem Leerzeichen
            }
        }
    }

    @Override
    public void saveSpielbrett(Character[][] spielbrett) {
        dao.saveSpielbrett(spielbrett);
    }

    @Override
    public Character[][] getSpielbrett() {
        return dao.getSpielbrett();
    }
}

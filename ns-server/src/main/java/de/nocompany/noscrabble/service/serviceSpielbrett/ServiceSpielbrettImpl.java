package de.nocompany.noscrabble.service.serviceSpielbrett;

import de.nocompany.noscrabble.dao.daoSpielbrett.SpielbrettDaoImpl;
import de.nocompany.noscrabble.dao.daoSpielbrett.SpielbrettDaoInterface;

public class ServiceSpielbrettImpl implements ServiceSpielbrettInterface {
    SpielbrettDaoInterface dao = new SpielbrettDaoImpl();

    @Override
    public void saveSpielbrett(Character[][] spielbrett) {
        dao.saveSpielbrett(spielbrett);
    }

    @Override
    public Character[][] getSpielbrett() {
        return dao.getSpielbrett();
    }
}

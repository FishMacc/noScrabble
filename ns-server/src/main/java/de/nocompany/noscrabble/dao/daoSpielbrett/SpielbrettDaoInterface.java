package de.nocompany.noscrabble.dao.daoSpielbrett;

public interface SpielbrettDaoInterface {
    void saveSpielbrett(Character[][] field);

    Character[][] getSpielbrett();
}

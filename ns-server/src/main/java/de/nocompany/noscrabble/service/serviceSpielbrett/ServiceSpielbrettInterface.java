package de.nocompany.noscrabble.service.serviceSpielbrett;

public interface ServiceSpielbrettInterface {
    void saveSpielbrett(Character[][] spielbrett);

    Character[][] getSpielbrett();
}

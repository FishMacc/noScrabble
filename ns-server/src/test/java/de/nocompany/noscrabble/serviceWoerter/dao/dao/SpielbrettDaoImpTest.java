package de.nocompany.noscrabble.serviceWoerter.dao.dao;

import de.nocompany.noscrabble.dao.daoSpielbrett.SpielbrettDaoImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class SpielbrettDaoImpTest {

    private SpielbrettDaoImpl spielbrettDao;

    @BeforeEach
    void setUp() {
        spielbrettDao = new SpielbrettDaoImpl();
    }

    @AfterEach
    void tearDown() {
        File file = new File(SpielbrettDaoImpl.DATEINAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndGetSpielbrett() {
        Character[][] erwartetesSpielbrett = {
                {'A', 'B', 'C'},
                {'D', ' ', 'F'},
                {'G', 'H', 'I'}
        };

        spielbrettDao.saveSpielbrett(erwartetesSpielbrett);
        Character[][] gelesenesSpielbrett = spielbrettDao.getSpielbrett();
        assertArrayEquals(erwartetesSpielbrett, gelesenesSpielbrett);
    }
}

package de.nocompany.noscrabble.serviceWoerter.dao.serviceWoerter;

import de.nocompany.noscrabble.service.serviceWoerter.ServiceWoerterImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ServiceWoerterImplTest {

    private ServiceWoerterImpl serviceWoerter;

    @BeforeEach
    void setUp() {
        serviceWoerter = new ServiceWoerterImpl();
    }

    @Test
    void testNeuesWortHorizontal() {
        Character[][] altBrett = new Character[15][15];
        Character[][] neuBrett = new Character[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                altBrett[i][j] = ' ';
                neuBrett[i][j] = ' ';
            }
        }

        neuBrett[7][5] = 'H';
        neuBrett[7][6] = 'A';
        neuBrett[7][7] = 'L';
        neuBrett[7][8] = 'L';
        neuBrett[7][9] = 'O';

        assertTrue(serviceWoerter.calcNewWordPoints(altBrett, neuBrett));
        assertEquals(9, serviceWoerter.getPunkte());
    }

    @Test
    void testNeuesWortVertikal() {
        Character[][] altBrett = new Character[15][15];
        Character[][] neuBrett = new Character[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                altBrett[i][j] = ' ';
                neuBrett[i][j] = ' ';
            }
        }

        neuBrett[0][7] = 'W';
        neuBrett[1][7] = 'O';
        neuBrett[2][7] = 'R';
        neuBrett[3][7] = 'T';

        assertTrue(serviceWoerter.calcNewWordPoints(altBrett, neuBrett));
        assertEquals(24, serviceWoerter.getPunkte());
        neuBrett[4][7] = 'S';
        assertTrue(serviceWoerter.calcNewWordPoints(altBrett, neuBrett));
        assertEquals(8, serviceWoerter.getPunkte());

    }

    @Test
    void testKeinNeuesWort() {
        Character[][] altBrett = new Character[15][15];
        Character[][] neuBrett = new Character[15][15];

        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                altBrett[i][j] = ' ';
                neuBrett[i][j] = ' ';
            }
        }

        assertTrue(serviceWoerter.calcNewWordPoints(altBrett, neuBrett));
        assertEquals(0, serviceWoerter.getPunkte());
    }
}

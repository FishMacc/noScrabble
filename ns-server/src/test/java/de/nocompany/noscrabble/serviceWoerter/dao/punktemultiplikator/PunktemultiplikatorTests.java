package de.nocompany.noscrabble.serviceWoerter.dao.punktemultiplikator;

import de.nocompany.noscrabble.model.punktemultiplikator.Punktemultiplikator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PunktemultiplikatorTest {

    private Punktemultiplikator punktemultiplikator;

    @BeforeEach
    void setUp() {
        punktemultiplikator = new Punktemultiplikator();
    }

    @Test
    void testCalcLetterPunkte() {
        assertEquals(1, punktemultiplikator.calcLetterPunkte('E'));
        assertEquals(2, punktemultiplikator.calcLetterPunkte('H'));
        assertEquals(10, punktemultiplikator.calcLetterPunkte('Q'));
        assertEquals(0, punktemultiplikator.calcLetterPunkte(' '));
    }

    @Test
    void testCalcWortPunkte() {
        assertEquals(33, punktemultiplikator.calcWortPunkte("Für", 0, 0, true));
        assertEquals(22, punktemultiplikator.calcWortPunkte("Amnesia", 1, 1, true));
        assertEquals(10, punktemultiplikator.calcWortPunkte("CAT", 3, 0, true));
        assertEquals(66, punktemultiplikator.calcWortPunkte("Katzenbaum", 7, 0, false));
    }
}

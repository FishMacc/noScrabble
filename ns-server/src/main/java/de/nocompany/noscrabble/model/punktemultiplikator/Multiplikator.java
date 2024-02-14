package de.nocompany.noscrabble.model.punktemultiplikator;

    public enum Multiplikator {
        NORMAL(1),
        DW(2), // Doppelter Wortwert
        TW(3), // Dreifacher Wortwert
        DL(2),
        TL(3);

        private final int wert;

        Multiplikator(int wert) {
            this.wert = wert;
        }

        public int getWert() {
            return this.wert;
        }
    }


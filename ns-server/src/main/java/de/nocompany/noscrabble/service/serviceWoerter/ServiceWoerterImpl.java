package de.nocompany.noscrabble.service.serviceWoerter;


//import de.nocompany.noscrabble.model.punktemultiplikator.Punktemultiplikator;

public class ServiceWoerterImpl implements ServiceWoerterInterface {

    //  private Punktemultiplikator punktemultiplikator = new Punktemultiplikator();

    public static void main(String[] args) {
        ServiceWoerterInterface test = new ServiceWoerterImpl();
        Character[][] altBrett = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'W', 'E', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'O', ' ', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'J', 'A', 'V', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'D', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'E', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'G', 'A', 'M', 'E', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        Character[][] neuBrett = {
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'H', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'W', 'E', 'B', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'O', ' ', 'O', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', 'J', 'A', 'V', 'A', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'D', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'E', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', 'a', ' ', ' ', ' ', ' ', ' ', 'G', 'A', 'M', 'E', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
        };

        System.out.println(test.calcNewWordPoints(altBrett, neuBrett));

    }

    @Override
    public String calcNewWordPoints(Character[][] altBrett, Character[][] neuBrett) {
        String neuesWort = "";
        int startX = -1;
        int startY = -1;
        boolean horizontal = false;

        outerLoop:
        for (int y = 0; y < neuBrett.length; y++) {
            for (int x = 0; x < neuBrett[y].length; x++) {
                if (!neuBrett[y][x].equals(altBrett[y][x])) {
                    startX = x;
                    startY = y;

                    if (x + 1 < neuBrett[y].length && !neuBrett[y][x + 1].equals(altBrett[y][x + 1])) {
                        horizontal = true;
                    } else if (y + 1 < neuBrett.length && !neuBrett[y + 1][x].equals(altBrett[y + 1][x])) {
                        horizontal = false;
                    }

                    if (horizontal) {
                        if (startX > 0 && neuBrett[y][startX - 1] != ' ') {
                            for (int i = startX - 1; i >= 0 && neuBrett[y][i] != ' '; i--) {
                                startX = i;
                            }
                        }
                        for (int i = startX; i < neuBrett[y].length && neuBrett[y][i] != ' '; i++) {
                            neuesWort += neuBrett[y][i];
                        }
                    } else {
                        if (startY > 0 && neuBrett[startY - 1][x] != ' ') {
                            for (int i = startY - 1; i >= 0 && neuBrett[i][x] != ' '; i--) {
                                startY = i;
                            }
                        }
                        for (int i = startY; i < neuBrett.length && neuBrett[i][x] != ' '; i++) {
                            neuesWort += neuBrett[i][x];
                        }
                    }
                    break outerLoop;
                }
            }
        }
//        if (!neuesWort.isEmpty()) {
//            return punktemultiplikator.calcWortPunkte(neuesWort, startX, startY, horizontal);
//        } else {
//            return 0;
//        }
        return neuesWort;
    }
}
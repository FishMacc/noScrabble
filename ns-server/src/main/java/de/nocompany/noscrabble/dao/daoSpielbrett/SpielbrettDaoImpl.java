package de.nocompany.noscrabble.dao.daoSpielbrett;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpielbrettDaoImpl implements SpielbrettDaoInterface {
    public static final String DATEINAME = "spielbrett.txt";

    @Override
    public void saveSpielbrett(Character[][] spielbrett) {
        try (PrintWriter out = new PrintWriter(DATEINAME)) {
            for (Character[] zeile : spielbrett) {
                for (Character zelle : zeile) {
                    out.print(zelle != null ? zelle : ' ');
                }
                out.println();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Character[][] getSpielbrett() {
        List<Character[]> spielbrettListe = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(DATEINAME))) {
            String zeile;
            while ((zeile = br.readLine()) != null) {
                Character[] zeilenArray = new Character[zeile.length()];
                for (int i = 0; i < zeile.length(); i++) {
                    char c = zeile.charAt(i);
                    zeilenArray[i] = c != ' ' ? c : ' ';
                }
                spielbrettListe.add(zeilenArray);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spielbrettListe.toArray(new Character[spielbrettListe.size()][]);
    }
}

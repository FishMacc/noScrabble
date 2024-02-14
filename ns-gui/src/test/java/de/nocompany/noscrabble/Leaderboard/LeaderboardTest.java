package de.nocompany.noscrabble.model.leaderboard;

import de.nocompany.noscrabble.model.spieler.Spieler;
import de.nocompany.noscrabble.model.spielsteinpool.SpielsteinPool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LeaderboardTest {

    private Leaderboard leaderboard;
    private SpielsteinPool pool = new SpielsteinPool();
    private Spieler spieler1, spieler2, spieler3;

    @BeforeEach
    void setUp() {
        spieler1 = new Spieler(pool);
        spieler1.setName("Spieler1");
        spieler1.updatePunkte(10);

        spieler2 = new Spieler(pool);
        spieler2.setName("Spieler2");
        spieler2.updatePunkte(20);

        spieler3 = new Spieler(pool);
        spieler3.setName("Spieler3");
        spieler3.updatePunkte(30);
        spieler3.setTurn(true);

        leaderboard = new Leaderboard(Arrays.asList(spieler1, spieler2, spieler3));
    }

    @Test
    void sortiereSpielerTest() {
        leaderboard.sortiereSpieler();

        assertEquals("Spieler3", leaderboard.spielerListe.get(0).getName());
        assertEquals("Spieler2", leaderboard.spielerListe.get(1).getName());
        assertEquals("Spieler1", leaderboard.spielerListe.get(2).getName());
    }

    @Test
    void whosTurnTest() {
        Spieler aktuellerSpieler = leaderboard.whosTurn();
        assertNotNull(aktuellerSpieler);
        assertEquals("Spieler3", aktuellerSpieler.getName());
    }

    @Test
    void leaderboardListTest() {
        List<String> erwartetesLeaderboard = Arrays.asList(
                "Spieler3\t30",
                "Spieler2\t20",
                "Spieler1\t10"
        );

        List<String> tatsaechlichesLeaderboard = leaderboard.leaderboardList();

        assertEquals(erwartetesLeaderboard.size(), tatsaechlichesLeaderboard.size());

        for (int i = 0; i < erwartetesLeaderboard.size(); i++) {
            assertEquals(erwartetesLeaderboard.get(i), tatsaechlichesLeaderboard.get(i));
        }
    }
}

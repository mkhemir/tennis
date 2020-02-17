import fr.nexity.tennis.Joueur;
import fr.nexity.tennis.TennisMatch;
import org.junit.Test;


import static junit.framework.TestCase.*;

public class TennisSetTest {

    @Test(expected = NullPointerException.class)
    public void playWithoutFirstPlayer() {
        TennisMatch.getMatch(null, new Joueur("Marwen"));
    }

    @Test(expected = NullPointerException.class)
    public void playWithoutSecondPlayer() {
        TennisMatch.getMatch(new Joueur("Rafael"), null);
    }


    @Test
    public void incrementFirstPlayerSet() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        joueur1.incrementScoreSet();
        assertEquals(0, game.getPlayerTwo().getScoreSet());
        assertEquals(1, game.getPlayerOne().getScoreSet());
    }

    @Test
    public void incrementSecondPlayer() {

        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        joueur2.incrementScoreSet();
        assertEquals(0, game.getPlayerOne().getScoreSet());
        assertEquals(1, game.getPlayerTwo().getScoreSet());
    }

    @Test
    public void winningFirstPlayerWithoutTieBreak() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        game.forceScoreSet(5 , 3); // 5 - 3
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 15 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 30 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 40 - 0
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // game won

        assertEquals("Marwen", game.getWinner().getName()); // 6 - 3
    }

    @Test
    public void winningSecondPlayerWithoutTieBreak() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        game.forceScoreSet(4 , 5); // 4 - 5
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 15
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 30
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 40
        assertFalse(game.incrementPlayerScore(joueur2 , joueur1)); // round won
        assertEquals("Rafael", game.getWinner().getName()); // 4 - 6
    }

    @Test
    public void winningFirstPlayerWithTieBreak() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        game.forceScoreSet(5 , 5); // 5 - 5
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 15 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 30 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 40 - 0
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // round won
        assertEquals(null, game.getWinner()); // 6 - 5

        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 15
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 30
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 40
        assertFalse(game.incrementPlayerScore(joueur2 , joueur1)); // round won
        assertEquals(null, game.getWinner()); // 6 - 6

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 15 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 30 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 40 - 0
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // round won
        assertEquals(null, game.getWinner()); // 7 - 6

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 15 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 30 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 40 - 0
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // round won
        assertEquals("Marwen", game.getWinner().getName()); // 8 - 6
    }
}


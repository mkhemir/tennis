import fr.nexity.tennis.Joueur;
import fr.nexity.tennis.TennisMatch;
import org.junit.Test;


import static junit.framework.TestCase.*;

public class TennisMatchTest {

    @Test(expected = NullPointerException.class)
    public void playWithoutFirstPlayer() {
        TennisMatch.getMatch(null, new Joueur("Marwen"));
    }

    @Test(expected = NullPointerException.class)
    public void playWithoutSecondPlayer() {
        TennisMatch.getMatch(new Joueur("Rafael"), null);
    }


    @Test
    public void incrementFirstPlayer() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        game.incrementPlayerScore(joueur1 , joueur2);
        assertEquals(0, game.getPlayerTwo().getScore());
        assertEquals(15, game.getPlayerOne().getScore());
    }

    @Test
    public void incrementSecondPlayer() {

        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        game.incrementPlayerScore(joueur2 , joueur1);
        assertEquals(0, game.getPlayerOne().getScore());
        assertEquals(15, game.getPlayerTwo().getScore());
    }

    @Test
    public void winningFirstPlayer() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 15 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 30 - 0
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // 40 - 0
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // game won

        assertEquals(1, game.getPlayerOne().getScoreSet()); // 1 - 0
    }

    @Test
    public void winningSecondPlayer() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 15
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 30
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 0 - 40
        assertFalse(game.incrementPlayerScore(joueur2 , joueur1)); // game won
        assertEquals(1, game.getPlayerTwo().getScoreSet()); // 0 - 1
    }

    @Test
    public void deuceToWinFirstPlayer() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 15 - 15
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 30 - 30
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 40 -40
        assertTrue(game.isDeuce());

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // player one has advantage
        assertTrue(game.getPlayerOne().hasAdvantage());
        assertFalse(game.getPlayerTwo().hasAdvantage());

        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // return to deuce
        assertTrue(game.isDeuce());

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // player one has advantage
        assertFalse(game.incrementPlayerScore(joueur1 , joueur2)); // player one has won the game

        assertEquals(1, game.getPlayerOne().getScoreSet()); // 1 - 0
    }

    @Test
    public void deuceToWinSecondPlayer() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 15 - 15
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 30 - 30
        assertTrue(game.incrementPlayerScore(joueur1 , joueur2));
        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // 40 -40
        assertTrue(game.isDeuce());

        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // player two has advantage
        assertTrue(game.getPlayerTwo().hasAdvantage());
        assertFalse(game.getPlayerOne().hasAdvantage());

        assertTrue(game.incrementPlayerScore(joueur1 , joueur2)); // return to deuce
        assertTrue(game.isDeuce());

        assertTrue(game.incrementPlayerScore(joueur2 , joueur1)); // player two has advantage
        assertTrue(game.getPlayerTwo().hasAdvantage());
        assertFalse(game.incrementPlayerScore(joueur2 , joueur1)); // player two has won the game

        assertEquals(1, game.getPlayerTwo().getScoreSet()); // 1 - 0
    }

    @Test
    public void isDeuce() {
        Joueur joueur1 = new Joueur("Marwen");
        Joueur joueur2 = new Joueur("Rafael");
        TennisMatch game = TennisMatch.getMatch(joueur1, joueur2);
        assertFalse(game.isDeuce()); // 0 - 0
        game.incrementPlayerScore(joueur1 , joueur2);
        assertFalse(game.isDeuce()); // 15 - 0
        game.incrementPlayerScore(joueur2 , joueur1);
        game.incrementPlayerScore(joueur2 , joueur1);
        assertFalse(game.isDeuce()); // 15 - 30

        game.incrementPlayerScore(joueur1 , joueur2);
        game.incrementPlayerScore(joueur1 , joueur2);
        game.incrementPlayerScore(joueur2 , joueur1);
        assertTrue(game.isDeuce()); // 40 - 40

        assertEquals(0, game.getPlayerOne().getScoreSet()); // 0 - 0
        assertEquals(0, game.getPlayerTwo().getScoreSet()); // 0 - 0
    }

}

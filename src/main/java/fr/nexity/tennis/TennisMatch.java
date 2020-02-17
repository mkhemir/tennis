package fr.nexity.tennis;

import java.util.Objects;

public class TennisMatch {

    private static final int MAX_SCORE = 40;
    private static final int MAX_SCORE_SET = 6;
    private final Joueur firstPlayer;
    private final Joueur secondPlayer;
    private boolean isFinished;
    private boolean tieBreak;
    private Joueur winner;


    private TennisMatch(Joueur firstPlayer, Joueur secondPlayer) {
        this.firstPlayer = Objects.requireNonNull(firstPlayer);
        this.secondPlayer = Objects.requireNonNull(secondPlayer);

    }

    /**
     * Crée un nouveau match entre deux joueurs.
     *
     * @param firstPlayer  le premier joueur.
     * @param secondPlayer le deuxieme joueur.
     * @return Nouveau Match;
     */
    public static TennisMatch getMatch(Joueur firstPlayer, Joueur secondPlayer) {
        return new TennisMatch(firstPlayer, secondPlayer);
    }

    /**
     * Incrémenter le score d'une round. Si la round est fini alors on incrémente le Set.
     *
     * @return true si le score est incrémenté, false si le match est fini ou la loi Deuce est activée
     */
    public boolean incrementPlayerScore(Joueur toIncrement, Joueur opponent) {
        if (isFinished()) {
            return false;
        }
        if (opponent.hasAdvantage()) {
            opponent.setAdvantage(false);
            return true;
        }
        if (isDeuce()) {
            toIncrement.setAdvantage(true);
            opponent.setAdvantage(false);
            return true;
        }
        if (toIncrement.getScore() == MAX_SCORE || toIncrement.hasAdvantage()) {
            toIncrement.resetScore();
            opponent.resetScore(); // round fini, on réinitialise le score pour une nouvelle ronde.
            incrementPlayerSet(toIncrement, opponent); // round fini, on incrémente le set.
            return false;
        }
        return toIncrement.incrementScore();
    }

    /**
     * Incrementer le score d'un set.
     *
     * @return true si le score est incrémenté, false si le match est fini.
     */
    private boolean incrementPlayerSet(Joueur firstPlayer, Joueur secondPlayer) {
        if (isFinished()) {
            return false; // le match est déjà fini on ne peut plus jouer.
        }
        if ((firstPlayer.getScoreSet() == MAX_SCORE_SET - 1) && (firstPlayer.getScoreSet() >= secondPlayer.getScoreSet() + 1)) {
            firstPlayer.incrementScoreSet();
            this.isFinished = true;
            setWinner(firstPlayer);
            return false;
        }
        if (tieBreak && (firstPlayer.getScoreSet() == secondPlayer.getScoreSet() + 1)) {
            this.isFinished = true;
            setWinner(firstPlayer);
            return false;
        }
        if (firstPlayer.getScoreSet() == MAX_SCORE_SET - 1 || secondPlayer.getScoreSet() == MAX_SCORE_SET) {
            tieBreak = true;
            return firstPlayer.incrementScoreSet();
        }

        return firstPlayer.incrementScoreSet();
    }

    /**
     * @return true si le match est fini, false sinon.
     */

    public boolean isFinished() {
        return this.isFinished;
    }

    /**
     * @return true si égalité, false sinon.
     */
    public boolean isDeuce() {
        if (firstPlayer.hasAdvantage() || secondPlayer.hasAdvantage()) {
            return false;
        }
        return (firstPlayer.getScore() == MAX_SCORE && secondPlayer.getScore() == MAX_SCORE);
    }

    /**
     * réinialiser le score d'une ronde.
     */
    void reset() {
        firstPlayer.resetScore();
        secondPlayer.resetScore();
    }

    /**
     * setWinner.
     */
    private void setWinner(Joueur winner) {
        this.winner = winner;
    }

    /**
     * @return winner.
     */
    public Joueur getWinner() {
        return this.winner;
    }

    /**
     * @return firstPlayer.
     */
    public Joueur getPlayerOne() {
        return this.firstPlayer;
    }

    /**
     * @return secondPlayer.
     */
    public Joueur getPlayerTwo() {
        return this.secondPlayer;
    }

    /**
     * set un score.
     */
    public void forceScoreSet(int score1, int score2) {
        this.firstPlayer.setScoreSet(score1);
        this.secondPlayer.setScoreSet(score2);
    }
}

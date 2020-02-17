package fr.nexity.tennis;


public class TennisSetScore implements Score {

    private final static int MAX_SCORE = 7;

    private int score;

    /**
     * incrémente le score d'un set.
     *
     * @return true si le score est incrémenté, false sinon
     */
    public boolean increment() {
        if (score >= MAX_SCORE) {
            return false;
        }
        score++;
        return true;
    }

    /**
     * @return le score courant.
     */
    public int currentScore() {
        return score;
    }

    public void setScore(int score) {
        if (score < MAX_SCORE) {
            this.score = score;
        }
    }

    public void reset() {
        throw new IllegalStateException("On ne peut pas réinitialiser le score d'un set");
    }
}

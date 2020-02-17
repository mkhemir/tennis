package fr.nexity.tennis;


public class TennisRoundScore implements Score {

    private int score;

    public TennisRoundScore() {
        this.score = 0;
    }

    public TennisRoundScore(int score) {
        if (score != 0 && score != 15 && score != 30 && score != 40) {
            throw new IllegalArgumentException(String.format("%d ce n'est pas un score de Tennis!", score));
        }
        this.score = score;
    }

    /**
     * incrémente le score d'une round
     *
     * @return true si le score est incrémenté, false sinon
     */
    public boolean increment() {
        if (score >= 40) {
            return false;
        }
        if (score == 0 || score == 15) {
            score += 15;
        } else if (score == 30) {
            score = 40;
        }
        return true;
    }

    /**
     * @return le score courant.
     */
    public int currentScore() {
        return score;
    }

    public void reset() {
        score = 0;
    }

    public void setScore(int score) {
        this.score = score;
    }
}

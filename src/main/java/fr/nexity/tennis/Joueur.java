package fr.nexity.tennis;


public class Joueur {

    private final String name;
    private final Score scoreRound;
    private final Score scoreSet;
    private boolean hasAdvantage;

    public Joueur(String name) {
        this.name = name;
        this.scoreRound = new TennisRoundScore();
        this.scoreSet = new TennisSetScore();
    }

    /**
     * incrémente le score d'une round
     *
     * @return true si le score est incrémenté, false sinon
     */
    public boolean incrementScore() {
        return scoreRound.increment();
    }

    /**
     * incrémente le score d'un set
     *
     * @return true si le score est incrémenté, false sinon
     */
    public boolean incrementScoreSet() {
        return scoreSet.increment();
    }

    /**
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return currentScore.
     */
    public int getScore() {
        return scoreRound.currentScore();
    }

    public int getScoreSet() {
        return scoreSet.currentScore();
    }

    public void setScoreSet(int score) {
        scoreSet.setScore(score);
    }

    /**
     * @return hasAdvantage.
     */
    public boolean hasAdvantage() {
        return hasAdvantage;
    }

    void setAdvantage(boolean advantage) {
        hasAdvantage = advantage;
    }

    void resetScore() {
        scoreRound.reset();
    }
}

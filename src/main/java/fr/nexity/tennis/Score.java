package fr.nexity.tennis;

public interface Score {
    public boolean increment();

    int currentScore();

    public void reset();

    public void setScore(int score);
}

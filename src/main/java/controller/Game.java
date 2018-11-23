package controller;

import logic.brick.Brick;
import logic.level.EmptyLevel;
import logic.level.Level;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author Juan-Pablo Silva
 * @author José Astorga
 */
public class Game implements Observer {
    private Level level;
    private int balls;
    private int points;

    public Game(int balls) {
        this.level = new EmptyLevel();
        this.balls = balls;
        this.points = 0;
    }

    /**
     * This method is just an example. Change it or delete it at wish.
     * <p>
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return false;
    }

    public void goNextLevel() {
    }

    public boolean isGameOver() {
        return balls==0 || this.winner();
    }

    public void dropBall() {
        if(balls>0)
            balls-=1;
    }

    public int getBalls() {
        return balls;
    }

    public int getCurrentPoints() {
        return points;
    }

    public Level getCurrentLevel() {
        return level;
    }

    public List<Brick> getBricks() {
        return level.getBricks();
    }

    public boolean hasNextLevel() {
        return level.hasNextLevel();
    }

    public int numberOfBricks() {
        return this.level.getNumberOfBricks();
    }

    public boolean hasCurrentLevel() {
        return level.isPlayableLevel();
    }

    public String getLevelName() {
        return level.getName();
    }

    public void setCurrentLevel(Level level) {
        this.level = level;
    }

    public void addPlayingLevel(Level level) {
        this.level.addPlayingLevel(level);
    }

    public int getLevelPoints() {
        return level.getPoints();
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
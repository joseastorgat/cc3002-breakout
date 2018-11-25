package controller;

import logic.level.NullLevel;
import logic.level.Level;
import visitor.CheckLevelOver;
import visitor.Visitor;

import java.util.Observable;
import java.util.Observer;

/**
 * Game logic controller class.
 *
 * @author José Astorga
 */
public class Game implements Observer {
    private Level level;
    private int balls;
    private int points;
    private boolean won;

    /**
     * Game Constructor
     *
     * @param initBalls number of initial balls
     */
    public Game(int initBalls) {
        level = new NullLevel();
        balls = initBalls;
        points = 0;
        won = false;
    }

    /**
     *
     * Checks whether the game has a winner or not
     *
     * @return true if the game has a winner, false otherwise
     */
    public boolean winner() {
        return won;
    }

    /**
     * Checks whether the game is over or not. A game is over when the number of available balls are 0 or the player won the game.
     *
     * @return true if the game is over, false otherwise
     **/
    public boolean isGameOver() {
        return balls==0 || this.winner();
    }

    /**
     * Reduces the count of available balls.
     */
    public void dropBall() {
        if(!this.isGameOver()) {
            balls -= 1;
        }
    }
    /**
     * Increases the count of available balls.
     **/
    public void addBall() {
        if(!this.isGameOver()) {
            balls += 1;
        }
    }

    /**
     * Gets the current number of available balls to play.
     *
     * @return number of the available balls
     **/
    public int getBalls() {
        return balls;
    }

    /**
     * Increases the accumulated points
     *
     * @param newPoints amount of points to increase
     */
    public void updatePoints(int newPoints){
        points+=newPoints;
    }

    /**
     * Gets the accumulated points through all levels and current {@link Level}.
     *
     * @return Current points of the Game
     */
    public int getCurrentPoints() {
        return points;
    }

    /**
     * Gets the current {@link Level}.
     *
     * @return the current level
     * @see Level
     */
    public Level getCurrentLevel() {
        return level;
    }

    /**
     * Sets a {@link Level} as the current playing level.
     *
     * @param lvl the level to be used as the current level
     * @see Level
     */
    public void setCurrentLevel(Level lvl) {
        level = lvl;
        level.subscribe(this);
    }

    /**
     * Pass to the next level of the current {@link Level}. Ignores all conditions and skip to the next level.
     **/
    public void goNextLevel() {

        setCurrentLevel(level.getNextLevel());

    }

    /**
     * Adds a level to the list of {@link Level} to play. This adds the level in the last position of the list.
     * @param lvl level to be added
     */
    public void addPlayingLevel(Level lvl) {
        level.addPlayingLevel(lvl);
    }

    /**
     * Update method, called when something change in observed {@link Level}
     * <br>
     * Update points and ball number then check if level is over.
     *
     * @param o Observable Object, Level observed.
     * @param arg Args of update
     */
    @Override
    public void update(Observable o, Object arg) {
        if( arg instanceof Visitor){
            this.accept((Visitor) arg);
        }

        CheckLevelOver cfl = new CheckLevelOver();
        level.accept(cfl);

        if(cfl.getResult()){
            if(!level.hasNextLevel()) {
                won = true;
            }
            goNextLevel();

        }
    }

    /**
     * Accept a visit from a {@link Visitor}
     *
     * @param v Visitor that visit
     */
    public void accept(Visitor v){
        v.visitGame(this);
    }
}
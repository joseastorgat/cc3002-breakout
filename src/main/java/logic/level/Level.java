package logic.level;

import logic.brick.Brick;
import visitor.Visitor;

import java.util.List;
import java.util.Observer;

/**
 * Interface that represents the basics of a level to be played on.
 *
 * @author Juan-Pablo Silva
 * @author José Astorga
 */
public interface Level {
    /**
     * Gets the level's name. Each level must have a name.
     *
     * @return the table's name
     */
    String getName();

    /**
     * Gets the number of {@link Brick} in the level.
     *
     * @return the number of Bricks in the level
     */
    int getNumberOfBricks();

    /**
     * Gets the {@link List} of {@link Brick}s in the level.
     *
     * @return the bricks in the level
     */
    List<Brick> getBricks();

    /**
     * Gets the next level of a level object. Each level have a reference to the next level to play.
     *
     * @return the next level
     */
    Level getNextLevel();

    /**
     * Gets whether the level is playable or not.
     *
     * @return true if the level is playable, false otherwise
     */
    boolean isPlayableLevel();

    /**
     * Whether the level's next level is playable or not.
     *
     * @return true if the level's next level is playable, false otherwise
     */
    boolean hasNextLevel();

    /**
     * Gets the total number of points obtainable in level.
     *
     * @return the number of points in the current level
     */
    int getPoints();

    /**
     * Adds a level to the list of levels. This adds the level in the last position of the list.
     *
     * @param level the level to be added
     */
    Level addPlayingLevel(Level level);

    /**
     * Sets the reference for the next level of a level object.
     *
     * @param level the next level of a level object
     */
    void setNextLevel(Level level);



    /**
     * Method to add points to a Lvl
     *
     * @param points points to be added
     */
    void addPoints(int points);

    /**
     * Method to get current points of the level
     *
     * @return currentPoints obtained in the level
     */

    int getCurrentPoints();


    /**
     * Accept the visit of a {@link Visitor}
     * @param visitor Visitor visiting
     */
    void accept(Visitor visitor);


    /**
     * Deal with {@link Observer} trying to observe level
     * @param observer Observer trying to observe
     */
    void subscribe(Observer observer);
}

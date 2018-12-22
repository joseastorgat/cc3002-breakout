package logic.brick;

import visitor.Visitor;

import java.util.Observer;

/**
 * Interface that represents a brick object.
 * <p>
 * All bricks should implement this interface.
 *
 * @author Juan-Pablo Silva
 * @author José Astorga
 */
public interface Brick {
    /**
     * Defines that a brick has been hit.
     * Implementations should consider the events that a hit to a brick can trigger.
     */
    void hit();

    /**
     * Gets whether the brick object is destroyed or not.
     *
     * @return true if the brick is destroyed, false otherwise
     */
    boolean isDestroyed();

    /**
     * Gets the points corresponding to the destroying of a brick object.
     *
     * @return the associated points of a brick object
     */
    int getScore();

    /**
     * Gets the remaining hits the brick has to receive before being destroyed.
     *
     * @return the remaining hits to destroy de brick
     */
    int remainingHits();

    /**
     * Add an Observer
     *
     * @see Observer
     * @param o Observer
     */
    void addObserver(Observer o);

    /**
     * Method to check if a brick is a wooden brick
     *
     * @return true if the brick is a {@link WoodenBrick}, false otherwise
     */
    boolean isWoodenBrick();

    /**
     * Method to check if a brick is a glass brick
     *
     * @return true if the brick is a {@link GlassBrick}, false otherwise
     */

    boolean isGlassBrick();

    /**
     * Method to check if a brick is a metal brick
     *
     * @return true if the brick is a {@link MetalBrick}, false otherwise
     */
    boolean isMetalBrick();

}

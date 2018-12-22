package visitor;

import controller.Game;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.NullLevel;
import logic.level.PlayableLevel;


/**
 * Class that represents a Visitor object
 *
 * @author José Astorga
 */
public abstract class Visitor {
    /**
     * Visits the bricks of a Level
     *
     * @param level {@link PlayableLevel} to visit
     */
    public void visitPlayableLevel(PlayableLevel level) {}


    /**
     * Visit a Game
     * @param game {@link Game} to visit
     */
    public void visitGame(Game game){}
}

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
     * Visits the bricks of a Level
     *
     * @param level {@link NullLevel} to visit
     */
    public void visitEmptyLevel(NullLevel level) {}


    /**
     * Visits a GlassBrick
     *
     * @param glassBrick {@link GlassBrick} to visit
     */
    public void visitGlassBrick(GlassBrick glassBrick) { }

    /**
     * Visits a WoodenBrick
     *
     * @param woodenBrick {@link WoodenBrick} to visit
     */
    public void visitWoodenBrick(WoodenBrick woodenBrick) { }

    /**
     * Visits a MetalBrick
     *
     * @param metalBrick {@link MetalBrick} to visit
     */
    public void visitMetalBrick(MetalBrick metalBrick) { }

    /**
     * Visit a Game
     * @param game {@link Game} to visit
     */
    public void visitGame(Game game){}
}

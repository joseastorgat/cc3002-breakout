package logic.visitor;

import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.EmptyLevel;
import logic.level.PlayableLevel;

import java.awt.dnd.DropTarget;

/**
 * Class that represents a Visitor object
 *
 * @author José Astorga
 */
public abstract class Visitor {

    /**
     * Visits the bricks of a Level
     *
     * @param level PlayableLevel to visit
     */
    public void visitPlayableLevel(PlayableLevel level) {}


    /**
     * Visits the bricks of a Level
     *
     * @param level EmptyLevel to visit
     */
    public void visitEmptyLevel(EmptyLevel level) {}


    /**
     * Visits a GlassBrick
     *
     * @param glassBrick GlassBrick to visit
     */
    public void visitGlassBrick(GlassBrick glassBrick) { }

    /**
     * Visits a WoodenBrick
     *
     * @param woodenBrick WoodenBrick to visit
     */
    public void visitWoodenBrick(WoodenBrick woodenBrick) { }

    /**
     * Visits a MetalBrick
     *
     * @param metalBrick MetalBrick to visit
     */
    public void visitMetalBrick(MetalBrick metalBrick) { }

}

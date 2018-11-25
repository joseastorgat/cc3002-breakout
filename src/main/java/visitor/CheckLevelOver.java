package visitor;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.WoodenBrick;
import logic.level.PlayableLevel;

/**
 * CheckLevelOver is a {@link Visitor} to check if a level is over
 *
 * @author José Astorga
 */
public class CheckLevelOver extends Visitor {
    private boolean result;

    /**
     * CheckLevelOver Constructor
     */
    public CheckLevelOver(){
        result = false;
    }

    @Override
    public void visitPlayableLevel(PlayableLevel level) {
        result = true;
        for(Brick brick : level.getBricks()){
            brick.accept(this);
            if(!result) {
                break;
            }
        }
    }

    /**
     * Check if a {@link Brick} is destroyed
     * @param brick Brick to be checked
     */
    private void checkBrick(Brick brick){
        if(!brick.isDestroyed()){
            result = false;
        }
    }

    @Override
    public void visitWoodenBrick(WoodenBrick brick){
        checkBrick(brick);
    }

    @Override
    public void visitGlassBrick(GlassBrick brick){
        checkBrick(brick);
    }

    /**
     * Gets the result, true if level visited is over, false otherwise.
     */
    public boolean getResult(){
        return result;
    }

}

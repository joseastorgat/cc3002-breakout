package visitor;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.WoodenBrick;
import logic.level.PlayableLevel;

public class CheckFinishLevel extends Visitor {
    private boolean result;
    public CheckFinishLevel(){
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

    public boolean checkResult(){
        return result;
    }

}

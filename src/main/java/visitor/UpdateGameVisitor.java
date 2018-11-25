package visitor;

import controller.Game;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
/**
 * UpdateGameVisitor is a {@link Visitor} to update Game Points and Balls
 *
 * @author José Astorga
 */
public class UpdateGameVisitor extends Visitor {
    private int points;
    private boolean upgradeBalls;

    /**
     * UpdateGameVisitor Constructor
     */
    public UpdateGameVisitor(){
        points = 0;
        upgradeBalls= false;
    }

    @Override
    public void visitGame(Game game) {
        if(points!=0){
            game.updatePoints(points);
        }
        if(upgradeBalls){
            game.addBall();
        }
    }

    @Override
    public void visitGlassBrick(GlassBrick brick){
        points+=brick.getScore();
    }

    @Override
    public void visitWoodenBrick(WoodenBrick brick){
        points+=brick.getScore();
    }

    @Override
    public void visitMetalBrick(MetalBrick brick){
        upgradeBalls=true;
    }
}

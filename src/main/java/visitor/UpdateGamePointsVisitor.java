package visitor;

import controller.Game;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.PlayableLevel;

/**
 * UpdateGamePointsVisitor is a {@link Visitor} to update Game Points and Balls
 *
 * @author José Astorga
 */
public class UpdateGamePointsVisitor extends Visitor {
    private int points;

    /**
     * UpdateGamePointsVisitor Constructor
     */
    public UpdateGamePointsVisitor(int newPoints){
        points = newPoints;
    }

    @Override
    public void visitGame(Game game) {
        game.updatePoints(points);

    }

    @Override
    public void visitPlayableLevel(PlayableLevel level){
        level.addPoints(points);
    }

}

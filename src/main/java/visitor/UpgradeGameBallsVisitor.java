package visitor;

import controller.Game;
/**
 * UpgradeGameBallsVisitor is a {@link Visitor} to add a Ball to a game
 *
 * @author José Astorga
 */
public class UpgradeGameBallsVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.addBall();
    }
}

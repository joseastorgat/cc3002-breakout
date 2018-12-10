package visitor;

import controller.Game;

public class UpgradeGameBallsVisitor extends Visitor {
    @Override
    public void visitGame(Game game) {
        game.addBall();
    }
}

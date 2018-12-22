package visitor;

import controller.Game;

/**
 * ChangeLevelVisitor is a {@link Visitor} to notify a Game that a level is over and must go to the next one
 *
 * @author José Astorga
 */
public class ChangeLevelVisitor extends Visitor {

    /**
     * ChangeLevelVisitor Constructor
     */
    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }
}

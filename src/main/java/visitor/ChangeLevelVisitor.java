package visitor;

import controller.Game;

/**
 * ChangeLevelVisitor is a {@link Visitor} to check if a level is over
 *
 * @author José Astorga
 */
public class ChangeLevelVisitor extends Visitor {
    private boolean result;

    /**
     * ChangeLevelVisitor Constructor
     */
    @Override
    public void visitGame(Game game){
        game.goNextLevel();
    }
}

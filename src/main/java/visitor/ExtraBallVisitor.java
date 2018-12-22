package visitor;

import controller.Game;
/**
 * ExtraBallVisitor is a {@link Visitor} to add an extra ball to a game
 *
 * @author José Astorga
 */
public class ExtraBallVisitor extends Visitor{
    public void visitGame(Game game) {
        game.upgradeExtraBalls();
    }
}

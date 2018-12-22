package logic.bonus;
import visitor.ExtraBallVisitor;

/**
 * ExtraPointsBonus is a {@link Bonus}.
 * <br>
 * When trigger add an extra ball to the game
 *
 * @author José Astorga
 */
public class ExtraBallBonus extends AbstractBonus{

    /**
     * Create an {@link ExtraBallVisitor} and deliver it to observers
     */
    @Override
    public void triggerBonus() {
        setChanged();
        notifyObservers(new ExtraBallVisitor());
    }
    @Override
    public boolean isExtraBallBonus(){
        return true;
    }


}

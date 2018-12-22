package logic.bonus;

import visitor.UpdateGamePointsVisitor;

import java.util.Random;


/**
 * ExtraPointsBonus is a {@link Bonus}.
 * <br>
 * When trigger add a random number of points (between 5000 and 50000) to the current points.
 *
 * @author José Astorga
 */

public class ExtraPointsBonus extends AbstractBonus{
    private Random random;

    /**
     * ExtraPointsBonus Constructor
     */
    public ExtraPointsBonus(long seed){
        random = new Random(seed);
    }


    /**
     * Create an {@link UpdateGamePointsVisitor} random number of points (between 5000 and 50000) and deliver it to observers
     */
    @Override
    public void triggerBonus() {
        setChanged();
        notifyObservers( new UpdateGamePointsVisitor(5000*(random.nextInt(9) + 1 )));
    }


    @Override
    public boolean isExtraPointsBonus(){
        return true;
    }
}

package logic.bonus;

import java.util.Observable;
import java.util.Observer;

/**
 * Abstract Bonus is an Abstract Class that implements {@link Bonus}
 *
 * @author Jose Astorga
 */
public abstract class AbstractBonus extends Observable implements Bonus{

    @Override
    public void triggerBonus(){};

    @Override
    public void addObserver(Observer o){
        super.addObserver(o);
    }

    public boolean isExtraBallBonus(){
        return false;
    }
    public boolean isExtraPointsBonus(){
        return false;
    }
    public boolean isNullBonus(){
        return false;
    }
}

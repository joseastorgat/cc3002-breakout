package logic.bonus;


import java.util.Observer;

/**
 * Abstract class for Bonus representation
 *
 * @author José Astorga
**/
public interface Bonus{

    /**
     * Method to trigger the bonus
     */
    void triggerBonus();

    /**
     * Method to addObservers to Bonus
     */
    void addObserver(Observer o);

    /**
     * Method
     *
     * @return true if the Bonus is a {@link ExtraBallBonus}, false otherwise
     */
    boolean isExtraBallBonus();

    /**
     *
     * @return true if the Bonus is a {@link ExtraPointsBonus}, false otherwise
     */
    boolean isExtraPointsBonus();

    /**
     *
     * @return true if the Bonus is a {@link NullBonus}, false otherwise
     */
    boolean isNullBonus();

}

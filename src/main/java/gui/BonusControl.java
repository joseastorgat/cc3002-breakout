package gui;

import logic.bonus.Bonus;


import com.almasb.fxgl.entity.component.Component;

/**
 * BonusControl Class
 * <br>
 *     is a {@link Component} to Control a Bonus
 *
 * @author José Astorga
 */
public class BonusControl extends Component {
    private Bonus bonus;

    /**
     * BonusControl Constructor
     * @param b {@link Bonus} to represent
     */
    public BonusControl(Bonus b){
        bonus = b;
    }

    /**
     * Method to trigger the {@link Bonus}
     */
    public void trigger(){
        bonus.triggerBonus();
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateY(5);
    }
}



package gui;

import com.almasb.fxgl.app.FXGL;
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
    private String sound;

    /**
     * BonusControl Constructor
     * @param b {@link Bonus} to represent
     */
    public BonusControl(Bonus b, String s){
        bonus = b;
        sound = s;
    }

    /**
     * Method to trigger the {@link Bonus}
     */
    public void trigger(){
        bonus.triggerBonus();
        FXGL.getAudioPlayer().playSound(sound);
    }

    @Override
    public void onUpdate(double tpf) {
        entity.translateY(5);
    }
}



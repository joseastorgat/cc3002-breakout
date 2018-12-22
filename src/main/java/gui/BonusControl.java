package gui;

import logic.bonus.Bonus;


import com.almasb.fxgl.entity.component.Component;


public class BonusControl extends Component {
    private Bonus bonus;

    public BonusControl(Bonus b){
        bonus = b;
    }
    public void trigger(){
        bonus.triggerBonus();
    }

    public void onUpdate(double tpf) {
        entity.translateY(5);
    }
}



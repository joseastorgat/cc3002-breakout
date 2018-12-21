package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.app.FXGL;
import logic.brick.Brick;

public class BrickControl extends Component {
    private Brick brick;
    private String sound;
    public BrickControl(Brick newBrick, String s){
        brick = newBrick;
        sound = s;
    }
    public void hit(){
        brick.hit();
        FXGL.getAudioPlayer().playSound(sound);
    }

    public boolean isDestroyed(){
        return brick.isDestroyed();
    }
}

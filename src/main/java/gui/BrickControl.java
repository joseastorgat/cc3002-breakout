package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.app.FXGL;
import logic.brick.Brick;

/**
 * BrickControl Class
 * <br>
 *     is a {@link Component} to Control a Brick
 *
 * @author José Astorga
 */
public class BrickControl extends Component {
    private Brick brick;
    private String sound;

    /**
     * BrickControl Constructor
     * @param newBrick Brick to represent
     * @param s Sound to play when hit
     */
    public BrickControl(Brick newBrick, String s){
        brick = newBrick;
        sound = s;
    }

    /**
     * Hit the {@link Brick} represented
     */
    public void hit(){
        brick.hit();
        FXGL.getAudioPlayer().playSound(sound);
    }

    /**
     * Method to check if the {@link Brick} is destroyed
     * @return True if the brick is destroyed. False otherwise
     */
    public boolean isDestroyed(){
        return brick.isDestroyed();
    }
}

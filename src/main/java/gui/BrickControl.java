package gui;

import com.almasb.fxgl.entity.component.Component;
import logic.brick.Brick;

public class BrickControl extends Component {
    private Brick brick;
    public BrickControl(Brick newBrick){
        brick = newBrick;

    }

    public void hit(){
        brick.hit();
    }

    public boolean isDestroyed(){
        return brick.isDestroyed();
    }
}

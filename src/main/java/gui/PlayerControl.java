package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

public class PlayerControl extends Component {
    private int velocity=100;

    public void moveLeft(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(-velocity,0);
    }

    public void moveRight(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(velocity,0);
    }

    public void stop(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(0,0);
    }

}

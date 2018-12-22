package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

/**
 * PlayerControl Class
 * <br>
 *     is a {@link Component} to Control a Ball
 *
 * @author José Astorga
 */
public class PlayerControl extends Component {
    private int velocity=300;

    /**
     * Move the Player Entity to the left
     */
    public void moveLeft(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(-velocity,0);
    }

    /**
     * Move the Player Entity to the right
     */
    public void moveRight(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(velocity,0);
    }

    /**
     * Stop the Player Entity
     */
    public void stop(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(0,0);
    }

}

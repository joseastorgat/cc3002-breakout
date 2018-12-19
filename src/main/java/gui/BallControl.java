package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import java.util.Random;

public class BallControl extends Component {
    private int velocity=500;
    private Random random= new Random();

    public void shoot(){
        double ang = (random.nextInt(14) +  7) / 10.0;
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(velocity*Math.cos(ang), -velocity*Math.sin(ang));
    }
    public void stop(){
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(0, 0);
    }

    public void reset(){
        entity.setPosition(390, 680);
    }
}

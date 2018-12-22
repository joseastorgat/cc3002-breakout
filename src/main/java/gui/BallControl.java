package gui;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;

import java.util.Random;

/**
 * BallControl Class
 * <br>
 *     is a {@link Component} to Control a Ball
 *
 * @author José Astorga
 */
public class BallControl extends Component {
    private int velocity=500;
    private Random random= new Random();
    private PhysicsComponent physics;
    private boolean ballInGame;

    /**
     * Shoot the Ball in a semi-Random direction
     */
    public void shoot(){
        double ang = (random.nextInt(14) +  7) / 10.0;
        entity.getComponent(PhysicsComponent.class).setLinearVelocity(velocity*Math.cos(ang), -velocity*Math.sin(ang));
        ballInGame = true;
    }

    @Override
    public void onUpdate(double tpf) {
        if(!ballInGame){
            return;
        }
        double velx = physics.getVelocityX();
        double vely = physics.getVelocityY();
        if(vely<10 && vely>0){
            vely = 120;
        }
        else if(vely>-10 && vely<0){
            vely = -120;
        }
        double vel = Math.sqrt(velx*velx+vely*vely);
        if(vel>0){
            double factor = velocity/vel;
            entity.getComponent(PhysicsComponent.class).setLinearVelocity(velx*factor, vely*factor);
        }
    }
}

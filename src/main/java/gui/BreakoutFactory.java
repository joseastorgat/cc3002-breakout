package gui;


import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.RenderLayer;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import logic.bonus.Bonus;
import logic.brick.Brick;


/**
 *
 * BreakoutFactory is a Factory to BreakoutGame Entities
 *
 * @author José Astorga
 */
public final class BreakoutFactory {

    /**
     * Create a Player Entities
     * @param x Position in X axis
     * @param y Position in Y axis
     * @return player Entity created
     */
    static Entity newPlayer(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef()
                        .restitution(0f)
                        .density(100000f));

        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameType.PLAYER)
                .bbox(new HitBox("Player", BoundingShape.box(200, 30)))
                .viewFromNode(new Rectangle(200, 30, Color.BLUE))
                .with(physics, new CollidableComponent(true), new PlayerControl())
                .build();
    }

    /**
     * Create a Background with background image
     *
     * @return Backgroung Entity
     */
    static Entity newBackground() {
        return Entities.builder()
                .viewFromTexture("bg.png")
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

    /**
     * Create a new Ball Entity
     * @param x Ball Position in X axis
     * @param y Ball Position in Y axis
     * @return ball Entity created
     */
    static Entity newBall(double x, double y) {
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.DYNAMIC);
        physics.setFixtureDef(
                new FixtureDef()
                    .restitution(1f)
                    .density(0f)
                    .friction(0.2f));

        return Entities.builder()
                .at(x, y)
                .type(BreakoutGameType.BALL)
                .bbox(new HitBox("Ball", BoundingShape.circle(10)))
                .viewFromNode(new Circle(10, Color.LIGHTCORAL))
                .with(physics, new CollidableComponent(true), new BallControl())
                .build();
    }

    /**
     * Create the walls for the game
     * @return Wall created
     */
    static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(BreakoutGameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    /**
     * Create a UI entity
     * @return UI entity created
     */
    static Entity newInfoBar(){
        return Entities.builder()
                .at(0,0)
                .type(BreakoutGameType.WALL)
                .viewFromNodeWithBBox(new Rectangle(800,100, Color.GRAY))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .build();
    }

    /**
     * Generate a Brick Entity. Representation of {@link Brick} in BreakOut Game
     *
     * @param posx Brick Position in X axis
     * @param posy Brick Position in Y axis
     * @param brick {@link Brick} to represent in this Entity
     * @return Brick Entity
     */
    static Entity newBrick(int posx, int posy, Brick brick){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        String sound;
        String texture;
        if (brick.isGlassBrick()){
            texture = "glass.jpg";
            sound = "glassHit.wav";


        }
        else if (brick.isWoodenBrick()){
            texture = "wood.jpg";
            sound = "woodenHit.wav";
        }
        else{
            texture = "metal.png";
            sound = "metalHit.wav";
        }
        return Entities.builder()
                .at(posx, posy)
                .type(BreakoutGameType.BRICK)
                .bbox(new HitBox("Brick", BoundingShape.box(100, 40)))
                .viewFromTextureWithBBox(texture)
                .with(physics, new CollidableComponent(true), new BrickControl(brick, sound))
                .build();
    }

    /**
     * Create a Bonus Entity to represent a {@link Bonus}
     * @param x Bonus Entity position in X axis
     * @param y Bonus Entity position in Y axis
     * @param bonus {@link Bonus} to represent
     * @param img String with the name of the texture of the bonus
     * @return Bonus Entity
     */
     static Entity newBonus(double x, double y, Bonus bonus, String img) {
            return Entities.builder()
                    .at(x, y)
                    .type(BreakoutGameType.BONUS)
                    .bbox(new HitBox("Bonus", BoundingShape.circle(50)))
                    .viewFromTextureWithBBox(img)
                    .with(new CollidableComponent(true), new BonusControl(bonus))
                    .build();
    }

    /**
     * Create a Bonus Entity to representint a {@link logic.bonus.ExtraBallBonus}
     * @param x Bonus Entity position in X axis
     * @param y Bonus Entity position in Y axis
     * @param bonus {@link Bonus} to represent
     * @return Bonus Entity
     */

    static Entity newBallBonus(double x, double y, Bonus bonus){
            return newBonus(x, y, bonus, "watermelon.png");

        }
    /**
     * Create a Bonus Entity to representint a {@link logic.bonus.ExtraPointsBonus}
     * @param x Bonus Entity position in X axis
     * @param y Bonus Entity position in Y axis
     * @param bonus {@link Bonus} to represent
     * @return Bonus Entity
     */
    static Entity newPointsBonus(double x, double y, Bonus bonus){
        return newBonus(x, y, bonus, "orange.png");

    }



}
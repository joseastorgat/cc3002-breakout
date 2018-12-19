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
import logic.brick.Brick;

public final class BreakoutFactory {
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


    static Entity newBackground() {
        return Entities.builder()
                .viewFromNode(new Rectangle(800, 800, Color.BLACK))
                .renderLayer(RenderLayer.BACKGROUND)
                .build();
    }

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

    static Entity newWalls() {
        Entity walls = Entities.makeScreenBounds(100);
        walls.setType(BreakoutGameType.WALL);
        walls.addComponent(new CollidableComponent(true));
        return walls;
    }

    static Entity newInfoBar(){
        return Entities.builder()
                .at(0,0)
                .type(BreakoutGameType.WALL)
                .viewFromNodeWithBBox(new Rectangle(800,100, Color.GRAY))
                .with(new PhysicsComponent(), new CollidableComponent(true))
                .build();
    }

    static Entity newBrick(int posx, int posy, Brick brick){
        PhysicsComponent physics = new PhysicsComponent();
        physics.setBodyType(BodyType.STATIC);

        Color color;
        String sound;
        if (brick.isGlassBrick()){
            color = Color.BLUE;
            sound = "glassHit.wav";


        }
        else if (brick.isWoodenBrick()){
            color = Color.BURLYWOOD;
            sound = "woodenHit.wav";
        }
        else{
            color = Color.GREY;
            sound = "metalHit.wav";
        }
        return Entities.builder()
                .at(posx, posy)
                .type(BreakoutGameType.BRICK)
                .bbox(new HitBox("Brick", BoundingShape.box(100, 40)))
                .viewFromNode(new Rectangle(100, 40, color))
                .with(physics, new CollidableComponent(true), new BrickControl(brick, sound))
                .build();
    }

     static Entity newBonus(double x, double y) {


            return Entities.builder()
                    .at(x, y)
                    .type(BreakoutGameType.BONUS)
                    .bbox(new HitBox("Bonus", BoundingShape.box(30, 30)))
                    .viewFromNode(new Rectangle(30, 30, Color.GOLDENROD))
                    .with(new CollidableComponent(true))
                    .build();
        }

}
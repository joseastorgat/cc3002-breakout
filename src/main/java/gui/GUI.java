package gui;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.input.Input;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.settings.GameSettings;
import facade.HomeworkTwoFacade;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;
import java.util.Random;

import static gui.BreakoutFactory.*;
import facade.HomeworkTwoFacade;
import logic.brick.Brick;
import logic.level.Level;
import logic.level.PlayableLevel;

public class GUI extends GameApplication {

    private HomeworkTwoFacade game;
    private Entity player;
    private Random random;

    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Breakout Game");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        player = newPlayer(300, 700);
        Entity ball = newBall(390, 680);
        getGameWorld().addEntities(newBackground(), player, ball, newWalls(), newInfoBar());
        game = new HomeworkTwoFacade();
        random = new Random();
    }


    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).moveLeft();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).stop();
            }
        }, KeyCode.A);

        input.addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerControl.class).moveRight();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerControl.class).stop();
            }
        }, KeyCode.D);


        input.addAction(new UserAction("Add Level") {
            @Override
            protected void onActionBegin() {
                game.setCurrentLevel(game.newLevelWithBricksFull(String.format("Level %d", getGameState().getInt("total_levels")+1), (int) Math.ceil (15*random.nextDouble()), random.nextDouble(), random.nextDouble(), (int) System.currentTimeMillis()));
                createBricks();

            }
        }, KeyCode.N);
    }

    @Override
    protected void initPhysics() {
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            ball.removeFromWorld();
                        }
                    }
                });

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.BRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {
                        System.out.println("brick hitted");
                        brick.getComponent(BrickControl.class).hit();

                        if(brick.getComponent(BrickControl.class).isDestroyed()){
                            brick.removeFromWorld();
                        }
                        }
                    });
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("total_score", 0);
        vars.put("level_score", 0);
        vars.put("current_level", 0);
        vars.put("total_levels", 0);
        vars.put("balls_left", 0);
    }

    @Override
    protected void initUI() {
        int tx = 50;
        int ty = 30;
        int ty2 = 60;
        int tx2 = 300;
        int tx3 = 600;

        Text textTotalScore = getUIFactory().newText("", Color.YELLOW, 22);
        Text textCurrentScore = getUIFactory().newText("", Color.YELLOW, 22);
        Text textBallsLeft = getUIFactory().newText("", Color.YELLOW, 22);
        Text textTotalLevel = getUIFactory().newText("", Color.YELLOW, 22);
        Text textCurrentLevel = getUIFactory().newText("", Color.YELLOW, 22);

        textTotalScore.textProperty().bind(getGameState().intProperty("total_score").asString("TotalScore: %d"));
        textCurrentScore.textProperty().bind(getGameState().intProperty("level_score").asString("CurrentScore: %d"));
        textBallsLeft.textProperty().bind(getGameState().intProperty("balls_left").asString("Balls Left: %d"));
        textTotalLevel.textProperty().bind(getGameState().intProperty("total_levels").asString("Levels: %d"));
        textCurrentLevel.textProperty().bind(getGameState().intProperty("current_level").asString("Current Level: %d"));

        textTotalScore.setTranslateX(tx);
        textTotalScore.setTranslateY(ty);
        textCurrentScore.setTranslateX(tx);
        textCurrentScore.setTranslateY(ty2);
        textTotalLevel.setTranslateX(tx2);
        textTotalLevel.setTranslateY(ty2);
        textCurrentLevel.setTranslateX(tx2);
        textCurrentLevel.setTranslateY(ty);
        textBallsLeft.setTranslateX(tx3);
        textBallsLeft.setTranslateY(ty);
        getGameScene().addUINodes(textTotalScore, textCurrentScore, textBallsLeft, textTotalLevel, textCurrentLevel);
    }

    private void deleteElements() {
        GameWorld world = getGameWorld();
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.BRICK));
    }

    private void createBricks(){
        deleteElements();

        int leftMargin = 50;
        int supMargin  = 150;
        int maxRight = 700;

        int posx = leftMargin;
        int posy = supMargin;

        System.out.println(game.numberOfBricks());
        for(Brick brick: game.getBricks()){
            if(posx > maxRight){
                posx = leftMargin;
                posy+=50;
            }
            getGameWorld().addEntity(newBrick(posx, posy, brick));
            posx+=100;

        }

        Entity ball = newBall(390, 680);
        getGameWorld().addEntity(ball);
    }

    public static void main(String... args) {
        launch(args);
    }

}

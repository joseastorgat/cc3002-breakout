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

import java.util.*;

import static gui.BreakoutFactory.*;

import logic.brick.Brick;

public class GUI extends GameApplication implements Observer {

    private HomeworkTwoFacade facade;
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
        getGameWorld().addEntities(newBackground(), player,newBall(390, 680), newWalls(), newInfoBar());

        facade = new HomeworkTwoFacade();
        facade.getGame().addObserver(this);

        random = new Random();

        getGameState().<Integer>addListener("balls_left", (old, newScore) -> {
            if(facade.isGameOver()) {
                gameOver();
            }
        });
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
                if(getGameState().getInt("total_levels")==0){
                    facade.setCurrentLevel(facade.newLevelWithBricksFull(String.format("Level %d", getGameState().getInt("total_levels")+1), (int) Math.ceil (15*random.nextDouble()), random.nextDouble(), random.nextDouble(), (int) System.currentTimeMillis()));
                    generateLevel();
                }
                else{
                    facade.addPlayingLevel(facade.newLevelWithBricksFull(String.format("Level %d", getGameState().getInt("total_levels")+1), (int) Math.ceil (15*random.nextDouble()), random.nextDouble(), random.nextDouble(), (int) System.currentTimeMillis()));
                }
                getGameState().setValue("total_levels", getGameState().getInt("total_levels")+1);
            }
        }, KeyCode.N);

        input.addAction(new UserAction("Throw Ball") {
            @Override
            protected void onActionBegin() {
                getGameWorld().getEntitiesByType(BreakoutGameType.BALL)
                        .forEach(e -> e.getComponent(BallControl.class).shoot());
            }
        }, KeyCode.SPACE);

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
                            facade.dropBall();
                            ball.removeFromWorld();
                            if(!facade.isGameOver()){
                                getGameWorld().addEntity(newBall(player.getPosition().getX()+100, player.getPosition().getY()-20));

                            }
                        }
                    }
                });

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.BRICK) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity brick,
                                                   HitBox boxBall, HitBox boxBrick) {

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


    @Override
    protected void onUpdate(double tpf){

        getGameState().setValue("total_score", facade.getCurrentPoints());
        getGameState().setValue("level_score", facade.getCurrentLevel().getCurrentPoints());
        getGameState().setValue("balls_left", facade.getBallsLeft());

    }

    private void removeBricks() {
        GameWorld world = getGameWorld();
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.BRICK));
    }

    private void generateLevel(){
        removeBricks();

        getGameState().setValue("current_level", getGameState().getInt("current_level")+1);
        int leftMargin = 50;
        int supMargin  = 150;
        int maxRight = 700;

        int posx = leftMargin;
        int posy = supMargin;

        int n = facade.numberOfBricks();

        List<Brick> bricks = facade.getBricks();

//        for(Brick brick : facade.getBricks()){
        for(int i=0; i<n; i++){
            Brick brick = bricks.get(random.nextInt(n-i));
            bricks.remove(brick);
            if(posx > maxRight){
                posx = leftMargin;
                posy+=50;
            }
            getGameWorld().addEntity(newBrick(posx, posy, brick));
            posx+=100;

        }

    }


    private void gameOver() {
        getDisplay().showMessageBox("Game Over", this::exit);
    }

    public static void main(String... args) {
        launch(args);
    }

    public void update(Observable o, Object arg){
        if (!facade.isGameOver())
            this.generateLevel();
        else
            gameOver();
    }
}

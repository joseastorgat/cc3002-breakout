package gui;


import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;
import com.almasb.fxgl.gameplay.GameState;
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

import logic.bonus.ExtraBallBonus;
import logic.bonus.ExtraPointsBonus;
import logic.brick.Brick;

/**
 * GUI Class, GUI implementation to BreakOut Game
 *
 * @author José Astorga
 */
public class GUI extends GameApplication implements Observer {

    private HomeworkTwoFacade facade;
    private Entity player;
    private Random random;
    private boolean inGame;

    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(800);
        gameSettings.setHeight(800);
        gameSettings.setTitle("Breakout Game");
        gameSettings.setVersion("0.1");
    }

    @Override
    protected void initGame() {

        createWorld();
        getAudioPlayer().playMusic("BossTheme.mp3");
        getGameState().<Integer>addListener("balls_left", (old, newScore) -> {
            if(facade.isGameOver()) {
                gameOver();
            }
        });
        getGameState().<Integer>addListener("extra_balls", (old, newScore) -> {
            if(newScore!=0)
                extraBall();

        });
        getAssetLoader().cache();
    }

    /**
     * Create the initial world of Breakout Game
     * <br>
     * Create and set the Player, Walls and Facade
     */
    protected void createWorld(){
        inGame = false;
        player = newPlayer(300, 700);
        getGameWorld().addEntities(newBackground(), player, newWalls(), newInfoBar());

        facade = new HomeworkTwoFacade();
        facade.addGameObserver(this);

        facade.setExtraPointsBonus(new ExtraPointsBonus(System.currentTimeMillis()));
        facade.setExtraBallBonus(new ExtraBallBonus());

        random = new Random();

    }

    @Override
    protected void initInput() {
        Input input = getInput();

        input.addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                if(inGame)
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
                if(inGame)
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
                    if(!facade.isGameOver())
                        facade.addPlayingLevel(facade.newLevelWithBricksFull(String.format("Level %d", getGameState().getInt("total_levels")+1), (int) Math.ceil (5+random.nextInt(20)), random.nextDouble(), random.nextDouble(), (int) System.currentTimeMillis()));
                }
                getGameState().setValue("total_levels", getGameState().getInt("total_levels")+1);
            }
        }, KeyCode.N);

        input.addAction(new UserAction("Throw Ball") {
            @Override
            protected void onActionBegin() {
                if(!inGame)
                    getGameWorld().getEntitiesByType(BreakoutGameType.BALL)
                            .forEach(e -> e.getComponent(BallControl.class).shoot());
                    inGame = true;
            }
        }, KeyCode.SPACE);

    }

    @Override
    protected void initPhysics(){
        getPhysicsWorld().setGravity(0,0);
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BALL, BreakoutGameType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity ball, Entity wall,
                                                   HitBox boxBall, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {

                            ball.removeFromWorld();
                            if(getGameWorld().getEntitiesByType(BreakoutGameType.BALL).size()==0){
                                inGame = false;
                                facade.dropBall();
                                player.getComponent(PlayerControl.class).stop();
                                if(!facade.isGameOver()) {

                                    getGameWorld().addEntity(newBall(player.getPosition().getX() + 100, player.getPosition().getY() - 20));
                                    getAudioPlayer().playSound("death.wav");
                                }
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
                            double p =random.nextDouble();
                            getAudioPlayer().playSound("explotion.wav");
                            if(p<0.5){
                                getGameWorld().addEntity(newBallBonus(ball.getX(),ball.getY()+10, facade.getExtraBallBonus()));
                            }
                            else if(p<0.8){
                                getGameWorld().addEntity(newPointsBonus(ball.getX(),ball.getY()+10, facade.getExtraPointsBonus()));
                            }
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.PLAYER, BreakoutGameType.BONUS) {
                    @Override
                    protected void onHitBoxTrigger(Entity player, Entity bonus,
                                                   HitBox boxPlayer, HitBox boxBonus) {

                        bonus.removeFromWorld();
                        bonus.getComponent(BonusControl.class).trigger();

                    }
                });

        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.BONUS, BreakoutGameType.WALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity bonus, Entity wall,
                                                   HitBox boxBonus, HitBox boxWall) {
                        if (boxWall.getName().equals("BOT")) {
                            bonus.removeFromWorld();
                        }
                    }
                });
        getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(BreakoutGameType.PLAYER, BreakoutGameType.BALL) {
                    @Override
                    protected void onHitBoxTrigger(Entity player, Entity ball,
                                                   HitBox boxPlayer, HitBox ballBonus) {

                        getAudioPlayer().playSound("ballBounce.wav");
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
        vars.put("extra_balls", 0);
    }

    protected void reinitGameVars(){
        GameState gs = getGameState();
        gs.setValue("total_score", 0);
        gs.setValue("level_score", 0);
        gs.setValue("current_level", 0);
        gs.setValue("total_levels", 0);
        gs.setValue("balls_left", 3);
        gs.setValue("extra_balls", 0);

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
    protected void onUpdate(double tpf) {

        getGameState().setValue("total_score", facade.getCurrentPoints());
        getGameState().setValue("level_score", facade.getCurrentLevel().getCurrentPoints());
        getGameState().setValue("balls_left", facade.getBallsLeft());
        getGameState().setValue("extra_balls", facade.getExtraBalls());
    }

    /**
     * Remove the Entities of the actual Level
     */
    private void clearLevel() {
        GameWorld world = getGameWorld();
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.BONUS));
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.BRICK));
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.BALL));
    }

    /**
     * Generate the Entities of a new Level
     */
    private void generateLevel(){
        clearLevel();
        inGame=false;
        getAudioPlayer().playSound("levelup.wav");
        getGameState().setValue("current_level", getGameState().getInt("current_level")+1);
        int leftMargin = 50;
        int supMargin  = 150;
        int maxRight = 700;

        getGameWorld().addEntity(newBall(player.getX()+100, player.getY()-25));
        int posx = leftMargin;
        int posy = supMargin;

        int n = facade.numberOfBricks();

        List<Brick> bricks = facade.getBricks();

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

    /**
     * Show GameOver Message
     */
    private void gameOver() {
        getDisplay().showMessageBox("Game Over =( Jugar denuevo?", this::reinitGame);
    }

    /**
     * Show win the Game message
     */
    private void winGame() {
        getDisplay().showMessageBox("Felicitaciones, Pasaste todos los niveles, Te atreves otra vez?", this::reinitGame);
    }

    /**
     * Generate a new Ball Entity and shoot it
     */
    public void extraBall(){
        Entity extraBall = newBall(player.getX(),player.getY());
        getGameWorld().addEntity(extraBall);
        extraBall.getComponent(BallControl.class).shoot();
    }

    @Override
    public void update(Observable o, Object arg){
        if (!facade.isGameOver())
            this.generateLevel();
        else
            winGame();
    }


    /**
     * Reinitialize the actual game
     * <br>
     * Remove all the entities and create new ones
     */
    public void reinitGame(){
        clearLevel();
        GameWorld world = getGameWorld();
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.PLAYER));
        world.removeEntities(world.getEntitiesByType(BreakoutGameType.WALL));
        createWorld();
        reinitGameVars();
    }

    public static void main(String... args) {
        launch(args);
    }

}

import controller.Game;
import logic.bonus.Bonus;
import logic.bonus.ExtraBallBonus;
import logic.bonus.ExtraPointsBonus;
import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.PlayableLevel;
import org.junit.Before;
import org.junit.Test;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class GameTest{

    private Game game;
    @Before
    public void setUp(){
        game = new Game(3);
    }


    @Test
    public void gameOverWinnerTest(){
        assertFalse(game.isGameOver());
        assertFalse(game.winner());

        PlayableLevel level = new PlayableLevel("Test", 10,1,0,0);
        game.setCurrentLevel(level);

        assertFalse(game.isGameOver());
        assertFalse(game.winner());

        for(int i=0; i<3; i++){
            game.dropBall();
        }
        assertEquals(game.getBalls(),0);
        assertTrue(game.isGameOver());
        assertFalse(game.winner());

        setUp();

        assertFalse(game.winner());
        assertFalse(game.isGameOver());
        game.setCurrentLevel(level);
        for(Brick brick: level.getBricks()) {
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        assertTrue(game.isGameOver());
        assertTrue(game.winner());

    }

    @Test
    public void ballsManagementTest(){
        assertEquals(game.getBalls(), 3 );
        game.dropBall();
        assertEquals(game.getBalls(),2);
        game.addBall();
        assertEquals(game.getBalls(), 3 );
    }

    @Test
    public void pointsManagementTest(){
        assertEquals(game.getCurrentPoints(), 0);
        game.updatePoints(300);
        assertEquals(game.getCurrentPoints(), 300);
    }


    @Test
    public void levelManagementTest(){
        PlayableLevel level = new PlayableLevel("Test1", 10,1,0,0);
        PlayableLevel secondLevel = new PlayableLevel("Test2", 10,1,0,0);
        PlayableLevel thirdLevel = new PlayableLevel("Test3", 10,1,0,0);

        assertFalse(game.getCurrentLevel().isPlayableLevel());

        game.setCurrentLevel(level);
        game.addPlayingLevel(secondLevel);

        assertEquals(game.getCurrentLevel(), level);
        assertEquals(game.getCurrentLevel().getNextLevel(), secondLevel);

        game.goNextLevel();
        assertEquals(game.getCurrentLevel(), secondLevel);

        game.goNextLevel();
        assertFalse(game.getCurrentLevel().isPlayableLevel());

        game.addPlayingLevel(thirdLevel);
        assertFalse(game.getCurrentLevel().isPlayableLevel());
    }

    @Test
    public void gamePlayingTest(){
        int balls = 3;
        int points = 0;

        PlayableLevel level = new PlayableLevel("Test1", 3, 0.5, 0.5, 0);
        int expected_point = 2*200 + 50;
        int expected_balls = 1;

        PlayableLevel secondLevel = new PlayableLevel("Test2", 3, 1, 1.0, 0);
        int expected_point2 = 50*3;
        int expected_balls2 = 3;

        PlayableLevel thirdLevel = new PlayableLevel("Test3", 3, 1, 1.0, 0);
        int expected_balls3 = 0;
        int expected_point3 = 50*3;

        game.setCurrentLevel(level);
        game.addPlayingLevel(secondLevel);
        game.addPlayingLevel(thirdLevel);

        assertEquals(game.getCurrentPoints(),0);
        assertEquals(game.getBalls(),3);

        List<Brick> bricks = game.getCurrentLevel().getBricks();

        for(Brick brick: bricks.stream().filter(brick -> brick instanceof MetalBrick).collect(Collectors.toList())) {
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        for(Brick brick: bricks){
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        balls+=expected_balls;
        points+=expected_point;

        assertEquals(game.getCurrentPoints(), points);
        assertEquals(game.getCurrentLevel(), secondLevel);
        assertEquals(game.getBalls(), balls);

        bricks = game.getCurrentLevel().getBricks();
        for(Brick brick: bricks.stream().filter(brick -> brick instanceof MetalBrick).collect(Collectors.toList())) {
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        balls+=expected_balls2;
        assertEquals(game.getBalls(), balls);
        assertEquals(game.getCurrentPoints(), points);

        for(Brick brick: game.getCurrentLevel().getBricks()) {
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        points+=expected_point2;
        assertEquals(game.getCurrentPoints(), points);

        for(Brick brick: game.getCurrentLevel().getBricks()) {
            while (!brick.isDestroyed()) {
                brick.hit();
            }
        }
        points+=expected_point3;
        balls+=expected_balls3;
        assertEquals(game.getCurrentPoints(), points);
        assertEquals(game.getBalls(), balls);

        assertTrue(game.isGameOver()&& game.winner());
    }

    @Test
    public void extraBallBonusTest(){
        assertEquals(game.getExtraBalls(),0);

        Bonus exBallBonus = game.getExtraBallBonus();


        assertEquals(game.getExtraBalls(),0);
        exBallBonus.triggerBonus();
        assertEquals(game.getExtraBalls(),0);

        assertTrue(exBallBonus.isNullBonus());
        assertFalse(exBallBonus.isExtraPointsBonus());
        assertFalse(exBallBonus.isExtraBallBonus());

        ExtraBallBonus bonus = new ExtraBallBonus();
        game.setExtraBallBonus(bonus);

        assertEquals(game.getExtraBallBonus(), bonus);
        exBallBonus = game.getExtraBallBonus();

        assertFalse(exBallBonus.isNullBonus());
        assertFalse(exBallBonus.isExtraPointsBonus());
        assertTrue(exBallBonus.isExtraBallBonus());

        exBallBonus.triggerBonus();
        assertEquals(game.getExtraBalls(), 1);
        exBallBonus.triggerBonus();
        assertEquals(game.getExtraBalls(), 2);
    }


    @Test
    public void extraPointBonusTest(){
        ExtraPointsBonus bonus = new ExtraPointsBonus(0);


        PlayableLevel level = new PlayableLevel("Test1", 3, 0.5, 0.5, 0);
        game.setCurrentLevel(level);

        assertEquals( game.getCurrentLevel().getCurrentPoints(), 0);
        assertEquals(game.getCurrentPoints(), 0);

        Bonus exPointBonus = game.getExtraPointsBonus();

        exPointBonus.triggerBonus();

        assertEquals( game.getCurrentLevel().getCurrentPoints(), 0);
        assertEquals(game.getCurrentPoints(), 0);
        assertTrue(exPointBonus.isNullBonus());
        assertFalse(exPointBonus.isExtraPointsBonus());
        assertFalse(exPointBonus.isExtraBallBonus());


        game.setExtraPointsBonus(bonus);
        exPointBonus = game.getExtraPointsBonus();
        assertTrue(exPointBonus.isExtraPointsBonus());
        assertFalse(exPointBonus.isNullBonus());
        assertFalse(exPointBonus.isExtraBallBonus());

        exPointBonus.triggerBonus();
        assertEquals(game.getCurrentPoints(), 35000);
        assertEquals( game.getCurrentLevel().getCurrentPoints() , 0);

    }
}
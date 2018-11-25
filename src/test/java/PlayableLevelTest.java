import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.level.PlayableLevel;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PlayableLevelTest{
    private PlayableLevel level;
    private PlayableLevel secondLevel;
    private PlayableLevel thirdLevel;

    @Before
    public void setUp(){
        level = new PlayableLevel("Test", 10, 0.5, 0.5, 0);
        secondLevel = new PlayableLevel("secondLevel", 10,0.75, 0.5,0);
        thirdLevel = new PlayableLevel("thirdLevel", 10,0.5, 0,0);

    }
    @Test
    public void getNameTest() {
        assertEquals("Test", level.getName());
    }


    @Test
    public void BricksTest() {
        assertEquals(level.getNumberOfBricks(),16);
        assertEquals(level.getBricks().size(),16);
        assertEquals(level.getBricks().stream().filter(brick -> brick instanceof MetalBrick).collect(Collectors.toList()).size(), 6);
        assertEquals(level.getBricks().stream().filter(brick -> brick instanceof GlassBrick).collect(Collectors.toList()).size(), 3);
        assertEquals(level.getBricks().stream().filter(brick -> brick instanceof WoodenBrick).collect(Collectors.toList()).size(), 7);

        assertEquals(secondLevel.getNumberOfBricks(),16);
        assertEquals(secondLevel.getBricks().size(),16);
        assertEquals(secondLevel.getBricks().stream().filter(brick -> brick instanceof MetalBrick).collect(Collectors.toList()).size(), 6);
        assertEquals(secondLevel.getBricks().stream().filter(brick -> brick instanceof GlassBrick).collect(Collectors.toList()).size(), 7);
        assertEquals(secondLevel.getBricks().stream().filter(brick -> brick instanceof WoodenBrick).collect(Collectors.toList()).size(), 3);


        assertEquals(thirdLevel.getNumberOfBricks(),10);
        assertEquals(thirdLevel.getBricks().size(),10);
        assertEquals(thirdLevel.getBricks().stream().filter(brick -> brick instanceof MetalBrick).collect(Collectors.toList()).size(),0 );
        assertEquals(thirdLevel.getBricks().stream().filter(brick -> brick instanceof GlassBrick).collect(Collectors.toList()).size(), 3);
        assertEquals(thirdLevel.getBricks().stream().filter(brick -> brick instanceof WoodenBrick).collect(Collectors.toList()).size(), 7);

    }

    @Test
    public void getNextLevelTest(){
        assertFalse(level.getNextLevel().isPlayableLevel());
        level.setNextLevel(thirdLevel);
        assertEquals(level.getNextLevel(), thirdLevel);
    }

    @Test
    public void isPlayableLevelTest() {
        assertTrue(level.isPlayableLevel());
    }

    @Test
    public void hasNextLevelTest(){
        assertFalse(level.hasNextLevel());
        level.setNextLevel(secondLevel);
        assertTrue(level.hasNextLevel());
    }

    @Test
    public void addPlayingLevelTest() {
        assertFalse(level.getNextLevel().isPlayableLevel() );
        level.addPlayingLevel(secondLevel);
        level.addPlayingLevel(thirdLevel);
        assertEquals(level.getNextLevel(), secondLevel);
        assertEquals(level.getNextLevel().getNextLevel(),thirdLevel);
    }

    @Test
    public void setNextLevelTest() {
        level.setNextLevel(secondLevel);
        assertEquals(level.getNextLevel(), secondLevel);
    }

    @Test
    public void getPointsTest(){
        assertEquals(level.getPoints(), 1550);
        assertEquals(secondLevel.getPoints(),950 );
        assertEquals(thirdLevel.getPoints(), 1550);
        assertEquals(new PlayableLevel("No Bricks", 0, 0, 0, 0).getPoints(), 0);
        assertEquals(new PlayableLevel("Only GlassBricks", 10, 1, 0, 0).getPoints(), 500);
        assertEquals(new PlayableLevel("Only WoodenBricks", 10, 0, 0, 0).getPoints(), 2000);
    }
}
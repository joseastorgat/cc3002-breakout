package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrickTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayableLevelTest extends GlassBrickTest {
    private PlayableLevel level;

    @Before
    public void setUp(){
        level = new PlayableLevel("Test", 10, 0.5, 0.5, 0);

    }
    @Test
    public void getName() {
        assertEquals("Test", level.getName());
    }
//
//    @Test
//    public void getNumberOfBricks() {
//        assertEquals(level.getNumberOfBricks(), 16);
//    }

    @Test
    public void getBricks() {
        assertEquals(level.getNumberOfBricks(),16);
        List<Brick> bricks = level.getBricks();
        assertEquals(bricks.size(),16);
    }

    @Test
    public void getNextLevel() {
    }

    @Test
    public void isPlayableLevel() {
        assertTrue(level.isPlayableLevel());
    }

    @Test
    public void hasNextLevel() {

    }

    @Test
    public void addPlayingLevel() {
        Level secondLevel = new PlayableLevel("secondLevel", 20,0.5, 0.5,0);
        Level thirdLevel = new PlayableLevel("thirdLevel", 20,0.5, 0.5,0);

        level.addPlayingLevel(secondLevel);
        level.addPlayingLevel(thirdLevel);

        assertTrue(level.getNextLevel().equals(secondLevel));
        assertTrue(level.getNextLevel().getNextLevel().equals(thirdLevel));
    }

    @Test
    public void setNextLevel() {
        Level secondLevel = new PlayableLevel("secondLevel", 20,0.5, 0.5,0);
        assertFalse(level.hasNextLevel());
        level.setNextLevel(secondLevel);
        assertTrue(level.hasNextLevel());
        assertTrue(level.getNextLevel().equals(secondLevel));
    }

    @Test
    public void getPoints() {
    }
}
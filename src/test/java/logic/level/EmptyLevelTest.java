package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrickTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class EmptyLevelTest extends GlassBrickTest {
    private EmptyLevel level;

    @Before
    public void setUp(){
        level = new EmptyLevel();

    }
    @Test
    public void getNameTest() {
        assertEquals(level.getName(),"");
    }

    @Test
    public void getNumberOfBricks() {
        assertEquals(level.getNumberOfBricks(),0);
    }

    @Test
    public void getBricks() {
        assertTrue(level.getBricks().isEmpty());
    }

    @Test
    public void getNextLevel() {
    
    }

    @Test
    public void isPlayableLevel() {
    }

    @Test
    public void hasNextLevel() {
    }

    @Test
    public void addPlayingLevel() {
    }

    @Test
    public void setNextLevel() {
    }

    @Test
    public void getPoints() {
    }

    @Test
    public void accept() {
    }
}
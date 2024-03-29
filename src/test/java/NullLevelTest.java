import logic.level.NullLevel;
import logic.level.Level;
import logic.level.PlayableLevel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NullLevelTest {
    private NullLevel level;

    @Before
    public void setUp(){
        level = new NullLevel();
    }
    @Test
    public void getNameTest() {
        assertEquals(level.getName(),"");
    }

    @Test
    public void getNumberOfBricksTest() {
        assertEquals(level.getNumberOfBricks(),0);
    }

    @Test
    public void getBricksTest() {
        assertTrue(level.getBricks().isEmpty());
    }

    @Test
    public void getNextLevelTest(){
        assertEquals(level, level.getNextLevel());
    }

    @Test
    public void isPlayableLevelTest() {
        assertFalse(level.isPlayableLevel());
    }

    @Test
    public void hasNextLeveTestl() {
        assertFalse(level.isPlayableLevel());
    }

    @Test
    public void addPlayingLevelTest() {
        Level newLevel = new PlayableLevel("test",0,0,0,0);
        assertEquals(level.addPlayingLevel(newLevel), newLevel );
        assertFalse(level.hasNextLevel());
    }

    @Test
    public void getPointsTest() {
        assertEquals(level.getPoints(), 0);
    }

    @Test
    public void setNextLevelTest(){
        level.setNextLevel(new PlayableLevel("test",0,0,0,0));
        assertFalse(level.hasNextLevel());
    }

    @Test
    public void pointsManagementTest(){
        assertEquals(level.getPoints(), 0);
        assertEquals(level.getCurrentPoints(), 0);
        level.addPoints(1000);
        assertEquals(level.getCurrentPoints(), 0);


    }


}
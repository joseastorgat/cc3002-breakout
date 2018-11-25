import logic.brick.Brick;
import logic.brick.GlassBrick;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GlassBrickTest {
    protected Brick brick;
    protected int score;
    protected int hits;

    @Before
    public void setUp(){
        brick  = new GlassBrick();
        score = 50;
        hits   = 1;
    }

    @Test
    public void hitTest(){
        assertEquals(brick.remainingHits(), hits);
        brick.hit();
        assertEquals(brick.remainingHits(), hits-1);
    }


    @Test
    public void getScoreTest(){
        assertEquals(brick.getScore(), score);
    }


    @Test
    public  void isDestroyedTest(){
        assertEquals(brick.remainingHits(), hits);
        for(int i=0; i < this.hits; i++){
            brick.hit();
        }
        assertTrue(brick.isDestroyed());
        brick.hit();
        brick.hit();
        assertEquals(brick.remainingHits(), 0);

    }


}
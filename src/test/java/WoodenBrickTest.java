import logic.brick.WoodenBrick;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class WoodenBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new WoodenBrick();
        score = 200;
        hits   = 3;

    }

    @Test
    public void isBrickTest(){
        assertTrue(brick.isWoodenBrick());
        assertFalse(brick.isGlassBrick());
        assertFalse(brick.isMetalBrick());
    }
}
import logic.brick.MetalBrick;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MetalBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new MetalBrick();
        score  = 0;
        hits   = 10;

    }
    @Test
    public void isBrickTest(){
        assertFalse(brick.isWoodenBrick());
        assertFalse(brick.isGlassBrick());
        assertTrue(brick.isMetalBrick());
    }

}
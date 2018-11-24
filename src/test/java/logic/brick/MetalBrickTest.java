package logic.brick;

import org.junit.Before;

public class MetalBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new MetalBrick();
        score = 200;
        hits   = 3;

    }


}
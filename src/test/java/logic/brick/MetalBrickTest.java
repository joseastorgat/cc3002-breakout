package logic.brick;

import org.junit.Before;

public class MetalBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick = new WoodenBrick() MetalBrickTest;
        score = 0;
        hits  = 10;

    }


}
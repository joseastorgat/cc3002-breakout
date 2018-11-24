package logic.brick;

import org.junit.Before;

import static org.junit.Assert.*;

public class WoodenBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new WoodenBrick();
        score = 200;
        hits   = 3;

    }


}
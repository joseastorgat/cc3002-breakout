import logic.brick.WoodenBrick;
import org.junit.Before;

public class WoodenBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new WoodenBrick();
        score = 200;
        hits   = 3;

    }


}
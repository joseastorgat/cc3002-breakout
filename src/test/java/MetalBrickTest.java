import logic.brick.MetalBrick;
import org.junit.Before;

public class MetalBrickTest extends GlassBrickTest {

    @Before
    public void setUp() {
        brick  = new MetalBrick();
        score  = 0;
        hits   = 10;

    }


}
package logic.brick;



/**
 * GlassBrick class is an {@link Brick}
 * <br>
 * 1 hit destroys it. Score 50 Points.
 *
 * @author José Astorga
 */
public class GlassBrick extends AbstractBrick {
	/**
	 * Glass Brick constructor
	 */
	public GlassBrick(){
		super(1, 50);
	}

	@Override
	public boolean isGlassBrick(){
		return true;
	}


}

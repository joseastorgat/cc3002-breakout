package logic.brick;


/**
 * WoodenBrick class is a {@link Brick}
 * <br>
 * 3 hit destroys it. Score 200 Points.
 *
 * @author José Astorga
 */
public class WoodenBrick extends AbstractBrick {
	/**
	 * WoodenBrick Constructor
	 */
	public WoodenBrick(){
		super(3, 200);
	}

	@Override
	public boolean isWoodenBrick(){
		return true;
	}

}

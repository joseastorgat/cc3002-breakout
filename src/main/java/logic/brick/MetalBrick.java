package logic.brick;

import visitor.UpgradeGameBallsVisitor;
import visitor.Visitor;

/**
 * MetalBrick class is a {@link Brick}
 * <br>
 * 10 hit destroys it. Score 0 Points.
 *
 * @author José Astorga
 */
public class MetalBrick extends AbstractBrick {
	/**
	 * Metal Brick constructor
	 */
	public MetalBrick(){
		super(10, 0);
	}

	@Override
	public boolean isMetalBrick(){
		return true;
	}

	@Override
	public void notifyUp(){
        notifyObservers(new UpgradeGameBallsVisitor());
	}
}


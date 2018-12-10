package logic.brick;

import visitor.UpdateGamePointsVisitor;
import visitor.Visitor;

/**
 * WoodenBrick class is an {@link Brick}
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
	public void accept(Visitor v){
		v.visitWoodenBrick(this);
	}

	@Override
	public boolean isWoodenBrick(){
		return true;
	}



}
